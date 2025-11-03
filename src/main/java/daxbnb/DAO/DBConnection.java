package daxbnb.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections to a MySQL database.
 * It provides methods to establish a new connection and to close an existing one.
 * Connection parameters such as URL, user, password, and driver class name
 * are defined as constants within this class.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class DBConnection {

    /**
     * The JDBC URL for connecting to the DAXBNB MySQL database.
     * Includes the hostname (localhost), port (3308), and database name (DAXBNB).
     */
    public static final String URL_DB = "jdbc:mysql://localhost:3308/DAXBNB";

    /**
     * The username for authenticating with the MySQL database.
     */
    public static final String USER = "root";

    /**
     * The password for authenticating with the MySQL database.
     * Note: Storing passwords directly in code is generally discouraged for production environments.
     * Consider using environment variables or a configuration file.
     */
    public static final String PASSWORD = "1234"; // Consider security implications

    /**
     * The fully qualified name of the MySQL JDBC driver class.
     */
    public static final String CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * Establishes a new connection to the MySQL database using the predefined
     * connection parameters (URL, user, password, and driver class).
     * <p>
     * This method first loads the JDBC driver using {@code Class.forName()} and then
     * attempts to get a connection via {@code DriverManager.getConnection()}.
     * </p>
     *
     * @return A {@link Connection} object representing the established database connection.
     * @throws ClassNotFoundException if the MySQL JDBC driver class cannot be found
     *                                on the classpath.
     * @throws SQLException if a database access error occurs while attempting to connect
     *                      (e.g., incorrect URL, user/password, database not accessible).
     */
    public Connection connect() throws ClassNotFoundException, SQLException {
        System.out.println("Attempting to connect to the database..."); // Changed message for clarity
        Class.forName(CLASS_NAME); // Loads the JDBC driver
        Connection connection = DriverManager.getConnection(URL_DB, USER, PASSWORD);
        // System.out.println("Database connection established successfully."); // Optional success message
        return connection;
    }

    /**
     * Closes the provided database {@link Connection}.
     * It is crucial to close connections after use to free up database resources.
     * <p>
     * This method should typically be called within a {@code finally} block or by using
     * try-with-resources statement for {@code Connection} objects to ensure
     * the connection is closed even if exceptions occur.
     * </p>
     *
     * @param connection The database {@code Connection} object to be closed.
     *                   If the connection is already closed, this method may have no effect
     *                   or could throw an exception depending on the JDBC driver's implementation.
     *                   It's good practice to ensure the connection is not null before calling close.
     * @throws SQLException if a database access error occurs while attempting to close
     *                      the connection.
     */
    public void closeConnection(Connection connection) throws SQLException {
        // System.out.println("Attempting to close database connection..."); // Changed message for clarity
        if (connection != null && !connection.isClosed()) { // Good practice: check if not null and not already closed
            connection.close();
            // System.out.println("Database connection closed successfully."); // Optional success message
        } else {
            // System.out.println("Connection was null or already closed."); // Optional message
        }
    }
}