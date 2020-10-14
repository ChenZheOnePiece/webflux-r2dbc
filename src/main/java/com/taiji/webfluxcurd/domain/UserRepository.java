package com.taiji.webfluxcurd.domain;

import java.util.List;
import java.util.Map;

import com.taiji.webfluxcurd.projection.UserProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taiji.webfluxcurd.base.IBaseRepository;
import com.taiji.webfluxcurd.dto.RoleUserDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends IBaseRepository<User, String> {


    @Modifying
    @Query("insert into user (id,name,age,content,created_date,modified_date) values (:#{#user.id},:#{#user.name},:#{#user.age},:#{#user.content},:#{#user.createdDate},:#{#user.modifiedDate})")
    Mono<User> addUser(User user);

    Flux<User> findAllByNameAndAgeIsNotNull(String name, Pageable pageable);

    //    @Query("select * from user limit 0,3")
    @Query("select name from user  where 1=1 and (:#{#user.name} is null or u.name = :#{#user.name}) ")
    Flux<User> findAllUser(User user);

    @Query("select u.id ,u.name,u.age as age, r.role_name  from user " +
            "u left join role_user ur on u.id=ur.user_id " +
            "left join role r on ur.role_id=r.id" +
            " where 1=1 and (:#{#user.name} is null or u.name = :#{#user.name}) " +
            " LIMIT :#{#page},:#{#size}")
    Flux<UserProjection> joinPageByPro(@Param("user") User user, @Param("page") int page, @Param("size") int size);


    @Query("select u.id ,u.name,u.age as age, r.role_name  from user " +
            "u left join role_user ur on u.id=ur.user_id " +
            "left join role r on ur.role_id=r.id" +
            " where 1=1 and (:#{#user.name} is null or u.name = :#{#user.name}) " +
            " LIMIT :#{#page},:#{#size}")
    Flux<RoleUserDto> joinPage(@Param("user") User user, @Param("page") int page, @Param("size") int size);
}
