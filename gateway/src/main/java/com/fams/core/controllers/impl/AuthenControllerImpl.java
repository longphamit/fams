package com.fams.core.controllers.impl;

import com.fams.core.entities.AccountEntity;
import com.fams.core.models.AccountDetailAuthenModel;
import com.fams.core.controllers.AuthenController;
import com.fams.core.repositories.AccountManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenControllerImpl implements AuthenController {

    private final AccountManager accountManager;

    @Override
    @SneakyThrows
    public AccountDetailAuthenModel loadUserByUsername(String username) {
        AccountEntity account = accountManager.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AccountDetailAuthenModel(account);
    }

    public AccountDetailAuthenModel loadUserByUserId(String id) {
        AccountEntity account = accountManager.findById(id);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AccountDetailAuthenModel(account);
    }
}
