package com.rosterflex.application.services.exceptions;

public class DatabaseException extends  RuntimeException {
    private static final long serialVersionUid = 1L;

    public DatabaseException(String msg) {
        super(msg);
    }
}
