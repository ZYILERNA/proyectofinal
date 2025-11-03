package daxbnb.model;

/**
 * Represents an offer that can be applied to apartments in the DAXBnB platform.
 * <p>
 * Each offer has a unique identifier and a name describing the offer.
 * </p>
 * 
 * 
 * @author Group 9
 * @version 1.0, 21 Apr 2025
 */
public class Offers {

    private int id;
    private String name;

    /**
     * Constructs a new Offer instance with the specified id and name.
     * 
     * @param id the unique identifier of the offer
     * @param name the name of the offer
     */
    public Offers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique identifier of this offer.
     * 
     * @return the offer id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this offer.
     * 
     * @param id the offer id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of this offer.
     * 
     * @return the offer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this offer.
     * 
     * @param name the offer name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the offer.
     * 
     * @return a string containing the id and name of the offer
     */
    @Override
    public String toString() {
        return "Offers [id=" + id + ", name=" + name;
    }
}
