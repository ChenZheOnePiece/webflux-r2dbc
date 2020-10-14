package com.taiji.webfluxcurd.domain;

import com.taiji.webfluxcurd.base.IBaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RoleUserRepository extends IBaseRepository<RoleUser,String> {

    Flux<RoleUser> findRoleByUserId(String userId);

    Mono<Void> deleteByUserId(String userId);
    Mono<RoleUser> findByUserId(String userId);

    Mono<RoleUser> findByRoleId(String roleId);
}
