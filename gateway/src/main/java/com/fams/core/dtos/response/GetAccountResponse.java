package com.fams.core.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountResponse {
    private String id;
    private String email;
    private String username;
    private Set<String> roles;
}
