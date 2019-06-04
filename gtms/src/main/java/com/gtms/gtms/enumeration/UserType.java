package com.gtms.gtms.enumeration;

import lombok.Getter;

@Getter
public enum UserType {
    TEACHER(2),STUDENT(1),MANAGE(3);
    private int type;

    UserType(int type){
        this.type = type;
    }
}
