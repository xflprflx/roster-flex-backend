package com.rosterflex.application.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosterflex.application.enums.ShiftStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_change_turn")
public class ChangeTurn implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate requestDate;
    private ShiftStatus peerApproval;
    private ShiftStatus managerApproval;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "original_schedule_user", referencedColumnName = "user_id"),
            @JoinColumn(name = "original_schedule_schedule_date", referencedColumnName = "schedule_date_id")
    })
    private UserScheduleDate originalSchedule;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "proposed_schedule_user", referencedColumnName = "user_id"),
            @JoinColumn(name = "proposed_schedule_schedule_date", referencedColumnName = "schedule_date_id")
    })
    private UserScheduleDate proposedSchedule;

    public ChangeTurn() {
    }

    public ChangeTurn(Long id, String message, UserScheduleDate originalSchedule, UserScheduleDate proposedSchedule) {
        this.id = id;
        this.message = message;
        this.originalSchedule = originalSchedule;
        this.proposedSchedule = proposedSchedule;
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
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

    public UserScheduleDate getOriginalSchedule() {
        return originalSchedule;
    }

    public void setOriginalSchedule(UserScheduleDate originalSchedule) {
        this.originalSchedule = originalSchedule;
    }

    public UserScheduleDate getProposedSchedule() {
        return proposedSchedule;
    }

    public void setProposedSchedule(UserScheduleDate proposedSchedule) {
        this.proposedSchedule = proposedSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChangeTurn that = (ChangeTurn) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
