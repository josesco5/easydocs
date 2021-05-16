package com.avilapps.easydocs.common.exceptions;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String message, Exception exception) {
        super(message, exception);
    }
}
