package com.fams.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindEventParamModel {
    private String accountId;
    private boolean isAdmin;
    private boolean isMember;
    private boolean isCreator;
    private String groupId;
}
