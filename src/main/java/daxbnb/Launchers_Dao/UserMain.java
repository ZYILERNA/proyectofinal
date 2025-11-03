package daxbnb.Launchers_Dao;
import java.sql.SQLException;
import java.util.List;

import daxbnb.DAO.UserDAO;
import daxbnb.model.User;
import daxbnb.model.UserType;

/**
 * Main class for testing the functionality of the {@link UserDAO}.
 * This class provides methods to test CRUD (Create, Read, Update, Delete)
 * operations for user entities.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class UserMain {

    private static UserDAO userDAO = new UserDAO();

    /**
     * The main entry point for executing tests on the {@link UserDAO}.
     * This method calls individual test methods for user operations.
     * Uncomment or comment test methods as needed to run specific tests.
     * <p>
     * Handles common exceptions such as {@link ClassNotFoundException}, {@link SQLException},
     * and other general exceptions that may occur during database interactions.
     * </p>
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("===== USER DAO TESTS ====="); // Original: PRUEBAS DE USER DAO

        try {
            // Sequential execution of CRUD tests
            testInsert();
            testSelect();
            testUpdate();
            testDelete();
            testSelectByID(); // Currently, only testSelectByID() is active by default.

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
     * Tests the insertion of a new user using the {@link UserDAO#insert(String, String, String, UserType, String)} method.
     * Predefined test data is used. The result of the operation and the inserted data are printed.
     * Original Javadoc:
     * "@throws SQLException En caso de error en la consulta (In case of query error)"
     * "@throws ClassNotFoundException Si no se encuentra el driver de BD (If DB driver is not found)"
     *
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    private static void testInsert() throws SQLException, ClassNotFoundException {
        System.out.println("\n----- Insert Test -----"); // Original: Prueba de Inserción

        // Test data based on UML model (as per original comments)
        String name = "Sebastin"; // Test name
        String email = "Email";   // Test email
        String password = "Password"; // Test password
        UserType type = UserType.ADMIN; // Test user type
        String address = "Address"; // Test address

        // Perform insertion
        int result = userDAO.insert(name, email, password, type, address);

        System.out.println("Insertion result: " + result + " row(s) affected"); // Original: Resultado de la inserción: ... fila(s) afectada(s)
        System.out.println("Data inserted: " + // Original: Datos insertados:
                "\nName: " + name +
                "\nEmail: " + email + ", " +
                "\nPassword: " + password + ", " +
                "\nType: " + type + ", " +
                "\nAddress: " + address
        );
    }

    /**
     * Tests the retrieval of all users from the database using the {@link UserDAO#select()} method.
     * The retrieved users are printed to the console. Assumes {@code User#toString()} provides a good representation.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testSelect() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Read Test (Select All) -----"); // Added a more descriptive title
        List<User> users = userDAO.select(); // Calls UserDAO.select()
        users.stream().forEach(System.out::println);
    }

    /**
     * Tests the retrieval of a specific user by their ID using the {@link UserDAO#selectById(int)} method.
     * Details of the found user or a "not found" message are printed.
     * This method currently does not have Javadoc comments in the original code.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testSelectByID() throws ClassNotFoundException, SQLException { // Original name kept: testSelectByID
        System.out.println("\n----- Read by ID Test -----"); // Original: Prueba de Lectura por ID

        int idToSearch = 24; // ID to search for in the database. Original: idBuscar

        User user = userDAO.selectById(idToSearch);

        if (user != null) {
            System.out.println("User found:"); // Original: Reserva encontrada (Should be User found)
            System.out.println("  ID: " + user.getId());
            System.out.println("  NAME: " + user.getName());
            System.out.println("  EMAIL: " + user.getEmail());
            System.out.println("  PASSWORD: " + user.getPassword());
            System.out.println("  TYPE: " + user.getType());
            System.out.println("  ADDRESS: " + user.getAddress());
        } else {
            System.out.println("No user found with ID: " + idToSearch); // Original: No se encontro ninguna propiedad con ID: (Should be user)
        }
    }

    /**
     * Tests the update of an existing user using the {@link UserDAO#update(int, String, String, String, UserType, String)} method.
     * An existing user, identified by ID, is updated with new test data.
     * The result and the data used are printed.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testUpdate() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Update Test -----"); // Original: Prueba de Actualización

        // ID for update (Adjust according to the database). Original: idActualizar
        int idToUpdate = 2;
        String name = "Prueba"; // Test name for update
        String email = "Prueba";   // Test email for update
        String password = "Prueba"; // Test password for update
        UserType type = UserType.ADMIN; // Test user type for update
        String address = "Prueba"; // Test address for update

        int result = userDAO.update(idToUpdate, name, email, password, type, address);

        if (result > 0) {
            // Original message refers to "inserción" (insertion) which is misleading for an update.
            System.out.println("Update result: " + result + " row(s) affected"); // Original: Resultado de la inserción: ... fila(s) afectada(s)
            System.out.println("Data updated for User ID " + idToUpdate + ":" + // Original: Datos insertados: // Adjusted for clarity
                    "\nName: " + name +
                    "\nEmail: " + email + ", " +
                    "\nPassword: " + password + ", " +
                    "\nType: " + type + ", " +
                    "\nAddress: " + address
            );
        } else {
            System.out.println("No user updated. ID not found or no changes made for ID: " + idToUpdate); // Original: No se actualizo ningun usuario. ID no encontrado:
        }
    }

    /**
     * Tests the deletion of a user using the {@link UserDAO#delete(int)} method.
     * A user is deleted using a predefined ID. A success or failure message is printed.
     *
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs.
     */
    private static void testDelete() throws ClassNotFoundException, SQLException {
        System.out.println("\n----- Delete Test -----"); // Original: Prueba de Eliminacion

        // ID to delete the selected user
        int idToDelete = 2; // Original: idEliminar

        int result = userDAO.delete(idToDelete);

        if (result > 0) {
            System.out.println("Deletion successful: " + result + " row(s) affected"); // Original: Elimacion exitosa: ... filas afectadas
            System.out.println("User with ID: " + idToDelete + " successfully deleted"); // Original: Usuario con id: ... eliminado con exito
        } else {
            System.out.println("Could not delete user with ID: " + idToDelete); // Original: No se pudo eleiminar el usuario con id: (Typo "eleiminar" kept)
        }
    }
}