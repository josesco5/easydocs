package com.avilapps.easydocs.common.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
