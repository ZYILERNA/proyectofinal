package daxbnb.Launchers_Dao;

import java.sql.SQLException;
import java.util.List;

// The import for StatementIsClosedException is not used and can be removed.
// import com.mysql.cj.exceptions.StatementIsClosedException;

import daxbnb.DAO.ApartmentDAO;
import daxbnb.model.Apartments;

/**
 * Main class for testing the functionalities of the {@link ApartmentDAO}.
 * This class contains a {@code main} method and several private static methods,
 * each designed to test a specific CRUD (Create, Read, Update, Delete) operation
 * provided by the {@code ApartmentDAO}.
 * <p>
 * To run the tests, uncomment the desired test methods within the {@code main} method.
 * Ensure the database is properly set up and accessible before running these tests.
 * </p>
 *
 * @author Grup_9
 * @version 1.0      
 * @since 2025/05/06
 */
public class ApartmentMain {

    private static ApartmentDAO apartmentDAO = new ApartmentDAO();

    /**
     * The main entry point for testing the {@link ApartmentDAO}.
     * It provides a controlled environment to execute various test methods sequentially.
     * Uncomment the test methods (e.g., {@code testInsert()}, {@code testSelect()})
     * to run specific DAO operations.
     * <p>
     * Handles common exceptions like {@link ClassNotFoundException} and {@link SQLException}
     * that might occur during database operations, printing error messages to standard error.
     * </p>
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        System.out.println("===== APARTMENTS DAO TESTS ====="); // Original: PRUEBAS DE APARTMENTS DAO

        try {
            // Sequential execution of CRUD tests
            testInsert();
           	testSelect();
            testSelectById();
            testUpdate();
            testDelete(); // Currently, only testDelete() is active by default in the provided code.

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
     * Tests the {@link ApartmentDAO#selectAll()} method.
     * Retrieves all apartments from the database and prints them to the console.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testSelect() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Read Test (Select All) -----"); // Original: Prueba de Lectura
        List<Apartments> apartments = apartmentDAO.selectAll();
        apartments.stream().forEach(System.out::println);
    }

    /**
     * Tests the {@link ApartmentDAO#selectById(int)} method.
     * Attempts to retrieve an apartment by a predefined ID and prints its details
     * if found, or a "not found" message otherwise.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testSelectById() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Read by ID Test -----"); // Original: Prueba de Lectura por ID
        int idToSearch = 1; // ID to search for in the database

        Apartments apartment = apartmentDAO.selectById(idToSearch);

        if (apartment != null) {
            System.out.println("Apartment found:"); // Original: Reserva encontrada (should be Apartment)
            System.out.println("  ID: " + apartment.getId());
            System.out.println("  Name: " + apartment.getName());
            System.out.println("  DESCRIPTION: " + apartment.getDescription());
            System.out.println("  LOCATION: " + apartment.getLocation());
            System.out.println("  CAPACITY: " + apartment.getCapacity());
            System.out.println("  PRICE-PER-NIGHT: " + apartment.getPricePerNight());
            System.out.println("  AVAILABILITY: " + apartment.isListedAvailable());
        } else {
            System.out.println("No apartment found with ID: " + idToSearch); // Original: No se encontro ninguna propiedad con ID:
        }
    }

    /**
     * Tests the {@link ApartmentDAO#insert(String, String, String, int, double, boolean)} method.
     * Inserts a new apartment with predefined test data into the database and prints
     * the result of the operation and the data inserted.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testInsert() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Insert Test -----"); // Original: Prueba de Inserción

        // Test data based on UML model (as per original comments)
        String name = "Coastal House"; // Original: Casa en la costa
        String description = "Beautiful coastal house with great lighting"; // Original: Hermosa casa en la costa con gran iluminacion
        String location = "Barcelona";
        int capacity = 5;
        int bedrooms = 5;
        int beds = 5;
        int baths = 5;
        double pricePerNight = 100.00;
        boolean availability = true;

        int result = apartmentDAO.insert(name, description, location, capacity, bedrooms, beds, baths, pricePerNight, availability, null);

        System.out.println("Insertion result: " + result + " row(s) affected"); // Original: Resultado de la inserción: ... fila(s) afectada(s)
        System.out.println("Data inserted: " + // Original: Datos insertados:
                "\nName: " + name +
                "\nDescription: " + description +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\nPricePerNight: " + pricePerNight +
                "\nAvailability: " + availability
                );
    }

    /**
     * Tests the {@link ApartmentDAO#update(int, String, String, String, int, double, boolean)} method.
     * Attempts to update an existing apartment identified by a predefined ID with new
     * test data and prints the result of the operation and the updated data.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testUpdate() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Update Test -----"); // Original: Prueba de Actualizacion

        // Test data based on UML model (as per original comments)
        int idToUpdate = 2; // ID of the apartment to update
        String name = "Coastal House"; // Original: Casa en la costa
        String description = "Beautiful coastal house with great lighting"; // Original: Hermosa casa en la costa con gran iluminacion
        String location = "Barcelona";
        int capacity = 5;
        int bedrooms = 5;
        int beds = 5;
        int baths = 5;
        double pricePerNight = 100.00;
        boolean availability = true;

        int result = apartmentDAO.update(idToUpdate, name, description, location, capacity, bedrooms, beds, baths, pricePerNight, availability, null);

        System.out.println("Update result: " + result + " row(s) affected"); // Original: Resultado de la actualizacion: ... fila(s) afectada(s)
        System.out.println("Data updated for ID " + idToUpdate + ":" + // Adjusted for clarity, original: Datos insertados:
                "\nName: " + name +
                "\nDescription: " + description +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\nPricePerNight: " + pricePerNight +
                "\nAvailability: " + availability
                );
    }

    /**
     * Tests the {@link ApartmentDAO#delete(int)} method.
     * Attempts to delete an apartment from the database using a predefined ID
     * and prints a success or failure message based on the operation's result.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testDelete() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Delete Test -----"); // Original: Prueba de Eliminacion

        // ID to delete the selected apartment
        int idToDelete = 2;

        int result = apartmentDAO.delete(idToDelete);

        if (result > 0 ) {
            System.out.println("Deletion successful: "+ result + " row(s) affected"); // Original: Elimacion exitosa: ... filas afectadas
            System.out.println("Apartment with ID: "+ idToDelete + " successfully deleted"); // Original: Propiedad con id: ... eliminado con exito
        } else {
            System.out.println("Could not delete apartment with ID: " + idToDelete); // Original: No se pudo eliminar la propiedad con id:
        }
    }
}