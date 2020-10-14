package com.taiji.webfluxcurd.base;

import org.springframework.data.domain.Page;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@NoRepositoryBean
public interface IBaseRepository<D,ID> extends R2dbcRepository<D, ID> {


     <S extends D> Mono<S> update(S objectToSave);

    Flux<List<D>>Page(Query query);

}
