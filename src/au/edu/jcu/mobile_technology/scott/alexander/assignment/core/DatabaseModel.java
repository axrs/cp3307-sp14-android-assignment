package au.edu.jcu.mobile_technology.scott.alexander.assignment.core;

/**
 * Abstract representation of a database model with an long based id
 */
public abstract class DatabaseModel {
    private long id = -1;

    /**
     * Gets the database row id
     *
     * @return -1 if not set
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the database row id
     *
     * @param id Row id
     */
    public void setId(long id) {
        if (id >= 0) {
            this.id = id;
        }
    }
}
