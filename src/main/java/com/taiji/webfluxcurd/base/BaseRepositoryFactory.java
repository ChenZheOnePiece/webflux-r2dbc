//package com.taiji.webfluxcurd.base;
//
//import org.springframework.data.r2dbc.core.DatabaseClient;
//import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
//import org.springframework.data.r2dbc.core.ReactiveDataAccessStrategy;
//import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
//import org.springframework.data.relational.repository.query.RelationalEntityInformation;
//import org.springframework.data.repository.core.RepositoryMetadata;
//
//public  class BaseRepositoryFactory extends R2dbcRepositoryFactory {
//
//    public BaseRepositoryFactory(DatabaseClient databaseClient, ReactiveDataAccessStrategy dataAccessStrategy) {
//        super(databaseClient, dataAccessStrategy);
//    }
//
//    public BaseRepositoryFactory(R2dbcEntityOperations operations) {
//        super(operations);
//    }
//
//    @Override
//    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
//        //注册自定义BaseRepository实现
//        return BaseRepositoryImpl.class;
//    }
//
//    @Override
//    public <T, ID> RelationalEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
//        return super.getEntityInformation(domainClass);
//    }
//}
