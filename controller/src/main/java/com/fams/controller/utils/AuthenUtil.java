package com.fams.controller.utils;

import com.fams.controller.models.AccountDetailAuthenModel;
import com.fams.manager.entities.AccountEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenUtil {
    public static AccountEntity getAccountByAuthenContext(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        AccountDetailAuthenModel accountDetailAuthenModel = (AccountDetailAuthenModel) authentication.getPrincipal();
        return accountDetailAuthenModel.getAccountEntity();
    }
    public static String getAccountIdByAuthenContext(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        AccountDetailAuthenModel accountDetailAuthenModel = (AccountDetailAuthenModel) authentication.getPrincipal();
        return accountDetailAuthenModel.getAccountEntity().getId();
    }
}
