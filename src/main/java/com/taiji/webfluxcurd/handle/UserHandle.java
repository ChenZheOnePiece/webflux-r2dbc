//package com.taiji.webfluxcurd.handle;
//
//import com.taiji.webfluxcurd.domain.User;
//import com.taiji.webfluxcurd.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//@Component
//public class UserHandle {
//
//    @Autowired
//    private IUserService iUserService;
//
//    public Mono<ServerResponse> getAllUser(ServerRequest serverRequest) {
//        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
//                .body(iUserService.findAll(), User.class);
//    }
//}
