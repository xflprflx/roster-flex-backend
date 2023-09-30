package com.rosterflex.application.dtos;

import com.rosterflex.application.models.User;
import jakarta.validation.constraints.NotNull;

public class UserInsertDTO extends UserDTO {
    private static  final long serialVersionUID =1L;

    @NotNull
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
