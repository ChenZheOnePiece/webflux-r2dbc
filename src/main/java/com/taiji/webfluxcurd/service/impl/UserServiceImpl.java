package com.taiji.webfluxcurd.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.taiji.webfluxcurd.domain.*;
import com.taiji.webfluxcurd.dto.RoleUserDto;
import com.taiji.webfluxcurd.service.IUserService;
import com.taiji.webfluxcurd.utils.PageDTO;
import com.taiji.webfluxcurd.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Mono<User> add(User user) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        user.setId(id);
        Mono<User> userMono = userRepository.save(user);
        return userMono;
    }

    @Override
    public Mono<User> update(User user) {
        return userRepository.update(user);
    }

    @Override
    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<List<User>> findAll() {
        PageRequest of = PageRequest.of(0, 10);
        Sort createdDate = Sort.by(Sort.Order.asc("id"));
        Criteria aNull = Criteria.where("name").isNotNull();
        Criteria id = Criteria.where("id").isNotNull();
        ArrayList<Criteria> objects = new ArrayList<>();
        objects.add(aNull);
        objects.add(id);
        CriteriaDefinition from1 = CriteriaDefinition.from(objects);
        Query name = Query.empty().query(from1).with(of).sort(createdDate);
        Flux<List<User>> page = userRepository.Page(name);
        Mono<List<User>> from = Mono.from(page);
        return from;
    }


    @Override
    public Flux<User> uploadImg(FilePart filePart) throws IOException {
        Flux<DataBuffer> content = filePart.content();
        Flux<byte[]> map = content.map(dataBuffer -> {

            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            return bytes;
        });
        Flux<List<byte[]>> buffer = map.buffer();
        return buffer.flatMap(x -> {
            byte[] bytes = new byte[100];
            for (int i = 0; i < x.size(); i++) {
                byte[] bytes1 = x.get(i);
                bytes = ArrayUtil.addAll(bytes, bytes1);
            }
            User user = new User();
            user.setName("文件上传测试");
            user.setId("ssssssssssssssss");
            user.setContent(bytes);
            user.setAge(12);
            return userRepository.save(user);

        });

    }


    @Override
    public Mono<List<User>> page(PageDTO pageDTO) {
        Query query = PageUtil.getQuery(pageDTO);
        Flux<List<User>> page = userRepository.Page(query);
        Mono<List<User>> from = Mono.from(page);
        return from;
    }

    @Override
    @Transactional
    public Mono<Void> delUserAndRole(String id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.deleteById(user.getId())
                        .then(roleUserRepository.deleteByUserId(id)))
                .doOnError(throwable -> {
                    System.out.println(throwable.getStackTrace());
                })
                .doOnSuccess(throwable -> {
                    System.out.println("成功");
                });
    }

    @Override
    public Flux<RoleUserDto> joinPage(User user, int page,int size) {
//    public Mono<Map<String, Object>> joinPage(User user, int page,int size) {
//        Flux<Map<String, Object>> mapFlux = userRepository.joinPage(user, page, size);
//        Flux<RoleUserDto> mapFlux = userRepository.joinPage(user, page, size);
//        mapFlux.subscribe(x -> System.out.println(x.size()));
//        Mono<Map<String, Object>> from = Mono.from(mapFlux);
//        Mono<List<RoleUserDto>> from = Mono.from(mapFlux);
        return Flux.just(null);
    }

    @Override
    @Transactional
    public Mono<Role> updateUserAndRole(UserRole u) {
        return userRepository.findById(u.getId())
                .flatMap(user ->{
                             user.setName(u.getName());
                           return userRepository.update(user);
                        })
                        .flatMap( user -> roleUserRepository.findByUserId(user.getId()))
                        .flatMap(roleUser -> roleRepository.findById(roleUser.getRoleId()))
                        .flatMap(role -> {
//                            Integer.parseInt("1231sdsdsd23");
                             role.setRoleName(u.getRoleName());
                             return    roleRepository.update(role);
                         });

    }


}
