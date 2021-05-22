package com.springboot.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.mongodb.domain.TodoDTO;

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

}
