package com.zenkou.paymentsystem.database.exception;

public class NotSupportedOperationException extends RuntimeException {
    public NotSupportedOperationException(String message) {
        super(message);
    }
}
