package com.rosterflex.application.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RevisionDataDTO {

    private Long id;
    private Date revisionMoment;
    private String revisionAuthor;
    private String revisionType;

    private Set<RevisionEditedFieldDTO> editedFields = new HashSet<>();

    public RevisionDataDTO() {
    }

    public RevisionDataDTO(Long id, Date revisionMoment, String revisionAuthor, String revisionType) {
        this.id = id;
        this.revisionMoment = revisionMoment;
        this.revisionAuthor = revisionAuthor;
        this.revisionType = revisionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRevisionMoment() {
        return revisionMoment;
    }

    public void setRevisionMoment(Date revisionMoment) {
        this.revisionMoment = revisionMoment;
    }

    public String getRevisionAuthor() {
        return revisionAuthor;
    }

    public void setRevisionAuthor(String revisionAuthor) {
        this.revisionAuthor = revisionAuthor;
    }

    public String getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(String revisionType) {
        this.revisionType = revisionType;
    }

    public Set<RevisionEditedFieldDTO> getEditedFields() {
        return editedFields;
    }
}
