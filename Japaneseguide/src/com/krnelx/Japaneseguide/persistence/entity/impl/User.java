package com.krnelx.Japaneseguide.persistence.entity.impl;

import com.krnelx.Japaneseguide.persistence.entity.Entity;
import com.krnelx.Japaneseguide.persistence.entity.ErrorTemplates;
import com.krnelx.Japaneseguide.persistence.exception.EntityArgumentException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Represents a User entity with associated properties and validation.
 */
public class User extends Entity {

    private final String password;
    private final LocalDate birthday;
    private String email;
    private String username;

    /**
     * Constructor for the User class.
     *
     * @param id       The UUID identifier for the user.
     * @param password The user's password.
     * @param email    The user's email.
     * @param birthday The user's date of birth.
     * @param username The user's username.
     */
    public User(UUID id, String password, String email, LocalDate birthday, String username) {
        super(id);
        this.password = validatedPassword(password);
        setEmail(email);
        this.birthday = validatedBirthday(birthday);
        setUsername(username);
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's date of birth.
     *
     * @return The user's date of birth.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Gets the user's email.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's username.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's email after validation.
     *
     * @param email The user's email to be set.
     */
    public void setEmail(String email) {
        final String templateName = "email";

        if (email.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        var pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
        if (!pattern.matcher(email).matches()) {
            errors.add(ErrorTemplates.ONLY_LATIN.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.email = email;
    }

    /**
     * Sets the user's username after validation.
     *
     * @param username The user's username to be set.
     */
    public void setUsername(String username) {
        final String templateName = "логіну";

        if (username.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (username.length() < 4) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 4));
        }
        if (username.length() > 24) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 24));
        }
        var pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
        if (pattern.matcher(username).matches()) {
            errors.add(ErrorTemplates.ONLY_LATIN.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        this.username = username;
    }

    /**
     * Validates the user's date of birth.
     *
     * @param birthday The user's date of birth to be validated.
     * @return The validated date of birth.
     */
    public LocalDate validatedBirthday(LocalDate birthday) {
        final String templateName = "дня народження";

        if (password.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }

        var pattern = Pattern.compile("^\\d{4},\\d{2},\\d{2}$");
        if (pattern.matcher(password).matches()) {
            errors.add(ErrorTemplates.PASSWORD.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return birthday;
    }

    /**
     * Validates and returns the user's password.
     *
     * @param password The user's password to be validated.
     * @return The validated password.
     */
    private String validatedPassword(String password) {
        final String templateName = "паролю";

        if (password.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (password.length() < 8) {
            errors.add(ErrorTemplates.MIN_LENGTH.getTemplate().formatted(templateName, 8));
        }
        if (password.length() > 32) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 32));
        }
        var pattern = Pattern.compile("\"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d).+$\"");
        if (pattern.matcher(password).matches()) {
            errors.add(ErrorTemplates.PASSWORD.getTemplate().formatted(templateName, 24));
        }

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }

        return password;
    }

    /**
     * Overrides the default equals method to compare users based on their email.
     *
     * @param o The object to compare with this user.
     * @return True if the users are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    /**
     * Overrides the default toString method to provide a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }

    /**
     * Overrides the default hashCode method to generate a hash code based on the user's email.
     *
     * @return The hash code value for this user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}