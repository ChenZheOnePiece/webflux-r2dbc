package com.taiji.webfluxcurd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.RoleRepository;
import com.taiji.webfluxcurd.service.IRoleService;
import com.taiji.webfluxcurd.utils.PageDTO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 如果不使用Controller方式 请注释该类,打开UserHandler和AllRouters 请求也可以正常运行这种方式不太常用,仅用于演示
 */
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 新增角色
     * @param role
     * @return
     */
    @PostMapping(value = "/add")
    public Mono<Role> add(@RequestBody Role role) {
        log.info("start");
        Mono<Role> add = iRoleService.add(role);
        log.info("end");
        return add;
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PostMapping(value = "/update")
    public Mono<Role> update(@RequestBody Role role) {
        log.info("start");
        Mono<Role> add = iRoleService.update(role);
        log.info("end");
        return add;
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @GetMapping(value = "/findByRoleId")
    public Mono<Role> findById( String id) {
        Mono<Role> add = iRoleService.findById(id);
        return add;
    }
    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @GetMapping(value = "/delById")
    public Mono delById( String id) {
    	try {
    		iRoleService.delete(id);
    		return Mono.just("sussce");
		} catch (Exception e) {
    		return Mono.just("false");
		}
    }
    /**
     * 查询所所有角色不不分页
     * @return
     */
    @GetMapping(value = "/findAll")
    public Mono<List<Role>>findAll(  ) {
        Mono<List<Role>> findAll = iRoleService.findAll();
        return findAll;
    }

    /**
     * 单表分页
     * @return
     */
    @PostMapping(value = "/page")
    public Mono<List<Role>> page(@RequestBody PageDTO pageDTO) {
        Mono<List<Role>> findAll = iRoleService.page(pageDTO);
        return findAll;
    }

    @PostMapping(value = "/testjpql")
    public Mono<List<Role>> testJpql(@RequestBody Role role  ) {
        Mono<List<Role>> findAll = iRoleService.testSql(role);
        return findAll;
    }
   

}
