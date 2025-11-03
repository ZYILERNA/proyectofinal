package daxbnb.Launchers_Dao;

import java.sql.SQLException;
import daxbnb.DAO.ApartmentsOffersDAO;

// No other specific model imports are needed for this Main class,
// as it directly tests the DAO which handles the association.

/**
 * Main class for testing the functionalities of the {@link ApartmentsOffersDAO}.
 * This class contains a {@code main} method designed to test operations related to
 * associating apartments with offers, primarily the insertion of such associations.
 * <p>
 * To run the tests, ensure the database is properly set up, and that valid
 * apartment and offer IDs exist for testing the association.
 * </p>
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class ApartmentsOffersMain {

    private static ApartmentsOffersDAO apartmentsOffersDAO = new ApartmentsOffersDAO();

    /**
     * The main entry point for testing the {@link ApartmentsOffersDAO}.
     * Currently, it focuses on testing the insertion of an apartment-offer association.
     * <p>
     * Handles common exceptions like {@link ClassNotFoundException} and {@link SQLException}
     * that might occur during database operations, printing error messages to standard error.
     * </p>
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        System.out.println("===== APARTMENTS OFFERS DAO TESTS ====="); // Original: PRUEBAS DE APARTMENTS OFFERS DAO

        try {
            // Sequential execution of tests
            testInsert(); // Currently, only testInsert() is active.

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
     * Tests the {@link ApartmentsOffersDAO#insert(int, int)} method.
     * Attempts to insert a new association between a predefined apartment ID
     * and a predefined offer ID into the database. It then prints the result
     * of the operation and the IDs used for the association.
     * <p>
     * Note: For this test to succeed, an apartment with {@code apartmentId} and an offer
     * with {@code offerId} must exist in their respective tables, and the
     * database schema must allow this association (e.g., foreign key constraints).
     * </p>
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs (e.g., foreign key violation,
     *                      duplicate entry if the association already exists and is unique).
     */
    private static void testInsert() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Insert Association Test -----"); // Added a descriptive title for the test

        // Predefined IDs for testing the association.
        // These IDs should correspond to existing records in the APARTMENTS and OFFERS tables.
        int apartmentId = 1;
        int offerId = 2;

        int result = apartmentsOffersDAO.insert(apartmentId, offerId);

        System.out.println("Insertion result: " + result + " row(s) affected"); // Original: Resultado de la inserción: ... fila(s) afectada(s)
        System.out.println("Data inserted for association: " + // Original: Datos insertados: // Adjusted message
                "\nApartment ID: " + apartmentId + // Corrected typo "Apartments ID" to "Apartment ID"
                "\nOffer ID: " + offerId    // Corrected typo "Offerd ID" to "Offer ID"
        );
    }
}