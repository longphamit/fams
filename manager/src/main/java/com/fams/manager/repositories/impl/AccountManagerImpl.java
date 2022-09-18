package com.fams.manager.repositories.impl;

import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AccountManagerImpl implements AccountManager {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public AccountEntity add(AccountEntity account) {
        return mongoTemplate.save(account);
    }

    public AccountEntity findByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, AccountEntity.class);
    }

    public AccountEntity findByUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, AccountEntity.class);
    }
    public AccountEntity findById(String id) {
        return mongoTemplate.findById(id, AccountEntity.class);
    }
}
