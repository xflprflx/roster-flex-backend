package com.rosterflex.application.dtos;

import com.rosterflex.application.models.TeamRole;

import java.io.Serializable;

public class TeamRoleDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long roleId;
    private Integer quantity;

    public TeamRoleDTO() {
    }

    public TeamRoleDTO(Long roleId, Integer quantity) {
        this.roleId = roleId;
        this.quantity = quantity;
    }

    public TeamRoleDTO(TeamRole teamRole) {
        this.roleId = teamRole.getRole().getId();
        this.quantity = teamRole.getQuantity();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
