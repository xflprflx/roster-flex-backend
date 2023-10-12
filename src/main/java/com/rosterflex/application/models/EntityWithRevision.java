package com.rosterflex.application.models;

public class EntityWithRevision<T> {

    private Revision revision;
    private T entity;

    public EntityWithRevision(Revision revision, T entity) {
        this.revision = revision;
        this.entity = entity;
    }

    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
