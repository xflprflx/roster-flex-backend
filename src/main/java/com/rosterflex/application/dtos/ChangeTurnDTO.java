package com.rosterflex.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosterflex.application.enums.ShiftStatus;
import com.rosterflex.application.models.ChangeTurn;

import java.io.Serializable;
import java.time.LocalDate;

public class ChangeTurnDTO implements Serializable {
    private static  final long serialVersionUID =1L;


    private Long id;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    private ShiftStatus peerApproval;
    private ShiftStatus managerApproval;
    private UserDTO userOriginalSchedule;
    private ScheduleDateDTO scheduleDateOriginalSchedule;
    private UserDTO userProposedSchedule;
    private ScheduleDateDTO scheduleDateProposedSchedule;

    public ChangeTurnDTO() {
    }

    public ChangeTurnDTO(Long id, String message, LocalDate requestDate, ShiftStatus peerApproval, ShiftStatus managerApproval, UserDTO userOriginalSchedule, ScheduleDateDTO scheduleDateOriginalSchedule, UserDTO userProposedSchedule, ScheduleDateDTO scheduleDateProposedSchedule) {
        this.id = id;
        this.message = message;
        this.requestDate = requestDate;
        this.peerApproval = peerApproval;
        this.managerApproval = managerApproval;
        this.userOriginalSchedule = userOriginalSchedule;
        this.scheduleDateOriginalSchedule = scheduleDateOriginalSchedule;
        this.userProposedSchedule = userProposedSchedule;
        this.scheduleDateProposedSchedule = scheduleDateProposedSchedule;
    }

    public ChangeTurnDTO(ChangeTurn changeTurn) {
        this.id = changeTurn.getId();
        this.message = changeTurn.getMessage();
        this.requestDate = changeTurn.getRequestDate();
        this.peerApproval = changeTurn.getPeerApproval();
        this.managerApproval = changeTurn.getManagerApproval();
        this.userOriginalSchedule = new UserDTO(changeTurn.getOriginalSchedule().getUser());
        this.scheduleDateOriginalSchedule = new ScheduleDateDTO(changeTurn.getOriginalSchedule().getScheduleDate());
        this.userProposedSchedule = new UserDTO(changeTurn.getProposedSchedule().getUser());
        this.scheduleDateProposedSchedule = new ScheduleDateDTO(changeTurn.getProposedSchedule().getScheduleDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShiftStatus getPeerApproval() {
        return peerApproval;
    }

    public void setPeerApproval(ShiftStatus peerApproval) {
        this.peerApproval = peerApproval;
    }

    public ShiftStatus getManagerApproval() {
        return managerApproval;
    }

    public void setManagerApproval(ShiftStatus managerApproval) {
        this.managerApproval = managerApproval;
    }

    public UserDTO getUserOriginalSchedule() {
        return userOriginalSchedule;
    }

    public void setUserOriginalSchedule(UserDTO userOriginalSchedule) {
        this.userOriginalSchedule = userOriginalSchedule;
    }

    public ScheduleDateDTO getScheduleDateOriginalSchedule() {
        return scheduleDateOriginalSchedule;
    }

    public void setScheduleDateOriginalSchedule(ScheduleDateDTO scheduleDateOriginalSchedule) {
        this.scheduleDateOriginalSchedule = scheduleDateOriginalSchedule;
    }

    public UserDTO getUserProposedSchedule() {
        return userProposedSchedule;
    }

    public void setUserProposedSchedule(UserDTO userProposedSchedule) {
        this.userProposedSchedule = userProposedSchedule;
    }

    public ScheduleDateDTO getScheduleDateProposedSchedule() {
        return scheduleDateProposedSchedule;
    }

    public void setScheduleDateProposedSchedule(ScheduleDateDTO scheduleDateProposedSchedule) {
        this.scheduleDateProposedSchedule = scheduleDateProposedSchedule;
    }
}
