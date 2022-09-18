package com.fams.controller.controllers;

import com.fams.manager.dtos.request.AddAccountRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.entities.AccountEntity;

public interface AccountController {
    GetAccountResponse add(AddAccountRequest addAccountRequest);

    GetAccountResponse findByEmail(String email);

    GetAccountResponse findByUsername(String username);
}
