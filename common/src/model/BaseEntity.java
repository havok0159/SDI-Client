package model;

/**
 * BaseEntity class, for encapsulating the objects that require an ID
 * within the application
 *
 * @param <ID> the generic type of the entity ID
 */
public class BaseEntity<ID> {

    /** actual value of the ID associated to the entity */
    private ID id;

    /** getter for the ID */
    public ID getId() {
        return id;
    }

    /** setter for the ID */
    public void setId(ID id) {
        this.id = id;
    }

    /** String conversion for the BaseEntity */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
