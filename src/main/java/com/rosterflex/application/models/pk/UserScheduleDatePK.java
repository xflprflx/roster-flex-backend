package com.rosterflex.application.models.pk;

import com.rosterflex.application.models.ScheduleDate;
import com.rosterflex.application.models.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserScheduleDatePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_date_id")
    private ScheduleDate scheduleDate;

    public UserScheduleDatePK() {
    }

    public UserScheduleDatePK(User user, ScheduleDate scheduleDate) {
        this.user = user;
        this.scheduleDate = scheduleDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ScheduleDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(ScheduleDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserScheduleDatePK that = (UserScheduleDatePK) o;

        if (!Objects.equals(user, that.user)) return false;
        return Objects.equals(scheduleDate, that.scheduleDate);
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (scheduleDate != null ? scheduleDate.hashCode() : 0);
        return result;
    }
}
