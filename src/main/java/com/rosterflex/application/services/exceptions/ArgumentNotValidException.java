package com.rosterflex.application.services.exceptions;

public class ArgumentNotValidException extends  RuntimeException {
    private static final long serialVersionUid = 1L;

    public ArgumentNotValidException(String msg) {
        super(msg);
    }
}
