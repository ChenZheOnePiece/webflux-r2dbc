package com.taiji.webfluxcurd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.RoleRepository;
import com.taiji.webfluxcurd.domain.User;
import com.taiji.webfluxcurd.service.IRoleService;
import com.taiji.webfluxcurd.utils.PageDTO;
import com.taiji.webfluxcurd.utils.SortDTO;

import cn.hutool.core.util.StrUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Mono<Role> add(Role role) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        role.setId(id);
        Mono<Role> roleMono = roleRepository.save(role);
        return roleMono;
    }

    @Override
    public Mono<Role> update(Role role) {
        return roleRepository.update(role);
    }

    @Override
    public Mono<Role> findById(String id) {
        return roleRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return roleRepository.deleteById(id);
    }

    @Override
    public Mono<List<Role>> findAll() {
        PageRequest of = PageRequest.of(0, 10);
        Sort createdDate = Sort.by(Sort.Order.asc("created_date"));
        Criteria aNull = Criteria.where("role_name").isNotNull();
        Criteria id = Criteria.where("id").isNotNull();
        ArrayList<Criteria> objects = new ArrayList<>();
        objects.add(aNull);
        objects.add(id);
        CriteriaDefinition from1 = CriteriaDefinition.from(objects);
        Query name = Query.empty().query(from1).with(of).sort(createdDate);
        Flux<List<Role>> page = roleRepository.Page(name);
        Mono<List<Role>> from = Mono.from(page);
        return from;
    }
 

    @Override
    public Mono<List<Role>> testSql(Role role) {
//        Flux<User> allUser = userRepository.findAllUser(PageRequest.of(0,3));
        Flux<Role> allRole = roleRepository.findAllRole(PageRequest.of(0,3));
        roleRepository.findAllByNameAndAgeIsNotNull("444444", PageRequest.of(0, 3));

        Flux<List<Role>> buffer = allRole.buffer();
        allRole.map(x -> {
            System.out.println(x);
            return x;
        }).subscribe(System.out::println);


        return Mono.from(buffer);
    }

    @Override
    public Mono<List<Role>> page(PageDTO pageDTO) {
        int page = pageDTO.getPage();
        int pageSize = pageDTO.getPageSize();
        List<SortDTO> sorts = pageDTO.getSorts();
        ArrayList<Sort.Order> objects = new ArrayList<>();
        for (SortDTO sort : sorts) {
            String direction = sort.getDirection();
            if (StrUtil.isNotBlank(direction) && "ASC".equals(direction.toUpperCase())) {
                objects.add(Sort.Order.asc(sort.getFieldName()));
            }
            if (StrUtil.isNotBlank(direction) && "DES".equals(direction.toUpperCase())) {
                objects.add(Sort.Order.desc(sort.getFieldName()));
            }
        }
        Map filters = pageDTO.getFilters();
        Query query = Query.empty().with(PageRequest.of(page, pageSize)).sort(Sort.by(objects));
        Flux<List<Role>> pagelist = roleRepository.Page(query);
        Mono<List<Role>> from = Mono.from(pagelist);
        return from;
    }

}
