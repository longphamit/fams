package com.fams.core.enums;

public enum RolesEnum {
    MEMBER("ROLE_MEMBER"),
    GUEST("ROLE_GUEST"),
    GROUP_ADMIN("ROLE_GROUP_ADMIN"),
    GROUP_MEMBER("ROLE_GROUP_MEMBER"),
    ADMIN("ROLE_ADMIN");
    String value;
    RolesEnum(String s){
        this.value=s;
    }

    public String getValue() {
        return value;
    }
}
