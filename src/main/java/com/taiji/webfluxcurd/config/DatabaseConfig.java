//package com.taiji.webfluxcurd.config;
//
//import com.taiji.webfluxcurd.converter.UserReadingConverter;
//import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
//import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author hantsy
// */
//@Configuration
//@EnableTransactionManagement
//@EnableR2dbcRepositories
//public class DatabaseConfig extends AbstractR2dbcConfiguration {
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
//                .host("127.0.0.1")
//                .port(3306)
//                .username("root")
//                .password("root")
//                .database("webflux")
//                .build());
//    }
//
//
//
//    @Override
//    protected List<Object> getCustomConverters() {
//        List<Object> objects = new ArrayList<>();
//        objects.add(new UserReadingConverter());
//        return objects;
//    }
//
//
//
//}
