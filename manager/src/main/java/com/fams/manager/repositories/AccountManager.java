package com.fams.manager.repositories;

import com.fams.manager.entities.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountManager{
    AccountEntity add(AccountEntity account);
    AccountEntity findByEmail(String email);
    AccountEntity findByUsername(String username);

    AccountEntity findById(String id);
}
