package com.rosterflex.application.services;

import com.rosterflex.application.dtos.RevisionDataDTO;
import com.rosterflex.application.dtos.RevisionEditedFieldDTO;
import com.rosterflex.application.models.EntityWithRevision;
import com.rosterflex.application.repositories.GenericRevisionRepository;
import org.hibernate.envers.RevisionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevisionService<T> {

    @Autowired
    private GenericRevisionRepository genericRevisionRepository;

    public List<EntityWithRevision<T>> getRevisions(Long id, Class<T> type) {
        List<EntityWithRevision<T>> revisions = genericRevisionRepository.revisionList(id, type);
        setRevisionType(revisions, type, id);
        return revisions;
    }

    public List<RevisionDataDTO> getRevisionsWithAttributeComparison(Long id, Class<T> type) {
        List<EntityWithRevision<T>> revisions = getRevisions(id, type);
        List<RevisionDataDTO> revisionDataDTOS = new ArrayList<>();

        for (int i = 0; i < revisions.size(); i++) {
            EntityWithRevision<T> actualRevision = revisions.get(i);
            EntityWithRevision<T> previousRevision = (i != 0) ? revisions.get(i - 1) : null;
            RevisionDataDTO revisionDataDTO = createRevisionDataDTO(actualRevision, previousRevision);
            revisionDataDTOS.add(revisionDataDTO);
        }
        return revisionDataDTOS;
    }

    private RevisionDataDTO createRevisionDataDTO(EntityWithRevision<T> actualRevision, EntityWithRevision<T> previousRevision) {
        RevisionDataDTO revisionDataDTO = new RevisionDataDTO();
        revisionDataDTO.setId(actualRevision.getRevision().getRevisionId());
        revisionDataDTO.setRevisionMoment(actualRevision.getRevision().getRevisionDate());
        revisionDataDTO.setRevisionAuthor(actualRevision.getRevision().getUsername());
        revisionDataDTO.setRevisionType(actualRevision.getRevision().getRevisionType().toString());

        Field[] attributes = actualRevision.getEntity().getClass().getDeclaredFields();
        for (Field attribute : attributes) {
            attribute.setAccessible(true);
            Object oldValue = (previousRevision != null) ? getAttributeValue(attribute, previousRevision) : "";
            Object newValue = getAttributeValue(attribute, actualRevision);

            if (oldValue != null && !oldValue.equals(newValue)) {
                RevisionEditedFieldDTO editedField = createEditedField(attribute, oldValue, newValue);
                revisionDataDTO.getEditedFields().add(editedField);
            }
        }
        return revisionDataDTO;
    }

    private Object getAttributeValue(Field attribute, EntityWithRevision<T> revision) {
        try {
            return (revision != null) ? attribute.get(revision.getEntity()) : null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private RevisionEditedFieldDTO createEditedField(Field attribute, Object oldValue, Object newValue) {
        RevisionEditedFieldDTO editedField = new RevisionEditedFieldDTO();
        editedField.setField(attribute.getName());
        editedField.setOldValue(oldValue.toString());
        editedField.setNewValue(newValue.toString());
        return editedField;
    }


    public void setRevisionType(List<EntityWithRevision<T>> revisions, Class<T> type, Long id) {
        for (EntityWithRevision revision : revisions) {
            if (revision == revisions.get(0)) {
                revision.getRevision().setRevisionType(RevisionType.ADD);
            } else {
                revision.getRevision().setRevisionType(genericRevisionRepository.getRevisionType(revision.getEntity().getClass(), id, revision.getRevision().getRevisionId()));
            }
        }
    }
}
