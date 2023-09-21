package com.rosterflex.application.services.exceptions;

public class ResourceNotFoundException extends  RuntimeException {
    private static final long serialVersionUid = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
