package daxbnb.model;

/**
 * Represents the association between an apartment and an offer.
 * <p>
 * This class models the relationship between {@link Apartments} and {@link Offers},
 * where an apartment can have multiple offers applied to it.
 * </p>
 * 
 * @author Group 9
 * @version 1.0, 21 Apr 2025
 */
public class ApartmentOffers {

	private int id;
	private Apartments apartment;
	private Offers offer;

	/**
	 * Constructs a new ApartmentOffers instance with the specified id, apartment, and offer.
	 * 
	 * @param id the unique identifier of this association
	 * @param apartment the apartment involved
	 * @param offer the offer applied to the apartment
	 */
	public ApartmentOffers(int id, Apartments apartment, Offers offer) {
		this.id = id;
		this.apartment = apartment;
		this.offer = offer;
	}
	
	/**
	 * Returns the unique identifier of this apartment-offer association.
	 * 
	 * @return the id of this association
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of this apartment-offer association.
	 * 
	 * @param id the new id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the apartment involved in this association.
	 * 
	 * @return the apartment object
	 */
	public Apartments getApartment() {
		return apartment;
	}

	/**
	 * Sets the apartment involved in this association.
	 * 
	 * @param apartment the apartment to set
	 */
	public void setApartment(Apartments apartment) {
		this.apartment = apartment;
	}

	/**
	 * Returns the offer associated with the apartment.
	 * 
	 * @return the offer object
	 */
	public Offers getOffer() {
		return offer;
	}

	/**
	 * Sets the offer associated with the apartment.
	 * 
	 * @param offer the offer to set
	 */
	public void setOffer(Offers offer) {
		this.offer = offer;
	}

	/**
	 * Returns a string representation of the ApartmentOffers object,
	 * including the id, apartment name, and offer name.
	 * 
	 * @return a string describing this apartment-offer association
	 */
	@Override
	public String toString() {
		return "ApartmentOffers{" + "id=" + id + ", apartment=" + apartment.getName() + ", offer=" + offer.getName()
				+ '}';
	}

}
