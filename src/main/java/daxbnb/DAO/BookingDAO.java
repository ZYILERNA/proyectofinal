package daxbnb.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date; // java.sql.Date is used for PreparedStatement
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate; // java.time.LocalDate is used in the Booking model
import java.util.ArrayList;
import java.util.List;

import daxbnb.model.Apartments;
import daxbnb.model.Booking;
import daxbnb.model.BookingStatus;
import daxbnb.model.Images;
import daxbnb.model.User;

/**
 * Data Access Object (DAO) for managing CRUD operations for the {@link Booking}
 * entity. This class handles all database interactions related to bookings,
 * including fetching associated {@link User} and {@link Apartments} data.
 *
 * @author Grup_9
 * @version 1.0
 * @since 2025/05/06
 */
public class BookingDAO {

	private DBConnection db;
	
	/**
     * Constructs an {@code ApartmentsOffersDAO} and initializes the database connection utility.
     */
	public BookingDAO() {
		db = new DBConnection();
	}

	/**
	 * Retrieves all bookings from the 'BOOKING' table in the database.
	 * For each booking, it also fetches the associated {@link User} and {@link Apartments} details.
	 *
	 * @return A {@link List} of {@link Booking} objects found in the database.
	 *         The list will be empty if no bookings are found, but never {@code null}.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 * @throws SQLException if a database access error occurs, or if an error occurs while fetching associated User or Apartment data.
	 */
	public List<Booking> selectAll() throws ClassNotFoundException, SQLException {
		Connection connection = db.connect();
		String sql = "SELECT * FROM BOOKING"; // Consider using the exact table name if it differs in casing
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();
		List<Booking> bookings = new ArrayList<>();

		// These DAOs are instantiated per call, which can be inefficient.
		// Consider instantiating them once in the constructor or passing them as
		// dependencies.
		UserDAO userDAO = new UserDAO();
		ApartmentDAO apartmentDAO = new ApartmentDAO();

		while (resultSet.next()) {
			int userId = resultSet.getInt("userId");
			int apartmentId = resultSet.getInt("apartmentId");

			User user = userDAO.selectById(userId);
			Apartments apartment = apartmentDAO.selectById(apartmentId);

			// It's good practice to check if user or apartment is null before creating
			// Booking,
			// to handle cases of orphaned bookings or data integrity issues.
			// For now, assuming they will always be found.

			Booking booking = new Booking(resultSet.getInt("id"), resultSet.getDate("startDate").toLocalDate(),
					resultSet.getDate("endDate").toLocalDate(), user, apartment, resultSet.getDouble("totalPrice"),
					BookingStatus.valueOf(resultSet.getString("status").toUpperCase()) // Assumes status in DB matches
																						// enum names
			);

			bookings.add(booking);
		}

		resultSet.close();
		ps.close();
		db.closeConnection(connection);
		return bookings;
	}

	/**
	 * Retrieves a specific booking from the database by its unique identifier.
	 * Also fetches the associated {@link User} and {@link Apartments} details.
	 *
	 * @param id The unique ID of the booking to retrieve.
	 * @return A {@link Booking} object if a booking with the given ID is found;
	 *         {@code null} if no booking matches the given ID.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 * @throws SQLException if a database access error occurs, or if an error occurs while fetching associated User or Apartment data.
	 */
	public Booking selectById(int id) throws ClassNotFoundException, SQLException {
		Connection connection = db.connect();
		String sql = "SELECT * FROM BOOKING WHERE id = ?"; // Case-sensitive table name if applicable
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet resultSet = ps.executeQuery();

		Booking booking = null;
		if (resultSet.next()) {
			UserDAO userDAO = new UserDAO(); // Consider reusability
			ApartmentDAO apartmentDAO = new ApartmentDAO(); // Consider reusability

			int userId = resultSet.getInt("userId");
			int apartmentId = resultSet.getInt("apartmentId");

			User user = userDAO.selectById(userId);
			Apartments apartment = apartmentDAO.selectById(apartmentId);

			// Add null checks for user and apartment for robustness

			booking = new Booking(resultSet.getInt("id"), resultSet.getDate("startDate").toLocalDate(),
					resultSet.getDate("endDate").toLocalDate(), user, apartment, resultSet.getDouble("totalPrice"),
					BookingStatus.valueOf(resultSet.getString("status").toUpperCase()));
		}

		resultSet.close();
		ps.close();
		db.closeConnection(connection);
		return booking;
	}

	/**
	 * Inserts a new booking into the database.
	 *
	 * @param startDate   The start date of the booking.
	 * @param endDate     The end date of the booking.
	 * @param userId      The ID of the {@link User} making the booking.
	 * @param apartmentId The ID of the {@link Apartments} being booked.
	 * @param totalPrice  The total price for the booking.
	 * @param status      The status of the booking (e.g., "CONFIRMED", "PENDING",
	 *                    "CANCELLED"). This string should match one of the
	 *                    {@link BookingStatus} enum values, case-insensitively.
	 * @return The auto-generated ID of the newly created booking if successful.
	 * @throws SQLException           If a database access error occurs or if the
	 *                                insertion fails (e.g., no rows affected or
	 *                                generated ID could not be retrieved).
	 * @throws ClassNotFoundException If the database driver class is not found.
	 */

