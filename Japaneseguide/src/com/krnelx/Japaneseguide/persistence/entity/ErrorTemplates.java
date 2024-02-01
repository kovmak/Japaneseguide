package com.krnelx.Japaneseguide.persistence.entity;

/**
 * Enum that defines error message templates for entity validation.
 */
public enum ErrorTemplates {


    /**
     * Error template for required field validation.
     */
    REQUIRED("Поле %s є обов'язковим до заповнення "),

    /**
     * Error template for minimum length validation.
     */
    MIN_LENGTH("Поле %s не може бути меншим за %d симв "),

    /**
     * Error template for maximum length validation.
     */
    MAX_LENGTH("Поле %s не може бути бiльшим за %d симв "),

    /**
     * Error template for allowing only Latin characters.
     */
    ONLY_LATIN("Поле %s лише латинськi символи та символ @ "),

    /**
     * Error template for password validation.
     */
    PASSWORD("Поле %s латинськi миволи, хочаб одна буква з великоi, одна з малоi та хочаб одна цифра ");

    /**
     * The error message template.
     */
    private String template;

    /**
     * Constructor for ErrorTemplates enum.
     *
     * @param template The error message template.
     */
    ErrorTemplates(String template) {
        this.template = template;
    }

    /**
     * Gets the error message template.
     *
     * @return The error message template.
     */
    public String getTemplate() {
        return template;
    }
}