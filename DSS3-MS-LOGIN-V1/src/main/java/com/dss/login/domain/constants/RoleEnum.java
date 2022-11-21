package com.dss.login.domain.constants;

import lombok.Getter;

@Getter
public enum RoleEnum {

    ADMIN(1, "Admin"),
    USER(0, "User")
    ;

    private Integer intValue;
    private String value;

    private RoleEnum(Integer intValue, String value){
        this.intValue = intValue;
        this.value = value;
    }
}
