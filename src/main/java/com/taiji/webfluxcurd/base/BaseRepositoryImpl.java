package com.taiji.webfluxcurd.base;

import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.repository.query.RelationalEntityInformation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BaseRepositoryImpl<T, ID> extends SimpleR2dbcRepository<T, ID> implements IBaseRepository<T,ID> {

    private final R2dbcEntityOperations entityOperations;

    private final RelationalEntityInformation<T, ID> entity;

    public BaseRepositoryImpl(RelationalEntityInformation<T, ID> entity, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
        super(entity, entityOperations, converter);
        this.entityOperations = entityOperations;
        this.entity = entity;

    }

    public BaseRepositoryImpl(RelationalEntityInformation<T, ID> entity, R2dbcEntityOperations entityOperations, R2dbcConverter converter, R2dbcEntityOperations entityOperations1) {
        super(entity, entityOperations, converter);
        this.entityOperations = entityOperations1;
        this.entity = entity;
    }

//    public BaseRepositoryImpl(RelationalEntityInformation<T, ID> entity, R2dbcEntityOperations entityOperations, R2dbcConverter converter) {
//        super(entity, entityOperations, converter);
//        this.entityOperations = entityOperations;
//        this.entity=entity;
//    }

    @Override
    public <S extends T> Mono<S> save(S objectToSave) {
        Mono<S> insert = entityOperations.insert(objectToSave);
        return insert;
    }

    public <S extends T> Mono<S> update(S objectToSave) {
        return entityOperations.update(objectToSave);
    }



    public Flux<List<T>> Page(Query query) {
        Flux<T> select = entityOperations.select(query, this.entity.getJavaType());
        Flux<List<T>> buffer = select.buffer();
        return buffer;
    }
}