	public int insert(LocalDate startDate, LocalDate endDate, int userId, int apartmentId, double totalPrice,
			String status) throws SQLException, ClassNotFoundException {
		Connection connection = db.connect();
		String sql = "INSERT INTO BOOKING (startDate, endDate, userId, apartmentId, totalPrice, status) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		ps.setDate(1, Date.valueOf(startDate)); // Converts LocalDate to java.sql.Date
		ps.setDate(2, Date.valueOf(endDate));
		ps.setInt(3, userId);
		ps.setInt(4, apartmentId);
		ps.setDouble(5, totalPrice);
		ps.setString(6, status.toUpperCase()); // Store status consistently (e.g., uppercase)

		int affectedRows = ps.executeUpdate();

		if (affectedRows == 0) {
			// Closing connection before throwing exception might be problematic if
			// connection management relies on it.
			// It's often better to ensure resources are closed in a finally block or with
			// try-with-resources.
			db.closeConnection(connection); // Ensure this happens even on failure if necessary
			throw new SQLException("Creating booking failed, no rows affected.");
		}

		int generatedId = -1; // Default if ID retrieval fails
		try (ResultSet generatedKeys = ps.getGeneratedKeys()) { // Use try-with-resources for ResultSet
			if (generatedKeys.next()) {
				generatedId = generatedKeys.getInt(1);
			} else {
				// This case should ideally not happen if affectedRows > 0 and
				// RETURN_GENERATED_KEYS is supported
				// and the table has an auto-increment ID.
				// Consider logging a warning or throwing a specific exception.
				db.closeConnection(connection); // Ensure this happens
				throw new SQLException("Creating booking succeeded but failed to retrieve generated ID.");
			}
		} finally {
			// Close PreparedStatement and Connection if not using try-with-resources for
			// them.
			// If ps.close() is here, it would be redundant with the outer
			// try-with-resources on generatedKeys
			// but db.closeConnection(connection) might still be needed.
			if (ps != null)
				ps.close(); // Should be in a finally block if connection is not closed inside try
		}

		db.closeConnection(connection); // This should ideally be in a broader finally or handled by try-with-resources
										// for 'connection'
		return generatedId;
	}

	/**
	 * Updates an existing booking in the database identified by its ID.
	 *
	 * @param id          The unique ID of the booking to update.
	 * @param startDate   The new start date for the booking.
	 * @param endDate     The new end date for the booking.
	 * @param userId      The ID of the {@link User} associated with the booking
	 *                    (can be updated if allowed).
	 * @param apartmentId The ID of the {@link Apartments} for the booking (can be
	 *                    updated if allowed).
	 * @param totalPrice  The new total price for the booking.
	 * @param status      The new status for the booking (e.g., "CONFIRMED",
	 *                    "CANCELLED"). Should match a {@link BookingStatus} enum
	 *                    value.
	 * @return The number of rows affected by the update operation (typically 1 if a
	 *         booking with the given ID was found and updated, 0 otherwise).
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 */
	public int update(int id, LocalDate startDate, LocalDate endDate, int userId, int apartmentId, double totalPrice,
			String status) throws SQLException, ClassNotFoundException {

		Connection conn = db.connect();
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE BOOKING SET startDate = ?, endDate = ?, userId = ?, apartmentId = ?, totalPrice = ?, status = ? WHERE id = ?");
		ps.setDate(1, java.sql.Date.valueOf(startDate));
		ps.setDate(2, java.sql.Date.valueOf(endDate));
		ps.setInt(3, userId);
		ps.setInt(4, apartmentId);
		ps.setDouble(5, totalPrice);
		ps.setString(6, status.toUpperCase()); // Store status consistently
		ps.setInt(7, id);

		int rows = ps.executeUpdate();
		ps.close();
		db.closeConnection(conn);
		return rows;
	}

	/**
	 * Deletes a booking from the database by its unique identifier.
	 *
	 * @param id The unique ID of the booking to delete.
	 * @return The number of rows affected by the delete operation (typically 1 if a
	 *         booking with the given ID was found and deleted, 0 otherwise).
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 */
	public int delete(int id) throws SQLException, ClassNotFoundException {
		Connection conn = db.connect();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM BOOKING WHERE id = ?");
		ps.setInt(1, id);

		int rows = ps.executeUpdate();
		ps.close();
		db.closeConnection(conn);
		return rows;
	}

