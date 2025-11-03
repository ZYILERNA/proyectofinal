package daxbnb.model;

/**
 * Represents the possible status states for a booking or reservation in the DaxBNB system.
 * <p>
 * This enum defines the distinct stages a booking can be in throughout its lifecycle,
 * from initial creation to final confirmation or cancellation.
 * </p>
 *
 * @author [Grupo de Trabajo G_9] // Replace with actual author information
 * @version 1.0
 * @see Booking
 */
public enum BookingStatus {

	/**
	 * The booking has been initiated but is awaiting confirmation.
	 * It is not yet finalized and could be subject to cancellation or further processing.
	 */
	PENDING,

	/**
	 * The booking has been confirmed and is active.
	 * This signifies that the reservation is finalized and the apartment is reserved for the specified dates.
	 */
	CONFIRMED,

	/**
	 * The booking has been cancelled.
	 * This signifies that the reservation is no longer valid and the apartment is not reserved for the specified dates under this booking.
	 */
	CANCELLED;
}
