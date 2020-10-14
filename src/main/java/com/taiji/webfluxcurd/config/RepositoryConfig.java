package com.taiji.webfluxcurd.config;

import com.taiji.webfluxcurd.base.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories( basePackages = "com.taiji.webfluxcurd",repositoryBaseClass= BaseRepositoryImpl.class)
public class RepositoryConfig {



}
