package com.taiji.webfluxcurd.domain;

import com.taiji.webfluxcurd.base.IBaseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface RoleRepository extends IBaseRepository<Role,String> {


    @Modifying
    @Query("insert into role (id,createdDate,roleName) values (:#{#role.id},:#{#role.createdDate},:#{#role.roleName})")
    Mono<Role> addRole( Role role);

    Flux<User> findAllByNameAndAgeIsNotNull(String name,Pageable pageable);

//    @Query("select * from user limit 0,3")
    @Query("select * from role r")
    Flux<Role> findAllRole(Pageable pageable);

//    Mono<Role> findById(String id);


}
