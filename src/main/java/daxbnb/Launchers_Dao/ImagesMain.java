package daxbnb.Launchers_Dao;

import daxbnb.DAO.ImagesDAO;
import daxbnb.model.Images;
import java.sql.SQLException;
import java.util.List;

/**
 * Main class for testing the functionality of the {@link ImagesDAO}.
 * It provides methods to test CRUD (Create, Read, Update, Delete) operations
 * for image entities.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class ImagesMain {

    private static ImagesDAO imagesDAO = new ImagesDAO();

    /**
     * The main entry point for executing tests on the {@link ImagesDAO}.
     * This method calls individual test methods for each CRUD operation.
     * <p>
     * Handles common exceptions such as {@link SQLException} and
     * {@link ClassNotFoundException} that may occur during database interactions.
     * </p>
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("===== IMAGES DAO TESTS ====="); // Original: PRUEBAS DE IMAGES DAO

        try {
            // Sequential execution of CRUD tests
            testInsert();
            testSelect();
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
     * Tests the retrieval of all existing images from the database using
     * the {@link ImagesDAO#selectAll()} method.
     * The retrieved images are then printed to the console. Assumes the
     * {@code Images#toString()} method provides a suitable string representation.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testSelect() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Read Test -----"); // Original: Prueba de Lectura
        List<Images> imagenes = imagesDAO.selectAll(); // Original variable name 'imagenes' kept
        imagenes.stream().forEach(System.out::println);
    }

    /**
     * Tests the insertion of a new image into the database using the
     * {@link ImagesDAO#insert(String, String, int)} method.
     * Predefined test data is used for the insertion. The result of the operation
     * and the data inserted are printed to the console.
     * <p>
     * Note: For this test to succeed (particularly regarding foreign key constraints),
     * an apartment with the specified {@code apartmentId} must exist in the database.
     * The return value from {@code imagesDAO.insert} is assumed to be the generated ID or
     * a similar indicator of success as implemented in {@code ImagesDAO}.
     * </p>
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testInsert() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Insert Test -----"); // Original: Prueba de Inserción

        // Test data based on UML model (as per original comments)
        String url = "PRUEBA"; // Test URL
        String description = "PRUEBA"; // Test description
        int apartmentId = 1;    // ID of an existing apartment

        int result = imagesDAO.insert(url, apartmentId);

        // The output message here assumes 'result' from imagesDAO.insert is the generated ID
        // or number of rows affected, consistent with ImagesDAO implementation.
        System.out.println("Insertion result (e.g., generated ID or rows affected): " + result); // Adjusted message slightly for clarity. Original: Resultado de la inserción: ... fila(s) afectada(s)
        System.out.println("Data inserted: " + "\nUrl: " + url + "\nDescription: " + description + ", "
                + "\nApartmentId: " + apartmentId);
    }

    /**
     * Tests the update of an existing image in the database using the
     * {@link ImagesDAO#update(int, String, String, int)} method.
     * An image identified by a predefined ID is updated with new test data.
     * The result of the operation and the data used for the update are printed to the console.
     * <p>
     * Note: An image with {@code idActualizar} must exist for the update to affect any rows.
     * If {@code apartmentId} is changed, it should refer to an existing apartment.
     * </p>
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testUpdate() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Update Test -----"); // Original: Prueba de Actualizacion

        // Test data based on UML model (as per original comments)
        int idToUpdate = 1; // ID of the image to update. Original: idActualizar
        String url = "PRUEBA_UPDATED"; // Updated test URL. Original: PRUEBA
        String description = "PRUEBA_UPDATED"; // Updated test description. Original: PRUEBA
        int apartmentId = 1;    // Apartment ID (can be the same or different existing ID)

        int result = imagesDAO.update(idToUpdate, url, apartmentId);

        System.out.println("Update result: " + result + " row(s) affected"); // Original: Resultado de la actualizacion: ... fila(s) afectada(s)
        System.out.println("Data updated for Image ID " + idToUpdate + ":" + // Adjusted message slightly for clarity. Original: Datos insertados:
                "\nUrl: " + url +
                "\nDescription: " + description + ", " +
                "\nApartmentId: " + apartmentId);
    }

    /**
     * Tests the deletion of an image from the database using the
     * {@link ImagesDAO#delete(int)} method.
     * An image is deleted using a predefined ID. A success or failure message
     * is printed based on the operation's result.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testDelete() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Delete Test -----"); // Original: Prueba de Eliminacion

        // ID to delete the selected image
        int idToDelete = 4; // Original: idEliminar

        int result = imagesDAO.delete(idToDelete);

        if (result > 0) {
            System.out.println("Deletion successful: " + result + " row(s) affected"); // Original: Elimacion exitosa: ... filas afectadas
            System.out.println("Image with ID: " + idToDelete + " successfully deleted"); // Original: Imagen con id: ... eliminado con exito
        } else {
            System.out.println("Could not delete image with ID: " + idToDelete); // Original: No se pudo eliminar la imagen con id:
        }
    }
}