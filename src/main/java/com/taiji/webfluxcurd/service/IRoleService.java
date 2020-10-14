package com.taiji.webfluxcurd.service;

import java.util.List;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.utils.PageDTO;

import reactor.core.publisher.Mono;

public interface IRoleService {

    Mono<Role> add(Role role);

    Mono<Role> update(Role role);

    Mono<Role> findById(String id);

    Mono<Void> delete(String id);
    Mono<List<Role>> findAll();



    Mono<List<Role>> testSql(Role role);

    Mono<List<Role>> page(PageDTO pageDTO);
}
