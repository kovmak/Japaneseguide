package com.krnelx.Japaneseguide.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract class representing a generic entity with an associated UUID identifier.
 */
public abstract class Entity {

    /**
     * The unique identifier for the entity.
     */
    protected final UUID id;

    /**
     * List of error messages associated with the entity's validation.
     */
    protected List<String> errors;

    /**
     * Flag indicating whether the entity is considered valid.
     */
    protected boolean isValid;

    /**
     * Constructor for the Entity class.
     *
     * @param id The UUID identifier for the entity.
     */
    protected Entity(UUID id) {
        errors = new ArrayList<>();
        this.id = id;
    }

    /**
     * Gets the UUID identifier of the entity.
     *
     * @return The UUID identifier.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Checks if the entity is considered valid based on validation errors.
     *
     * @return True if the entity is valid, false otherwise.
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Gets the list of error messages associated with the entity's validation.
     *
     * @return The list of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Overrides the default equals method to compare entities based on their UUID.
     *
     * @param o The object to compare with this entity.
     * @return True if the entities are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Overrides the default hashCode method to generate a hash code based on the entity's UUID.
     *
     * @return The hash code value for this entity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
