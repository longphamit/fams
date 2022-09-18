package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.AuthenController;
import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenControllerImpl implements AuthenController {
    @Autowired
    AccountManager accountManager;
    @Override
    @SneakyThrows
    public AccountDetailAuthenModel loadUserByUsername(String username) {
        AccountEntity account=accountManager.findByEmail(username);
        if(account==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new AccountDetailAuthenModel(account);
    }
    public AccountDetailAuthenModel loadUserByUserId(String id) {
        AccountEntity account=accountManager.findById(id);
        if(account==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new AccountDetailAuthenModel(account);
    }
}
