package com.zenkou.paymentsystem.database.repository;


import com.querydsl.jpa.impl.JPAQuery;
import com.zenkou.paymentsystem.database.entity.db.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, QuerydslPredicateExecutor<User> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByLoginAndPassword(String login, String password);

    default List<User> findAllByFilter(JPAQuery<User> query) {
        return query.fetch();
    }
}

