package com.fams.controller.controllers.impl;

import com.fams.controller.controllers.AccountController;
import com.fams.manager.constants.enums.RolesEnum;
import com.fams.manager.dtos.request.AddAccountRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountControllerImpl implements AccountController {
    private final AccountManager accountManager;

    private final ModelMapper modelMapper;

    @Override
    public GetAccountResponse add(AddAccountRequest addAccountRequest) {
        Assert.isNull(accountManager.findByEmail(addAccountRequest.getEmail()),"Email is existed");
        Assert.isNull(accountManager.findByUsername(addAccountRequest.getUsername()),"Username is existed");
        AccountEntity account=modelMapper.map(addAccountRequest, AccountEntity.class);
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        Set<String> roles= new HashSet<>();
        roles.add(RolesEnum.ADMIN.getValue());
        account.setRoles(roles);
        account.setEnabled(true);
        account.setPassword(bCryptPasswordEncoder.encode(addAccountRequest.getPassword()));
        AccountEntity rs = accountManager.save(account);
        return modelMapper.map(rs, GetAccountResponse.class);
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
