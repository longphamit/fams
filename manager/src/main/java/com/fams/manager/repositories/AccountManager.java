package com.fams.manager.repositories;

import com.fams.manager.entities.AccountEntity;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

@Repository
public class AccountManager extends BaseManager<AccountEntity> {

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
