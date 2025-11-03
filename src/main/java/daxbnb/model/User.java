package daxbnb.model;

/**
 * Represents a user in the DAXBnB platform.
 * <p>
 * A user can be of different types defined by {@link UserType}, such as ADMIN or CLIENT.
 * This class stores user details including personal information, authentication data, and address.
 * </p>
 * 
 * @author Group 9
 * @version 1.0, 21 Apr 2025
 */
public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private UserType type; // Enum: ADMIN, CLIENT
	private String address;

	/**
	 * Constructs a new User with full details.
	 * 
	 * @param id the unique identifier of the user
	 * @param name the user's full name
	 * @param email the user's email address
	 * @param password the user's password (ideally hashed)
	 * @param type the type of user (ADMIN, CLIENT)
	 * @param address the user's address
	 */
	public User(int id, String name, String email, String password, UserType type, String address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.address = address;
	}

	/**
	 * Constructs a User instance with minimal data for light queries.
	 * 
	 * @param id the unique identifier of the user
	 * @param name the user's name
	 */
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Returns the unique identifier of the user.
	 * 
	 * @return the id of the user
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the user.
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the user.
	 * 
	 * @return the user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the user.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the user's email address.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the user's email address.
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns the user's password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the user's password.
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the user's type (ADMIN or CLIENT).
	 * 
	 * @return the user type
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * Sets the user's type.
	 * 
	 * @param type the user type to set
	 */
	public void setType(UserType type) {
		this.type = type;
	}

	/**
	 * Returns the user's address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the user's address.
	 * 
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns a string representation of the User object.
	 * 
	 * @return string describing the user
	 */
	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + ", type=" + type
				+ ", address='" + address + '\'' + '}';
	}

	/**
	 * Checks if the user is an administrator.
	 * 
	 * @return true if user type is ADMIN, false otherwise
	 */
	public boolean isAdmin() {
		return this.type == UserType.ADMIN;
	}

	/**
	 * Checks if the user is a client.
	 * 
	 * @return true if user type is CLIENT, false otherwise
	 */
	public boolean isClient() {
		return this.type == UserType.CLIENT;
	}
}
