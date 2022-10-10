package com.fams.restgateway.apis;

import com.fams.controller.components.JwtTokenProvider;
import com.fams.controller.controllers.AccountController;
import com.fams.controller.exceptions.InvalidSignInException;
import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.manager.dtos.request.AddAccountRequest;
import com.fams.manager.dtos.request.SignInRequest;
import com.fams.manager.dtos.response.GetAccountResponse;
import com.fams.manager.dtos.response.ObjectWrapperResponse;
import com.fams.manager.dtos.response.SignInResponse;
import com.fams.manager.dtos.response.WrapperResponse;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
public class AuthenAPI {
    private AuthenticationManager authenticationManager;

    private AccountManager accountManager;
    private JwtTokenProvider tokenProvider;
    private AccountController accountController;

    @Autowired
    public AuthenAPI(AccountController accountController, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.accountController = accountController;
    }

    @PostMapping("sign-in")
    public WrapperResponse<Object> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getEmail(),
                            signInRequest.getPassword()
                    )
            );

            // Nếu không xảy ra exception tức là thông tin hợp lệ
            // Set thông tin authentication vào Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AccountDetailAuthenModel accountDetail = (AccountDetailAuthenModel) authentication.getPrincipal();
            // Trả về jwt cho người dùng.
            String jwt = tokenProvider.generateToken(accountDetail);

            AccountEntity account = accountDetail.getAccountEntity();
            GetAccountResponse getAccountResponse = GetAccountResponse.builder()
                    .id(account.getId())
                    .email(account.getEmail())
                    .username(account.getUsername())
                    .roles(account.getRoles())
                    .build();

            return ObjectWrapperResponse.builder()
                    .data(SignInResponse.builder()
                            .account(getAccountResponse)
                            .jwt(jwt).build())
                    .message("Login success")
                    .build();
        } catch (BadCredentialsException e) {
            throw new InvalidSignInException();
        }
    }

    @PostMapping("sign-out")
    public void signOut() {

    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody AddAccountRequest addAccountRequest) {
        accountController.add(addAccountRequest);
    }

}
