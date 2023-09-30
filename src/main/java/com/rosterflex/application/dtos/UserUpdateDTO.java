package com.rosterflex.application.dtos;

import com.rosterflex.application.models.User;
import com.rosterflex.application.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotNull;

@UserInsertValid
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
