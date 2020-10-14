package com.taiji.webfluxcurd.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

/**
 * The type R 2 dbc config.
 *
 * @author a
 * @since 16 :32
 */
//@Configuration
public class R2dbcConfiguration {


//    @Bean
    ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
                .host("127.0.0.1")
                .port(3306)
                .username("root")
                .password("123456")
                .database("bc_rec")
                .build());
    }
}
