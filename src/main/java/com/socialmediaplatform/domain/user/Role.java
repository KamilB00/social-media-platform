package com.socialmediaplatform.domain.user;

public enum Role {
    ROLE_ADMIN, ROLE_CLIENT;
    public String getAuthority(){
        return name();
    }

}
