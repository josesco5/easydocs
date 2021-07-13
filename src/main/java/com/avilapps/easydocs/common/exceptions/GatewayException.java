package com.avilapps.easydocs.common.exceptions;

public class GatewayException extends RuntimeException {
    public GatewayException(String message, Exception exception) {
        super(message, exception);
    }
}
