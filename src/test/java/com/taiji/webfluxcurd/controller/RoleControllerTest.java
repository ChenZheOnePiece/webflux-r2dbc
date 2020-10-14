package com.taiji.webfluxcurd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.User;
import com.taiji.webfluxcurd.utils.PageDTO;
import com.taiji.webfluxcurd.utils.SortDTO;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class RoleControllerTest {
    @Autowired
    private WebTestClient webClient;
    @Test
    void add() {
        Role role = new Role();
        role.setRoleName("wanghw");
        Flux<Role> responseBody = webClient.post()
                .uri("/role/add")
                .bodyValue(role)
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);

    }

    @Test
    void update() {
        Role role = new Role();
        role.setId("123");
        role.setRoleName("wanghw1");
        Flux<Role> responseBody = webClient.post()
                .uri("/role/update")
                .bodyValue(role)
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void findById() {
        Flux<Role> responseBody = webClient.get()
                .uri("/role/findById?id=123123123123123")
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void findAll() {
        Flux<Role> responseBody = webClient.get()
                .uri("/role/findAll")
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();
        Flux<List<Role>> buffer = responseBody.buffer();
        buffer.subscribe(roles ->{
            for (Role role : roles) {
                System.out.println(role);
            }
        });
        buffer.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void page() {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(1);
        pageDTO.setPageSize(10);
        Map map = new HashMap<>();
        map.put("name", "444");
        pageDTO.setFilters(map);
        List list = new ArrayList();
        SortDTO sortDTO = new SortDTO();
        sortDTO.setDirection("ASC");
        sortDTO.setFieldName("createdDate");
        list.add(sortDTO);
        pageDTO.setSorts(list);
        Flux<Role> responseBody = webClient.post()
                .uri("/role/page")
                .bodyValue(pageDTO)
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();

        Flux<List<Role>> buffer = responseBody.buffer();
        buffer.subscribe(roles ->{
            for (Role role : roles) {
                System.out.println(role);
            }
        });
        buffer.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

}