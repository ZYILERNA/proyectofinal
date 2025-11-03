package daxbnb.model;

/**
 * Represents an image associated with an apartment or other entities in the system.
 * <p>
 * Each image has a unique identifier and a URL pointing to the image location.
 * </p>
 * 
 * @author Group 9
 * @version 1.0, 21 Apr 2025
 */
public class Images {

	private int id;
	private String url;

	/**
	 * Constructs a new Images instance with the specified id and URL.
	 * 
	 * @param id the unique identifier of the image
	 * @param url the URL of the image
	 */
	public Images(int id, String url) {
		this.id = id;
		this.url = url;
	}

	/**
	 * Returns the unique identifier of the image.
	 * 
	 * @return the id of the image
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the image.
	 * 
	 * @param id the new id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the URL of the image.
	 * 
	 * @return the URL string
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the URL of the image.
	 * 
	 * @param url the new URL string to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Returns a string representation of the Images object,
	 * including the id and URL.
	 * 
	 * @return a string describing this image
	 */
	@Override
	public String toString() {
		return "Images [id=" + id + ", url=" + url + "]";
	}
}
