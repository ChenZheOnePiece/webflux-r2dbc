package com.taiji.webfluxcurd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.RoleUser;
import com.taiji.webfluxcurd.domain.RoleUserRepository;
import com.taiji.webfluxcurd.service.IRoleUserService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 如果不使用Controller方式 请注释该类,打开UserHandler和AllRouters 请求也可以正常运行这种方式不太常用,仅用于演示
 */
@RestController
@Slf4j
@RequestMapping("/roleUser")
public class RoleUserController {

    @Autowired
    private IRoleUserService iRoleUserService;

    @Autowired
    private RoleUserRepository roleUserRepository;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping(value = "/add")
    public Mono<RoleUser> add(@RequestBody RoleUser roleUser) {
        log.info("start");
        Mono<RoleUser> add = iRoleUserService.add(roleUser);
        log.info("end");
        return add;
    }

 
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @GetMapping(value = "/findByRoleId")
    public Mono<RoleUser> findByRoleId( String id) {
        Mono<RoleUser> add = iRoleUserService.findByRoleId(id);
        return add;
    }

    @GetMapping(value = "/delById")
    public Mono delById( String id) {
    	try {
    		iRoleUserService.delete(id);
    		return Mono.just("sussce");
		} catch (Exception e) {
    		return Mono.just("false");
		}
    }
}
