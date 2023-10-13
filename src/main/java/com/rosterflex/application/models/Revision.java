package com.rosterflex.application.models;

import com.rosterflex.application.listeners.EntityRevisionListener;
import jakarta.persistence.*;
import org.hibernate.envers.*;

import java.util.Date;

@Entity
@RevisionEntity(value = EntityRevisionListener.class)
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_Sequence")
    @SequenceGenerator(name = "revision_Sequence", sequenceName = "REVISION_SEQ")
    @RevisionNumber
    private Long revisionId;

    @RevisionTimestamp
    private Date revisionDate;

    @Column
    private String username;

    @Column
    private RevisionType revisionType;

    public Revision() {
    }

    public Revision(Long revisionId, Date revisionDate, String username) {
        this.revisionId = revisionId;
        this.revisionDate = revisionDate;
        this.username = username;
    }

    public Long getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(Long revisionId) {
        this.revisionId = revisionId;
    }

    public Date getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Date revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RevisionType getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }
}
