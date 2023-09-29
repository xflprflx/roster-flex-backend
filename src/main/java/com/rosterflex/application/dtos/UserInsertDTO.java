package com.rosterflex.application.dtos;

import com.rosterflex.application.models.User;

public class UserInsertDTO extends UserDTO {
    private static  final long serialVersionUID =1L;

    private String password;

    public UserInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
