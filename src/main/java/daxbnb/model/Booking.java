/*
* Booking.java 21 abr 2025
*
*
* ©Copyright 2025 Ricardo Luis Avila Bazalar <ricardoavilabazalar@gmail.com>
*
* This is free software, licensed under the GNU General Public License v3.
* See http://www.gnu.org/licenses/gpl.html for more information.
*/
package daxbnb.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
// import java.util.Date; // Unused import
// import java.util.concurrent.TimeUnit; // Unused import

/**
 * Represents a reservation or booking made by a user for a specific apartment in the DaxBNB system.
 * This class encapsulates all the essential details of a booking, including the reserved dates,
 * the user who made the booking, the apartment being booked, the calculated total price,
 * and the current status of the booking (e.g., pending, confirmed, cancelled).
 * It serves as a key entity for managing the booking lifecycle and associated information.
 *
 * @author [Grupo de Trabajo G_9] // Replace with actual author information
 * @version 1.0
 */
public class Booking {

	private int id;
	private LocalDate startDate;
	private LocalDate endDate;
	private User user; // Assuming User is another class in daxbnb.model
	private Apartments apartment; // Assuming Apartments is another class in daxbnb.model
	private double totalPrice;
	private BookingStatus status; // Assuming BookingStatus is an enum

	/**
	 * Constructs a new Booking object, initializing all its properties.
	 * This constructor is used to create a complete representation of a reservation.
	 *
	 * @param id The unique integer identifier for this booking.
	 * @param startDate The {@link LocalDate} representing the start date of the booking period (check-in).
	 * @param endDate The {@link LocalDate} representing the end date of the booking period (check-out). Must be on or after the start date.
	 * @param user The {@link User} object representing the user who made this booking.
	 * @param apartment The {@link Apartments} object representing the apartment that is being booked.
	 * @param totalPrice The calculated total price for this booking. This value is typically calculated based on the duration and price per night, possibly including offers.
	 * @param status The initial {@link BookingStatus} of the booking (e.g., PENDING).
	 */
	public Booking(int id, LocalDate startDate, LocalDate endDate, User user, Apartments apartment, double totalPrice,
			BookingStatus status) {
		// super(); // Redundant call to Object constructor
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
		this.apartment = apartment;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	// Getters and Setters

	/**
	 * Returns the unique integer identifier of the booking.
	 * This ID uniquely identifies this specific reservation within the system.
	 * @return The unique identifier (int) of the booking.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique integer identifier of the booking.
	 * This method should be used cautiously, ideally only during object creation or mapping from storage.
	 * @param id The new unique identifier (int) to set for the booking.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the start date of the booking period (check-in date).
	 * @return The {@link LocalDate} representing the booking start date.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date of the booking period (check-in date).
	 * @param startDate The new {@link LocalDate} to set as the booking start date.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date of the booking period (check-out date).
	 * @return The {@link LocalDate} representing the booking end date.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date of the booking period (check-out date).
	 * The end date should ideally be on or after the start date.
	 * @param endDate The new {@link LocalDate} to set as the booking end date.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns the {@link User} object who made this booking.
	 * @return The {@link User} object associated with this booking.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the {@link User} object who made this booking.
	 * @param user The new {@link User} object to associate with this booking.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the {@link Apartments} object that is being booked.
	 * @return The {@link Apartments} object associated with this booking.
	 */
	public Apartments getApartment() {
		return apartment;
	}

	/**
	 * Sets the {@link Apartments} object that is being booked.
	 * @param apartment The new {@link Apartments} object to associate with this booking.
	 */
	public void setApartment(Apartments apartment) {
		this.apartment = apartment;
	}

	/**
	 * Returns the total price calculated for this booking.
	 * This value represents the final cost of the reservation.
	 * @return The total price (double) of the booking.
	 */
	public double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Sets the total price calculated for this booking.
	 * @param totalPrice The new total price (double) to set for the booking. Should be a non-negative value.
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Returns the current status of the booking.
	 * @return The current {@link BookingStatus} of the booking (e.g., CONFIRMED, CANCELLED, PENDING).
	 */
	public BookingStatus getStatus() {
		return status;
	}

	/**
	 * Sets the current status of the booking.
	 * @param status The new {@link BookingStatus} to set for the booking.
	 */
	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	// Specific Methods

	/**
	 * Calculates the total price for the booking based on the number of nights and the apartment's base price per night.
	 * <p>
	 * The calculation is performed as: {@code (number of nights) * (apartment's base price per night)}.
	 * The number of nights is determined by the number of full days between the {@link #startDate}
	 * and {@link #endDate} (exclusive of the end date itself).
	 * </p>
	 * <p>
	 * Returns {@code 0.0} if the {@link #apartment}, {@link #startDate}, or {@link #endDate} are {@code null},
	 * or if the {@link #endDate} is before the {@link #startDate}.
	 * If the calculated number of nights is 0 (meaning start and end dates are the same day),
	 * it is typically considered a 1-night stay for calculation purposes in this implementation.
	 * </p>
	 * <p>
	 * Note: This calculation uses the base price from {@link Apartments#getPricePerNight()}
	 * and does **NOT** take into account any active offers or discounts associated with the apartment.
	 * If offer logic is needed, it should be handled elsewhere or by calling a method like
	 * {@link Apartments#getCurrentPrice()} before creating/setting the booking's {@link #totalPrice}.
	 * </p>
	 *
	 * @return The calculated total price (double) for the booking duration, or {@code 0.0} if the input data (dates, apartment) is invalid or incomplete for calculation.
	 * @see Apartments#getPricePerNight()
	 * @see #getStartDate()
	 * @see #getEndDate()
	 */
	public double calculatePrice() {
		 if (apartment == null || startDate == null || endDate == null || endDate.isBefore(startDate)) {
             return 0.0; // Return 0.0 for invalid or incomplete data
        }
        // ChronoUnit.DAYS.between counts full days (nights). End date is exclusive.
        long numberOfNights = ChronoUnit.DAYS.between(startDate, endDate);

        // If start and end are the same day, treat as 1 night. If end is before start, handled by check above.
        if (numberOfNights <= 0) {
             numberOfNights = 1; // Assuming a same-day booking means 1 paid night
        }

         // Note: This calculation uses the base price and does not include active offers.
         // If offer logic is required, the total price field should be set externally
         // using logic that calls apartment.getCurrentPrice() or similar.
        return apartment.getPricePerNight() * numberOfNights;
    }

	/**
	 * Attempts to confirm the booking.
	 * The status of the booking is changed to {@link BookingStatus#CONFIRMED},
	 * but only if the current status is {@link BookingStatus#PENDING}.
	 * If the status is already CONFIRMED or has been CANCELLED, calling this method has no effect on the status.
	 * @see BookingStatus#CONFIRMED
	 * @see BookingStatus#PENDING
	 */
	public void confirmBooking() {
		 if (this.status == BookingStatus.PENDING) { // Only confirm if it was pending
             this.status = BookingStatus.CONFIRMED;
        }
    }

	/**
	 * Cancels the booking.
	 * This method changes the status of the booking to {@link BookingStatus#CANCELLED}.
	 * This operation is typically irreversible and signifies that the reservation is no longer active.
	 * The status is set to CANCELLED regardless of its current state (PENDING, CONFIRMED).
	 * @see BookingStatus#CANCELLED
	 */
	public void cancelBooking() {
		this.status = BookingStatus.CANCELLED;
	}

	/**
	 * Returns a string representation of the Booking object.
	 * This string includes the values of all key fields: ID, start date, end date,
	 * user, apartment, total price, and status. It provides a summary of the booking's state.
	 * Note: This implementation might trigger {@code NullPointerException} if {@code user}
	 * or {@code apartment} are {@code null} and their {@code toString()} methods (implicitly called here)
	 * do not handle {@code null} safely.
	 *
	 * @return A String containing the representation of the booking object's state.
	 */
	@Override
	public String toString() {
		return "Booking [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", user=" + user
				+ ", apartment=" + apartment + ", totalPrice=" + totalPrice + ", status=" + status + "]";
	}

}