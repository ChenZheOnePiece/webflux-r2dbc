package com.taiji.webfluxcurd.controller;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.User;
import com.taiji.webfluxcurd.domain.UserRepository;
import com.taiji.webfluxcurd.domain.UserRole;
import com.taiji.webfluxcurd.dto.RoleUserDto;
import com.taiji.webfluxcurd.service.IUserService;
import com.taiji.webfluxcurd.utils.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

/**
 * 如果不使用Controller方式 请注释该类,打开UserHandler和AllRouters 请求也可以正常运行这种方式不太常用,仅用于演示
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping(value = "/add")
    public Mono<User> add(@RequestBody User user) {
        log.info("start");
        Mono<User> add = iUserService.add(user);
        log.info("end");
        return add;
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping(value = "/update")
    public Mono<User> update(@RequestBody User user) {
        log.info("start");
        Mono<User> add = iUserService.update(user);
        log.info("end");
        return add;
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping(value = "/findById")
    public Mono<User> findById( String id) {
        Mono<User> add = iUserService.findById(id);
        return add;
    }

    /**
     * 查询所所有用户不不分页
     * @return
     */
    @GetMapping(value = "/findAll")
    public Mono<List<User>> findAll(  ) {
        Mono<List<User>> findAll = iUserService.findAll();
        return findAll;
    }

    /**
     * 删除用户以及用户下的角色关系
     * @return
     */
    @GetMapping(value = "/delUserAndRoleUser")
    public Mono<Void> delUser(String id) {
       return iUserService.delUserAndRole(id);
    }

    /**
     * 单表分页
     * @return
     */
    @PostMapping(value = "/page")
    public Mono<List<User>> page(@RequestBody PageDTO pageDTO) {
        Mono<List<User>> findAll = iUserService.page(pageDTO);
        return findAll;
    }

    @PostMapping(value = "/joinPage")
    public Flux<RoleUserDto> testJpql(@RequestBody User user  ) {
    	Flux<RoleUserDto> findAll = iUserService.joinPage(user,0,10);
//    public Mono<Map<String, Object>> testJpql(@RequestBody User user  ) {
//        Mono<Map<String, Object>> findAll = iUserService.joinPage(user,0,3);
        return findAll;
    }


    @PostMapping(value = "/uploadimg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> uploadImg(@RequestPart("file") FilePart filePart,@RequestParam("userId") String userId) throws IOException {

        return iUserService.uploadImg(filePart);
    }
//    @PostMapping(value = "/testobj", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//    public Mono<User> testobj() throws IOException {
//        Prize prize = new Prize();
//        prize.setName("123123");
//        ByteArrayOutputStream ba = new ByteArrayOutputStream();
//        ObjectOutputStream oos=new ObjectOutputStream(ba);
//        oos.writeObject(prize);
//        User user = new User();
//        user.setName("对象测试");
//        user.setContent(ba.toByteArray());
//        user.setAge(12);
//        user.setId("123123123123123");
//        Mono<User> save = userRepository.save(user);
//        byte[] pBytes=ba.toByteArray();
//        return save;
//    }

    @PostMapping("/updateUserAndRole")
    public Mono<Role> updateUserAndRole(@RequestBody UserRole user) {
       return iUserService.updateUserAndRole(user);
    }

}
