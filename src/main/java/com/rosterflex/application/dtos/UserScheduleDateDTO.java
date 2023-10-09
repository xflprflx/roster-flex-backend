package com.rosterflex.application.dtos;

import com.rosterflex.application.models.UserScheduleDate;

import java.io.Serializable;

public class UserScheduleDateDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long userId;
    private TurnDTO turn;

    public UserScheduleDateDTO() {
    }

    public UserScheduleDateDTO(Long userId, TurnDTO turn) {
        this.userId = userId;
        this.turn = turn;
    }

    public UserScheduleDateDTO(UserScheduleDate scheduleDate) {
        this.userId = scheduleDate.getUser().getId();
        this.turn = new TurnDTO(scheduleDate.getTurn());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TurnDTO getTurn() {
        return turn;
    }

    public void setTurn(TurnDTO turn) {
        this.turn = turn;
    }
}
