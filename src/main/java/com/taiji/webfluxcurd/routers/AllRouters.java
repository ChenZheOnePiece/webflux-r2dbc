//package com.taiji.webfluxcurd.routers;
//
//import com.taiji.webfluxcurd.handle.UserHandle;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//@Configuration
//public class AllRouters {
//
//    @Bean
//    RouterFunction<ServerResponse> routerFunction(UserHandle userHandle) {
//        return RouterFunctions.nest(RequestPredicates.path("/user"),
//                RouterFunctions.route(RequestPredicates.path("/findAll"), userHandle::getAllUser));
//
//    }
// }
