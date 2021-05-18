package com.serviceManagementAPI.Exception;

public class ResourceBadException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public ResourceBadException(String message) {
        super(message);
    }
}
