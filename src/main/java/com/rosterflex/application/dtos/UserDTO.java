package com.rosterflex.application.dtos;

import com.rosterflex.application.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;

    @Size(min = 5, max = 15, message = "Nome de usuário deve ter entre 5 e 15 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String username;
    private String fullName;

    @Email(message = "E-mail inválido")
    private String email;
    private String imgUrl;

    Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String fullName, String email, String imgUrl) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.imgUrl = user.getImgUrl();
        user.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
