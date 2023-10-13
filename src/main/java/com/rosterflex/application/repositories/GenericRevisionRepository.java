package com.rosterflex.application.repositories;

import com.rosterflex.application.models.EntityWithRevision;
import com.rosterflex.application.models.Revision;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.configuration.internal.metadata.RevisionInfoHelper;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class GenericRevisionRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EntityWithRevision<T>> revisionList(Long id, Class<T> type) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        List<Number> idsRevision = auditReader.getRevisions(type, id);
        List<EntityWithRevision<T>> allRevisions = new ArrayList<>();
        Map<Number, Revision> revisions = auditReader.findRevisions(Revision.class, new HashSet<Number>(idsRevision));
        for (Number revisionId : idsRevision){
            T revisionList = auditReader.find(type, id, revisionId);
            Revision revision = revisions.get(revisionId);
            auditReader.findRevisions(Revision.class, new HashSet<Number>(idsRevision));
            allRevisions.add(new EntityWithRevision(new Revision(revision.getRevisionId(), revision.getRevisionDate(), revision.getUsername()), revisionList));
        }
        return allRevisions;
    }

    public RevisionType getRevisionType(Class<T> type, Long id, Number revisionId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        // Verifique se a entidade existe na revisão atual
        T revisionList = auditReader.find(type, id, revisionId);
        if (revisionList != null) {
            return RevisionType.MOD; // Entidade existe, é uma atualização
        } else {
            return RevisionType.DEL; // Entidade não existe, é uma exclusão
        }
    }


}
