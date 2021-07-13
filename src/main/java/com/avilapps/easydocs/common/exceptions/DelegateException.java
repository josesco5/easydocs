package com.avilapps.easydocs.common.exceptions;

public class DelegateException extends RuntimeException {
    public DelegateException(String message, Exception exception) {
        super(message, exception);
    }
}
