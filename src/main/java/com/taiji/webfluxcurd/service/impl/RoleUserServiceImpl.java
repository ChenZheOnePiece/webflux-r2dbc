package com.taiji.webfluxcurd.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.RoleUser;
import com.taiji.webfluxcurd.domain.RoleUserRepository;
import com.taiji.webfluxcurd.service.IRoleUserService;

import reactor.core.publisher.Mono;

@Service
public class RoleUserServiceImpl implements IRoleUserService {
    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    @Transactional
    public Mono<RoleUser> add(RoleUser roleUser) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        roleUser.setId(id);
        Mono<RoleUser> roleMono = roleUserRepository.save(roleUser);
        return roleMono;
    }
 

    @Override
    public Mono<RoleUser> findByRoleId(String id) {
        return roleUserRepository.findByRoleId(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return roleUserRepository.deleteById(id);
    }


}
