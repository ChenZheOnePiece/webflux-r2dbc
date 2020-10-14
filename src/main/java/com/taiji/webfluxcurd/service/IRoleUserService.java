package com.taiji.webfluxcurd.service;

import java.util.List;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.RoleUser;

import reactor.core.publisher.Mono;

public interface IRoleUserService {

    Mono<RoleUser> add(RoleUser roleUser);

    Mono<RoleUser> findByRoleId(String id);

    Mono<Void> delete(String id);
 
}
