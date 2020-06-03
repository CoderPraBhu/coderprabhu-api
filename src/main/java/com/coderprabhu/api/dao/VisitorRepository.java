package com.coderprabhu.api.dao;

import com.coderprabhu.api.data.Visitor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitorRepository extends MongoRepository<Visitor, String>{
    
}