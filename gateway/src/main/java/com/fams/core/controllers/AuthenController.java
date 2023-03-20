package com.fams.core.controllers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenController extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    UserDetails loadUserByUserId(String id);
}
