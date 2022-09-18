package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.AccountController;
import com.fams.manager.dtos.request.AddAccountRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountControllerImpl implements AccountController {
    @Autowired
    AccountManager accountManager;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public GetAccountResponse add(AddAccountRequest addAccountRequest) {
        AccountEntity account = accountManager.add(modelMapper.map(addAccountRequest, AccountEntity.class));
        return modelMapper.map(account, GetAccountResponse.class);
    }
    @Override
    public GetAccountResponse findByEmail(String email){
        AccountEntity account = accountManager.findByEmail(email);
        return modelMapper.map(account, GetAccountResponse.class);
    }
    @Override
    public GetAccountResponse findByUsername(String username){
        AccountEntity account = accountManager.findByUsername(username);
        return modelMapper.map(account, GetAccountResponse.class);
    }
}
