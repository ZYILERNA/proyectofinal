package daxbnb.Launchers_Dao;

import daxbnb.DAO.OffersDAO;
import daxbnb.model.Offers;

import java.sql.SQLException;
// import java.time.LocalDate; // Unused import, kept as in original
import java.util.List;

/**
 * Main class for testing the functionality of the {@link OffersDAO}.
 * This class provides methods to test CRUD (Create, Read, Update, Delete)
 * operations for offer entities.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class OffersMain {

    private static OffersDAO offersDAO = new OffersDAO();

    /**
     * The main entry point for executing tests on the {@link OffersDAO}.
     * This method calls individual test methods for each CRUD operation in sequence.
     * <p>
     * Handles common exceptions such as {@link SQLException} and
     * {@link ClassNotFoundException} that may occur during database interactions.
     * </p>
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("===== OFFERS DAO TESTS ====="); // Original: PRUEBAS DE OFFERS DAO

        try {
            // Sequential execution of CRUD tests
            testCreate();
            testRead();
            testUpdate();
            testDelete();

            System.out.println("===== TESTS COMPLETED ====="); // Original: PRUEBAS COMPLETADAS

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage()); // Original: Error de SQL:
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Database driver not found."); // Original: Error: Driver de base de datos no encontrado.
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage()); // Original: Error inesperado:
            e.printStackTrace();
        }
    }

    /**
     * Tests the insertion of a new offer using the {@link OffersDAO#insert(String)} method.
     * Predefined test data is used for the offer's name. The result of the operation
     * (number of affected rows) and the inserted data are printed to the console.
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testCreate() throws SQLException, ClassNotFoundException { // Method name kept as testCreate
        System.out.println("\n----- Insert Test -----"); // Original: Prueba de Inserción

        // Test data based on UML model (as per original comment, though "URL" seems like a typo for UML)
        String name = "prueba4"; // Test offer name

        // Perform insertion
        int result = offersDAO.insert(name);

        System.out.println("Insertion result: " + result + " row(s) affected"); // Original: Resultado de la inserción: ... fila(s) afectada(s)
        System.out.println("Data inserted: " + // Original: Datos insertados:
                "\nName: " + name
        );
    }

    /**
     * Tests the retrieval of all offers from the database using the
     * {@link OffersDAO#SelectAll()} method.
     * The retrieved offers are then printed to the console. Assumes the
     * {@code Offers#toString()} method provides a suitable string representation.
     * Note: The DAO method is named {@code SelectAll} (uppercase 'S').
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testRead() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Read Test (Select All) -----"); // Added a more descriptive title
        List<Offers> offers = offersDAO.SelectAll(); // Calling SelectAll (with uppercase S)
        offers.stream().forEach(System.out::println);
    }

    /**
     * Tests the update of an existing offer in the database using the
     * {@link OffersDAO#update(int, String)} method.
     * An offer identified by a predefined ID is updated with a new name.
     * The result of the operation and the data used for the update are printed.
     * A message is shown if the ID is not found or no update occurs.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testUpdate() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Update Test -----"); // Original: Prueba de Actualización

        int idToUpdate = 1; // ID for update (Update according to the database). Original: idActualizar
        String name = "Esto es un mensaje de prueba"; // New name for the offer

        int result = offersDAO.update(idToUpdate, name);

        if (result > 0) {
            // This output message is a bit confusing, as it says "Resultado de la inserción" and "Datos insertados"
            // even though it's an update operation. Kept original phrasing for strictness.
            System.out.println("Update result: " + result + " row(s) affected"); // Original: Resultado de la inserción:
            System.out.println("Data updated for Offer ID " + idToUpdate + ":" + // Original: Datos insertados: // Slightly adjusted for context
                    "\nName: " + name
            );
        } else {
            System.out.println("No offer updated. ID not found or no changes made for ID: " + idToUpdate); // Original: No se actualizo ningun usuario. ID no encontrado:
        }
    }

    /**
     * Tests the deletion of an offer from the database using the
     * {@link OffersDAO#delete(int)} method.
     * An offer is deleted using a predefined ID. A success or failure message
     * is printed based on the operation's result.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testDelete() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Delete Test -----"); // Original: Prueba de Eliminacion

        // ID to delete the selected offer
        int idToDelete = 1; // Original: idEliminar

        int result = offersDAO.delete(idToDelete);

        if (result > 0) {
            System.out.println("Deletion successful: " + result + " row(s) affected"); // Original: Elimacion exitosa: ... filas afectadas
            System.out.println("Offer with ID: " + idToDelete + " successfully deleted"); // Original: Oferta con id: ... eliminado con exito
        } else {
            System.out.println("Could not delete offer with ID: " + idToDelete); // Original: No se pudo eliminar la oferta con id:
        }
    }
}