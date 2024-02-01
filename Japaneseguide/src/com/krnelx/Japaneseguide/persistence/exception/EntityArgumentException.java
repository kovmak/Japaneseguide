package com.krnelx.Japaneseguide.persistence.exception;

import java.util.List;

/**
 * Exception class to represent an exception related to entity argument validation.
 */
public class EntityArgumentException extends IllegalArgumentException {

    /**
     * List of error messages associated with the exception.
     */
    private final List<String> errors;

    /**
     * Constructs a new EntityArgumentException with the specified list of errors.
     *
     * @param errors The list of error messages associated with the exception.
     */
    public EntityArgumentException(List<String> errors) {
        this.errors = errors;
    }

    /**
     * Gets the list of error messages associated with the exception.
     *
     * @return The list of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }
}
