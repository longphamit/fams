package com.fams.manager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountEntity {
    @Id
    private String id;
    @Indexed
    private String email;
    @Indexed
    private String userName;
    private String password;
    private boolean isEnabled;
    private Set<String> roles;
}
