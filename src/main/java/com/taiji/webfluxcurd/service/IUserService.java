package com.taiji.webfluxcurd.service;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.User;
import com.taiji.webfluxcurd.domain.UserRole;
import com.taiji.webfluxcurd.dto.RoleUserDto;
import com.taiji.webfluxcurd.utils.PageDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IUserService {

    Mono<User> add(User user);

    Mono<User> update(User user);

    Mono<User> findById(String id);

    Mono<Void> delete(String id);

    Mono<List<User>> findAll();


    Flux<User> uploadImg(FilePart filePart) throws IOException;


    Mono<List<User>> page(PageDTO pageDTO);

    Mono<Void> delUserAndRole(String id);

    Flux<RoleUserDto> joinPage(User user, int page, int size);

    Mono<Role> updateUserAndRole(UserRole user);
}
