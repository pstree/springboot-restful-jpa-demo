package com.example.demojpa;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> ,QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {
    @Override
    default void customize(QuerydslBindings querydslBindings, QUser qUser){
        querydslBindings.bind(qUser.username).first((path, value) -> path.contains(value));
    };

//    @Override
//    Page<User> findAll(Predicate predicate, Pageable pageable);
}
