package com.fams.controller.configs;

import com.fams.manager.constants.enums.EventStatusEnum;
import com.fams.manager.constants.enums.EventTypeEnum;
import com.fams.manager.constants.enums.RolesEnum;
import com.fams.manager.entities.AccountEntity;
import com.fams.manager.entities.EventEntity;
import com.fams.manager.entities.GroupEntity;
import com.fams.manager.repositories.AccountManager;
import com.fams.manager.repositories.EventManager;
import com.fams.manager.repositories.GroupManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
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
            admin.setUserName("admin");
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
            member.setUserName("member");
            member.setEnabled(true);
            member.setPassword(passwordEncoder.encode("Goboi123"));
            HashSet<String> roles = new HashSet<>();
            roles.add(RolesEnum.MEMBER.getValue());
            member.setRoles(roles);
            accountManager.save(member);


            AccountEntity member2 = new AccountEntity();
            member2.setEmail("member2@yopmail.com");
            member2.setUserName("member2");
            member2.setEnabled(true);
            member2.setPassword(passwordEncoder.encode("Goboi123"));
            roles.add(RolesEnum.MEMBER.getValue());
            member2.setRoles(roles);
            accountManager.save(member2);

            // Group
            List<String> members = new ArrayList<>();
            members.add(member.getId());
            members.add(member2.getId());
            GroupEntity groupEntity = GroupEntity.builder()
                    .admin(member.getId())
                    .members(members)
                    .name("abc")
                    .build();
            groupManager.save(groupEntity);

            // Event
            EventEntity eventEntity= EventEntity.builder()
                    .creator(member.getId())
                    .fee(new BigDecimal(20000))
                    .fromDate(Calendar.getInstance().getTime())
                    .type(EventTypeEnum.BET.name())
                    .status(EventStatusEnum.PROCESSING.name())
                    .members(Arrays.asList(member.getId()))
                    .groupId(groupEntity.getId())
                    .name("MU vs VN")
                    .build();
            eventManager.save(eventEntity);
            EventEntity eventEntity2= EventEntity.builder()
                    .creator(member2.getId())
                    .fee(new BigDecimal(20000))
                    .fromDate(Calendar.getInstance().getTime())
                    .type(EventTypeEnum.BET.name())
                    .status(EventStatusEnum.PROCESSING.name())
                    .members(Arrays.asList(member2.getId()))
                    .groupId(groupEntity.getId())
                    .name("MU vs ARS")
                    .build();
            eventManager.save(eventEntity2);
        }

    }
}
