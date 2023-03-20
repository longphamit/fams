package com.fams.core.controllers;

import com.fams.core.dtos.request.AddAccountRequest;
import com.fams.core.dtos.response.GetAccountResponse;

public interface AccountController {
    GetAccountResponse add(AddAccountRequest addAccountRequest);

    GetAccountResponse findByEmail(String email);

    GetAccountResponse findByUsername(String username);
}
