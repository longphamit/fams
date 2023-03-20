package com.fams.core.utils;

import com.fams.core.entities.AccountEntity;
import com.fams.core.models.AccountDetailAuthenModel;
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
