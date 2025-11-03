package daxbnb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single apartment in the DAXBnB platform.
 * <p>
 * This class models an apartment's core attributes such as name, description,
 * location, capacity, amenities, price, availability status, and its type.
 * Additionally, it maintains lists of images associated with the apartment and
 * offers linked to it.
 * </p>
 * 
 */
public class Apartments {

	private int id;
	private String name;
	private String description;
	private String location;
	private int capacity;
	private int bedrooms;
	private int beds;
	private int baths;
	private double pricePerNight;
	private boolean listedAvailable;
	private ApartmentType apartmentType;
	private List<Images> images = new ArrayList<>();
	private List<ApartmentOffers> associatedOffers = new ArrayList<>();

	/**
	 * Constructs a full Apartments object with all fields initialized.
	 * 
	 * @param id               the unique identifier of the apartment
	 * @param name             the name of the apartment
	 * @param description      a textual description of the apartment
	 * @param location         the location of the apartment
	 * @param capacity         maximum guest capacity
	 * @param bedrooms         number of bedrooms
	 * @param beds             number of beds
	 * @param baths            number of bathrooms
	 * @param pricePerNight    cost per night stay
	 * @param listedAvailable  availability status for booking
	 * @param apartmentType    the type of apartment (e.g., studio, suite)
	 * @param images           list of images associated with the apartment
	 * @param associatedOffers list of offers linked to this apartment
	 */
	public Apartments(int id, String name, String description, String location, int capacity, int bedrooms, int beds,
			int baths, double pricePerNight, boolean listedAvailable, ApartmentType apartmentType,
			List<Images> images, List<ApartmentOffers> associatedOffers) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.location = location;
		this.capacity = capacity;
		this.bedrooms = bedrooms;
		this.beds = beds;
		this.baths = baths;
		this.pricePerNight = pricePerNight;
		this.listedAvailable = listedAvailable;
		this.apartmentType = apartmentType;
		this.images = images != null ? images : new ArrayList<>();
		this.associatedOffers = associatedOffers != null ? associatedOffers : new ArrayList<>();
	}
	
	/**
	 * Constructs a minimal Apartments object with only id and name.
	 * Useful for lightweight queries where only basic info is required.
	 * 
	 * @param id   the unique identifier of the apartment
	 * @param name the name of the apartment
	 */
	public Apartments(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns the unique identifier of the apartment.
	 * 
	 * @return the apartment id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the apartment.
	 * 
	 * @param id the new apartment id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the apartment.
	 * 
	 * @return the apartment name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the apartment.
	 * 
	 * @param name the new name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description of the apartment.
	 * 
	 * @return the apartment description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the apartment.
	 * 
	 * @param description the new description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the location of the apartment.
	 * 
	 * @return the apartment location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the apartment.
	 * 
	 * @param location the new location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Returns the maximum guest capacity of the apartment.
	 * 
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Sets the maximum guest capacity of the apartment.
	 * 
	 * @param capacity the new capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Returns the number of bedrooms in the apartment.
	 * 
	 * @return number of bedrooms
	 */
	public int getBedrooms() {
		return bedrooms;
	}

	/**
	 * Sets the number of bedrooms in the apartment.
	 * 
	 * @param bedrooms the new bedroom count to set
	 */
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	/**
	 * Returns the number of beds in the apartment.
	 * 
	 * @return number of beds
	 */
	public int getBeds() {
		return beds;
	}

	/**
	 * Sets the number of beds in the apartment.
	 * 
	 * @param beds the new bed count to set
	 */
	public void setBeds(int beds) {
		this.beds = beds;
	}

	/**
	 * Returns the number of bathrooms in the apartment.
	 * 
	 * @return number of bathrooms
	 */
	public int getBaths() {
		return baths;
	}

	/**
	 * Sets the number of bathrooms in the apartment.
	 * 
	 * @param baths the new bathroom count to set
	 */
	public void setBaths(int baths) {
		this.baths = baths;
	}

	/**
	 * Returns the price per night for renting the apartment.
	 * 
	 * @return price per night
	 */
	public double getPricePerNight() {
		return pricePerNight;
	}

	/**
	 * Sets the price per night for renting the apartment.
	 * 
	 * @param pricePerNight the new price per night to set
	 */
	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	/**
	 * Returns whether the apartment is currently listed as available.
	 * 
	 * @return true if available, false otherwise
	 */
	public boolean isListedAvailable() {
		return listedAvailable;
	}

	/**
	 * Sets the availability status of the apartment listing.
	 * 
	 * @param listedAvailable the new availability status to set
	 */
	public void setListedAvailable(boolean listedAvailable) {
		this.listedAvailable = listedAvailable;
	}

	/**
	 * Returns the type of the apartment.
	 * 
	 * @return the apartment type
	 */
	public ApartmentType getApartmentType() {
		return apartmentType;
	}

	/**
	 * Sets the type of the apartment.
	 * 
	 * @param apartmentType the new apartment type to set
	 */
	public void setApartmentType(ApartmentType apartmentType) {
		this.apartmentType = apartmentType;
	}

	/**
	 * Returns the list of images associated with the apartment.
	 * 
	 * @return list of images
	 */
	public List<Images> getImages() {
		return images;
	}

	/**
	 * Sets the list of images associated with the apartment.
	 * 
	 * @param images the new list of images to set
	 */
	public void setImages(List<Images> images) {
		this.images = images;
	}

	/**
	 * Returns the list of offers associated with the apartment.
	 * 
	 * @return list of apartment offers
	 */
	public List<ApartmentOffers> getAssociatedOffers() {
		return associatedOffers;
	}

	/**
	 * Sets the list of offers associated with the apartment.
	 * 
	 * @param associatedOffers the new list of offers to set
	 */
	public void setAssociatedOffers(List<ApartmentOffers> associatedOffers) {
		this.associatedOffers = associatedOffers;
	}

	/**
	 * Returns a string representation of the apartment, including all its fields.
	 * 
	 * @return a string describing the apartment
	 */
	@Override
	public String toString() {
		return "Apartment [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
				+ ", capacity=" + capacity + ", bedrooms=" + bedrooms + ", beds=" + beds + ", baths=" + baths
				+ ", pricePerNight=" + pricePerNight + ", listedAvailable=" + listedAvailable + ", apartmentType="
				+ apartmentType + ", images=" + images + ", associatedOffers=" + associatedOffers + "]";
	}
}
