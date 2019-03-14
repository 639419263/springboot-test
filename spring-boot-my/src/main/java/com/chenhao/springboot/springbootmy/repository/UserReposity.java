package com.chenhao.springboot.springbootmy.repository;

import com.chenhao.springboot.springbootmy.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserReposity  extends MongoRepository<User, Long> {
     public User findByName(String name);

    public List<User> findByAgeGreaterThan(int age);

}
