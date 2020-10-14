package com.taiji.webfluxcurd.controller;

import com.taiji.webfluxcurd.domain.Role;
import com.taiji.webfluxcurd.domain.User;
import com.taiji.webfluxcurd.domain.UserRepository;
import com.taiji.webfluxcurd.domain.UserRole;
import com.taiji.webfluxcurd.projection.UserProjection;
import com.taiji.webfluxcurd.utils.PageDTO;
import com.taiji.webfluxcurd.utils.SortDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
class UserControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Autowired
    private UserRepository userRepository;
    @Test
    void add() {
        User user = new User();
        user.setAge(11);
        user.setName("chenzhe");
        Flux<User> responseBody = webClient.post()
                .uri("/user/add")
                .bodyValue(user)
                .exchange()
                .returnResult(User.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);

    }

    @Test
    void update() {
        User user = new User();
        user.setId("123123123123123");
        user.setAge(11);
        user.setName("chenzhe");
        Flux<User> responseBody = webClient.post()
                .uri("/user/update")
                .bodyValue(user)
                .exchange()
                .returnResult(User.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void findById() {
        Flux<User> responseBody = webClient.get()
                .uri("/user/findById?id=123123123123123")
                .exchange()
                .returnResult(User.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void findAll() {
        Flux<User> responseBody = webClient.get()
                .uri("/user/findAll")
                .exchange()
                .returnResult(User.class)
                .getResponseBody();
        Flux<List<User>> buffer = responseBody.buffer();
        buffer.subscribe(users ->{
            for (User user : users) {
                System.out.println(user);
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
        Flux<User> responseBody = webClient.post()
                .uri("/user/page")
                .bodyValue(pageDTO)
                .exchange()
                .returnResult(User.class)
                .getResponseBody();

        Flux<List<User>> buffer = responseBody.buffer();
        buffer.subscribe(users ->{
            for (User user : users) {
                System.out.println(user);
            }
        });
        buffer.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void delUser() {
        webClient.get()
                .uri("/user/delUserAndRoleUser?id=123123123234")
                .exchange()
                .returnResult(Void.class)
                .getResponseBody();


    }
    @Test
    void updateUserAndRole(){
        UserRole user = new UserRole();
        user.setId("40b3d32283b1438c8faa966d756b7211");
        user.setAge(11);
        user.setName("事务测试");
        user.setRoleName("事务测试");
        Flux<Role> responseBody = webClient.post()
                .uri("/user/updateUserAndRole")
                .bodyValue(user)
                .exchange()
                .returnResult(Role.class)
                .getResponseBody();

        responseBody.subscribe(System.out::println);
        responseBody.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void uploadImg() {
    }
    @Test
    void joinPage() {
        User user = new User();
        user.setAge(11);
        user.setName("chenzhe11111");
        Flux<User> responseBody = webClient.post()
                .uri("/user/joinPage")
                .bodyValue(user)
                .exchange()
                .returnResult(User.class)
                .getResponseBody();

        Flux<List<User>> buffer = responseBody.buffer();
        buffer.subscribe(users ->{
            for (User user1 : users) {
                System.out.println(user1);
            }
        });
        buffer.as(StepVerifier::create)
                .assertNext(Assert::assertNotNull);
    }

    @Test
    void testJpql() {
        User user = new User();
        Flux<User> allUser = userRepository.findAllUser(user);
        allUser.subscribe(System.out::println);
    }

    @Test
    void testPro() {
        User user = new User();
        user.setAge(11);
        user.setName("chenzhe11111");
        Flux<UserProjection> userProjectionFlux = userRepository.joinPageByPro(user, 0, 3);
        userProjectionFlux.subscribe(x -> {
            System.out.println(x.getName());
//            System.out.println(x.getRole().getRoleName());
        });
    }

}