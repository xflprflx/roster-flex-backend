package com.rosterflex.application.models;

import com.rosterflex.application.models.pk.UserScheduleDatePK;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user_schedule_date")
public class UserScheduleDate {

    @EmbeddedId
    private UserScheduleDatePK id = new UserScheduleDatePK();

    @ManyToOne
    @JoinColumn(name = "turn_id")
    private Turn turn;

    @OneToMany(mappedBy = "originalSchedule")
    private Set<ChangeTurn> originalScheduleList = new HashSet<>();

    @OneToMany(mappedBy = "proposedSchedule")
    private Set<ChangeTurn> proposedScheduleList = new HashSet<>();

    public UserScheduleDate() {
    }

    public UserScheduleDate(User user, ScheduleDate scheduleDate, Turn turn) {
        id.setUser(user);
        id.setScheduleDate(scheduleDate);
        this.turn = turn;
    }

    public User getUser(){
        return id.getUser();
    }

    public void setUser(User user){
        id.setUser(user);
    }

    public ScheduleDate getScheduleDate(){
        return id.getScheduleDate();
    }

    public void setScheduleDate(ScheduleDate scheduleDate){
        id.setScheduleDate(scheduleDate);
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserScheduleDate that = (UserScheduleDate) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
