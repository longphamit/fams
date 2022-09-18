package com.fams.controller.configs;

import com.fams.manager.constants.enums.RolesEnum;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.repositories.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class DataSeedingConfig implements ApplicationListener<ContextRefreshedEvent> {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AccountManager accountManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Admin account
        if (accountManager.findByEmail("admin@yopmail.com") == null) {
            AccountEntity admin = new AccountEntity();
            admin.setEmail("admin@yopmail.com");
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("Goboi123"));
            HashSet<String> roles = new HashSet<>();
            roles.add(RolesEnum.ADMIN.getValue());
            roles.add(RolesEnum.MEMBER.getValue());
            admin.setRoles(roles);
            admin.setEnabled(true);
            accountManager.add(admin);
        }

        // Member account
        if (accountManager.findByEmail("member@yopmail.com") == null) {
            AccountEntity member = new AccountEntity();
            member.setEmail("member@yopmail.com");
            member.setUserName("member");
            member.setEnabled(true);
            member.setPassword(passwordEncoder.encode("Goboi123"));
            HashSet<String> roles = new HashSet<>();
            roles.add(RolesEnum.MEMBER.getValue());
            member.setRoles(roles);
            accountManager.add(member);
        }
    }
}