	/**
	 * Inserts a new booking into the database using a stored procedure.
	 *
	 * @param booking The {@link Booking} object containing the booking details to insert.
	 * @return {@code true} if the booking was successfully inserted; {@code false} otherwise.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 */
	public boolean insertBooking(Booking booking) throws SQLException, ClassNotFoundException {
		Connection conn = db.connect();

		CallableStatement cs = conn.prepareCall("{ call InsertBooking(?, ?, ?, ?, ?, ?) }");
		cs.setDate(1, java.sql.Date.valueOf(booking.getStartDate()));
		cs.setDate(2, java.sql.Date.valueOf(booking.getEndDate()));
		cs.setInt(3, booking.getUser().getId());
		cs.setInt(4, booking.getApartment().getId());
		cs.setDouble(5, booking.getTotalPrice());
		cs.setString(6, booking.getStatus().toString());

		boolean hadResults = cs.executeUpdate() > 0;

		cs.close();
		db.closeConnection(conn);

		return hadResults;
	}

	/**
	 * Checks if there are any existing bookings for a given apartment that overlap with the specified date range.
	 *
	 * @param apartmentId The ID of the apartment to check for overlapping bookings.
	 * @param newStart    The proposed start date for the new booking.
	 * @param newEnd      The proposed end date for the new booking.
	 * @return {@code true} if there is at least one overlapping booking; {@code false} otherwise.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 */
	public boolean hasOverlappingBooking(int apartmentId, LocalDate newStart, LocalDate newEnd)
	        throws SQLException, ClassNotFoundException {

	    String sql = "{CALL check_overlapping_booking(?, ?, ?)}";

	    try (Connection conn = db.connect();
	         CallableStatement stmt = conn.prepareCall(sql)) {

	        stmt.setInt(1, apartmentId);
	        stmt.setDate(2, Date.valueOf(newStart));
	        stmt.setDate(3, Date.valueOf(newEnd));

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt("overlap_count"); // Usa el alias del procedimiento
	            return count > 0;
	        }
	    }
	    return false;
	}
	
	/**
	 * Calculates the total booking cost for a given apartment within the specified date range.
	 * This method calls a stored procedure which returns the total as an output parameter.
	 *
	 * @param apartmentId The ID of the apartment for which the total booking cost is calculated.
	 * @param startDate   The start date of the booking period.
	 * @param endDate     The end date of the booking period.
	 * @return The total booking cost as a double.
	 * @throws SQLException           if a database access error occurs.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 */
	public double calculateTotalBooking(int apartmentId, LocalDate startDate, LocalDate endDate)
	        throws SQLException, ClassNotFoundException {

	    String sql = "{CALL calculate_total_booking(?, ?, ?, ?)}";
	    
	    try (Connection conn = db.connect();
	         CallableStatement stmt = conn.prepareCall(sql)) {

	        stmt.setInt(1, apartmentId);
	        stmt.setDate(2, Date.valueOf(startDate));
	        stmt.setDate(3, Date.valueOf(endDate));
	        stmt.registerOutParameter(4, java.sql.Types.DECIMAL);

	        stmt.execute();

	        return stmt.getBigDecimal(4).doubleValue();
	    }
	}

	/**
	 * Retrieves all bookings associated with a specific user ID from the database.
	 * For each booking, the associated {@link User} and {@link Apartments} details
	 * are also fetched.
	 *
	 * @param user_id The unique ID of the user whose bookings are to be retrieved.
	 * @return A {@link List} of {@link Booking} objects corresponding to the given user.
	 *         The list will be empty if no bookings are found, but never {@code null}.
	 * @throws ClassNotFoundException if the database driver class is not found.
	 * @throws SQLException           if a database access error occurs, or if an
	 *                                error occurs while fetching associated User or
	 *                                Apartment data.
	 */
	public List<Booking> selectBookingByUserId(int user_id) throws ClassNotFoundException, SQLException{
    	Connection connection = db.connect();
        String sql = "SELECT * FROM BOOKING WHERE userid = ?"; // Consider using the exact table name if it differs in casing
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, user_id);
        ResultSet resultSet = ps.executeQuery();
        
        List<Booking> bookings = new ArrayList<>();

        UserDAO userDAO = new UserDAO();
        ApartmentDAO apartmentDAO = new ApartmentDAO();

        while (resultSet.next()) {
            int userId = resultSet.getInt("userId");
            int apartmentId = resultSet.getInt("apartmentId");

            User user = userDAO.selectById(userId);
            Apartments apartment = apartmentDAO.selectById(apartmentId);

            Booking booking = new Booking(
                resultSet.getInt("id"),
                resultSet.getDate("startDate").toLocalDate(),
                resultSet.getDate("endDate").toLocalDate(),
                user,
                apartment,
                resultSet.getDouble("totalPrice"),
                BookingStatus.valueOf(resultSet.getString("status").toUpperCase()) // Assumes status in DB matches enum names
            );

            bookings.add(booking);
        }

        resultSet.close();
        ps.close();
        db.closeConnection(connection);
        return bookings;

    }

}