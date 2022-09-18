package com.fams.restgateway.apis;

import com.fams.controller.components.JwtTokenProvider;
import com.fams.controller.controllers.AccountController;
import com.fams.controller.controllers.impl.AuthenControllerImpl;
import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.manager.dtos.request.SignInRequest;
import com.fams.manager.dtos.response.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenAPI {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((AccountDetailAuthenModel) authentication.getPrincipal());
        return SignInResponse.builder().jwt(jwt).build();
    }
    @PostMapping("sign-out")
    public void signOut(){

    }
    @PostMapping("sign-up")
    public void signUp(){

    }

}
