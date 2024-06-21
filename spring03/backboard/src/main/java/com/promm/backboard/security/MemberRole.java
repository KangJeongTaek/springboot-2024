package com.promm.backboard.security;

import lombok.Getter;

@Getter
public enum MemberRole {
    // 해당 방식이 존재하고
    // ADMIN("관리자","ROLE_ADMIN"),USER("사용자","ROLE_USER");

    // MemberRole(String key,String value){
    //     this.key =key;
    //     this.value = value;
    // }

    // private String key;
    // private String value;


    ADMIN("ROLE_ADMIN"),USER("ROLE_USER");

    MemberRole(String value){
        this.value = value;
    }

    private String value;
}
