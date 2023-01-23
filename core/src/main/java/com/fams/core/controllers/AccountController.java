package com.fams.core.controllers;

import com.fams.manager.dtos.request.AddAccountRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
public interface AccountController {
    GetAccountResponse add(AddAccountRequest addAccountRequest);

    GetAccountResponse findByEmail(String email);

    GetAccountResponse findByUsername(String username);
}
