package daxbnb.Launchers_Dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import daxbnb.DAO.BookingDAO;
import daxbnb.model.Booking;
import daxbnb.model.BookingStatus;
// The import for daxbnb.model.Images is not used and can be removed.
// import daxbnb.model.Images;

/**
 * Main class for testing the functionalities of the {@link BookingDAO}.
 * This class implements tests for the CRUD (Create, Read, Update, Delete)
 * operations related to bookings.
 * <p>
 * To run specific tests, uncomment the desired test methods within the {@code main} method.
 * Ensure that the database is properly configured and accessible, and that any
 * prerequisite data (like existing users and apartments for foreign key constraints)
 * is present before running.
 * </p>
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class BookingMain {

    private static BookingDAO bookingDAO = new BookingDAO();

    /**
     * The main entry point for testing the {@link BookingDAO}.
     * It allows for the sequential execution of various test methods for booking operations.
     * Uncomment test methods as needed to perform specific DAO function tests.
     * <p>
     * Handles common exceptions like {@link ClassNotFoundException} and {@link SQLException},
     * logging errors to standard error.
     * </p>
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("===== BOOKING DAO TESTS ====="); // Original: PRUEBAS DE BOOKING DAO

        try {
            // Sequential execution of CRUD tests
            //testInsert();
            testRead();
            //testReadById();
            //testUpdate();
            //testDelete(); // Currently, only testDelete() is active by default.

            System.out.println("===== TESTS COMPLETED ====="); // Original: PRUEBAS COMPLETADAS

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Database driver not found."); // Original: Error: Driver de base de datos no encontrado.
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage()); // Original: Error de SQL:
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage()); // Original: Error inesperado:
            e.printStackTrace();
        }
    }

    /**
     * Tests the {@link BookingDAO#insert(LocalDate, LocalDate, int, int, double, String)} method.
     * Attempts to insert a new booking into the database using predefined test data.
     * Prints the result of the insertion and the data used.
     * <p>
     * Note: This test requires that a user with {@code idUsuario} and an apartment with
     * {@code idApartamento} exist in the database to satisfy foreign key constraints.
     * </p>
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testInsert() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Insert Test -----"); // Original: Prueba de Inserción

        // Test data based on UML model (as per original comments)
        LocalDate startDate = LocalDate.parse("2025-05-10"); // Original: fechaInicio
        LocalDate endDate = LocalDate.parse("2025-05-15");   // Original: fechaFin
        int userId = 24;         // Refers to an existing User. Original: idUsuario
        int apartmentId = 1;     // Refers to an existing Apartment. Original: idApartamento
        double price = 93202.50; // Corresponds to totalPrice in the UML diagram. Original: precio

        // Uses BookingStatus enum as per UML diagram.
        String status = BookingStatus.CONFIRMED.name(); // Original: estado

        // Perform insertion
        int generatedId = bookingDAO.insert(
                startDate, endDate, userId, apartmentId, price, status); // Changed result to generatedId based on BookingDAO.insert() behavior

        // Assuming BookingDAO.insert now returns the generated ID
        if (generatedId > 0) {
            System.out.println("Insertion successful. New Booking ID: " + generatedId);
        } else {
            System.out.println("Insertion failed or did not return a valid ID.");
        }
        System.out.println("Data attempted for insertion: " + // Original: Datos insertados:
                "\nDates: " + startDate + " to " + endDate + ", " + // Original: Fechas
                "\nUser ID: " + userId + ", " + // Original: Usuario
                "\nApartment ID: " + apartmentId + ", " + // Original: Apartamento
                "\nPrice: " + price + ", " + // Original: Precio
                "\nStatus: " + status); // Original: Estado
    }

    /**
     * Tests the {@link BookingDAO#selectAll()} method.
     * Retrieves all bookings from the database and prints them to the console.
     * Assumes the {@code Booking#toString()} method provides a good representation.
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testRead() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Read Test (Select All) -----"); // Original: Prueba de Lectura
        List<Booking> bookings = bookingDAO.selectAll();
        bookings.stream().forEach(System.out::println);
    }

    /**
     * Tests the {@link BookingDAO#selectById(int)} method.
     * Attempts to retrieve a booking by a predefined ID and prints its details
     * if found, or a "not found" message otherwise.
     * Assumes the {@code Booking#toString()} method provides a good representation.
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testReadById() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Read by ID Test -----"); // Original: Prueba de Lectura por ID

        int idToSearch = 3; // Change this to an ID that exists in your DB. Original: idBuscar

        Booking booking = bookingDAO.selectById(idToSearch);

        if (booking != null) {
            System.out.println("Booking found:"); // Original: Reserva encontrada:
            System.out.println(booking); // Relies on Booking.toString()
        } else {
            System.out.println("No booking found with ID: " + idToSearch); // Original: No se encontró ninguna reserva con ID:
        }
    }


    /**
     * Tests the {@link BookingDAO#update(int, LocalDate, LocalDate, int, int, double, String)} method.
     * Attempts to update an existing booking identified by a predefined ID with new test data.
     * Prints the result of the update operation and the data used for the update.
     * <p>
     * Note: This test requires a booking with {@code idActualizar} to exist. Also,
     * if {@code idUsuario} or {@code idApartamento} are changed, these new IDs must
     * correspond to existing users and apartments.
     * </p>
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testUpdate() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Update Test -----"); // Original: Prueba de Actualización

        int idToUpdate = 7; // Original: idActualizar
        LocalDate startDate = LocalDate.parse("2055-01-11"); // Original: fechaInicio
        LocalDate endDate = LocalDate.parse("2078-01-13");   // Original: fechaFin
        int userId = 24;         // Original: idUsuario
        int apartmentId = 1;     // Original: idApartamento
        double price = 100000000000.00; // Original: precio
        String status = BookingStatus.PENDING.name(); // Original: estado

        int result = bookingDAO.update(idToUpdate, startDate, endDate, userId, apartmentId, price, status);
        System.out.println("Update result: " + result + " row(s) affected"); // Original: Resultado de la actualizacion: ... fila(s) afectada(s)
        System.out.println("Data updated for Booking ID " + idToUpdate + ":" + // Original: Datos insertados: // Adjusted message
                "\nDates: " + startDate + " to " + endDate + ", " +
                "\nUser ID: " + userId + ", " +
                "\nApartment ID: " + apartmentId + ", " +
                "\nPrice: " + price + ", " +
                "\nStatus: " + status);
    }

    /**
     * Tests the {@link BookingDAO#delete(int)} method.
     * Attempts to delete a booking from the database using a predefined ID.
     * Prints a success or failure message based on the operation's result.
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testDelete() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Delete Test -----"); // Original: Prueba de Eliminacion

        // ID to delete the selected booking
        int idToDelete = 8; // Original: idEliminar

        int result = bookingDAO.delete(idToDelete);

        if (result > 0 ) {
            System.out.println("Deletion successful: "+ result + " row(s) affected"); // Original: Elimacion exitosa: ... filas afectadas
            System.out.println("Booking with ID: "+ idToDelete + " successfully deleted"); // Original: Booking con id: ... eliminado con exito
        } else {
            System.out.println("Could not delete booking with ID: " + idToDelete); // Original: No se pudo eliminar el booking con id:
        }
    }
}