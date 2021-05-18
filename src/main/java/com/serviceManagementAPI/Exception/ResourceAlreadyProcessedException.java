package com.serviceManagementAPI.Exception;

public class ResourceAlreadyProcessedException extends RuntimeException {
    public static final long serialVersionUID = 1L;

    public ResourceAlreadyProcessedException(String message) {
        super(message);
    }
}
