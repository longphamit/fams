package com.fams.core.configs;

import com.fams.core.entities.AccountEntity;
import com.fams.core.enums.RolesEnum;
import com.fams.core.repositories.AccountManager;
import com.fams.core.repositories.EventManager;
import com.fams.core.repositories.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class DataSeedingConfig implements ApplicationListener<ContextRefreshedEvent> {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AccountManager accountManager;

    @Autowired
    GroupManager groupManager;

    @Autowired
    EventManager eventManager;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Admin account
        if (accountManager.findByEmail("admin@yopmail.com") == null) {
            AccountEntity admin = new AccountEntity();
            admin.setEmail("admin@yopmail.com");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("Goboi123"));
            HashSet<String> roles = new HashSet<>();
            roles.add(RolesEnum.ADMIN.getValue());
            roles.add(RolesEnum.MEMBER.getValue());
            admin.setRoles(roles);
            admin.setEnabled(true);
            accountManager.save(admin);
        }

        // Member account
        if (accountManager.findByEmail("member@yopmail.com") == null) {
            AccountEntity member = new AccountEntity();
            member.setEmail("member@yopmail.com");
            member.setUsername("member");
            member.setEnabled(true);
            member.setPassword(passwordEncoder.encode("Goboi123"));
            HashSet<String> roles = new HashSet<>();
            roles.add(RolesEnum.MEMBER.getValue());
            member.setRoles(roles);
            accountManager.save(member);


            AccountEntity member2 = new AccountEntity();
            member2.setEmail("member2@yopmail.com");
            member2.setUsername("member2");
            member2.setEnabled(true);
            member2.setPassword(passwordEncoder.encode("Goboi123"));
            roles.add(RolesEnum.MEMBER.getValue());
            member2.setRoles(roles);
            accountManager.save(member2);
        }

    }
}
