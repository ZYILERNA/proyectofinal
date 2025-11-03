package daxbnb.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection; // Already imported, kept for clarity of use in methods

import daxbnb.model.User;
import daxbnb.model.UserType;

/**
 * Data Access Object (DAO) for managing CRUD operations for the {@link User} entity
 * in the database.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class UserDAO {

    // SQL query constants
    private final String CREATE = "INSERT INTO USER (name, email, password, type, address) VALUES (?, ?, ?, ?, ?)";
    private final String READ = "SELECT * FROM USER";
    private final String READ_ID = "SELECT * FROM USER WHERE id = ?";
    private final String UPDATE = "UPDATE USER SET name = ?, email = ?, password = ?, type = ?, address = ? WHERE id = ?"; // Note: space before id in WHERE
    private final String DELETE = "DELETE FROM USER WHERE id = ?"; // Note: space before id in WHERE

    private DBConnection db;

    /**
     * Constructs a {@code UserDAO} and initializes the database connection utility.
     * Original comment: "Instancion la case DBConnection, encargado de conectar y desconectar la base de datos"
     * (Instantiates the DBConnection class, responsible for connecting and disconnecting the database).
     */
    public UserDAO() {
        db = new DBConnection();
    }

    /**
     * Inserts a new user into the database.
     *
     * <p>This method adds a new user record to the {@code USERS} table using the provided details.
     * The {@link UserType} enum is stored as a string using its {@code name()} representation.</p>
     *
     * @param name The full name of the user.
     * @param email The email address of the user.
     * @param password The password associated with the user (stored as plain text unless hashed before).
     * @param type The type of the user (e.g., ADMIN, CLIENT, GUEST).
     * @param address The physical address of the user.
     * @return The number of rows affected by the insert operation; typically 1 if successful, 0 otherwise.
     * @throws SQLException If a database access error occurs during the insert.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public int insert(String name, String email, String password, UserType type, String address) throws SQLException, ClassNotFoundException {
        // Original comment: "Aqui podriamos verificar si los datos no son nulos"
        // (Here we could verify if the data is not null)
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement(CREATE);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, type.name()); // Converts the enum to its string name (e.g., "ADMIN", "CLIENT", "GUEST")
        ps.setString(5, address);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }

    /**
     * Retrieves all users from the {@code USER} table in the database.
     *
     * <p>Each row in the table is mapped to a {@link User} object and added to a list.
     * The {@code type} column is converted to a {@link UserType} enum value.</p>
     *
     * @return A list of {@link User} objects found in the database.
     *         The list will be empty if no users are found, but never {@code null}.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public List<User> select() throws SQLException, ClassNotFoundException { // Method name `select` kept as is.
        Connection connection = (Connection) db.connect(); // Original comment: "Conecta con la BD" (Connects to the DB)
        PreparedStatement ps = ((java.sql.Connection) connection).prepareStatement(READ);
        // Original comment: "A diferencia de Statement, PreparedStatement incluye parametros (?) y mejora el rendimiento al utilizar el plan de ejcucion"
        // (Unlike Statement, PreparedStatement includes parameters (?) and improves performance by using the execution plan)
        ResultSet resultSet = ps.executeQuery(READ); // Original comment: "ejecuto la consulta" (execute the query)
                                                    // Note: Passing 'READ' again to executeQuery for a PreparedStatement.
                                                    // Usually, for PreparedStatement, executeQuery() without arguments is called.
        List<User> users = new ArrayList<>(); // Original comment: "Creo una lista de usuarios" (Create a list of users)

        while (resultSet.next()) { // Original comment: "Recorre los resulados fila por fila" (Iterate through results row by row)
            // Original comment: "Se asegura de que no se procesen filas nula" (Ensures that null rows are not processed)
            // The condition `!resultSet.wasNull()` here is unusual for row processing.
            // `resultSet.wasNull()` checks if the *last read column* was SQL NULL.
            if (!resultSet.wasNull()) {
                User user = new User(
                        resultSet.getInt("id"), // Original comment: "Por cada fila creo un objeto Son con sus datos (id, name, duration )"
                                                // (For each row, I create a Son object with its data (id, name, duration)) - Note: "Son" seems like a typo for "User".
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        UserType.valueOf(resultSet.getString("type").toUpperCase()), // Converts DB string to UserType enum
                        resultSet.getString("address"));
                users.add(user); // Original comment: "Agrego los objetos a la lista" (Add objects to the list)
            }
        }
        resultSet.close(); // Original comment: "Cierro el resultSet" (Close the resultSet)
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection((java.sql.Connection) connection); // Original comment: "Cierro la conexion" (Close the connection)
        return users; // Original comment: "retorno la lista de usuarios" (return the list of users)
    }

    /**
     * Retrieves a user from the database by their unique identifier.
     *
     * @param id The ID of the user to retrieve.
     * @return A {@link User} object if a user with the given ID exists; {@code null} otherwise.
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public User selectById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement(READ_ID);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    UserType.valueOf(resultSet.getString("type").toUpperCase()),
                    resultSet.getString("address")
            );
        }

        resultSet.close();
        ps.close(); // PreparedStatement is closed here
        db.closeConnection(connection);
        return user;
    }

    /**
     * Updates an existing user in the database, identified by their unique ID.
     *
     * @param id The unique ID of the user to be updated.
     * @param name The new name for the user.
     * @param email The new email address for the user.
     * @param password The new password for the user.
     * @param type The new type for the user (e.g., ADMIN, CLIENT, GUEST), represented as a {@link UserType} enum.
     *             This is stored as the enum's string name in the database.
     * @param address The new address for the user.
     * @return The number of rows affected by the update operation (typically 1 if a user
     *         with the given ID was found and updated, 0 otherwise).
     * @throws SQLException If a database access error occurs during the connection or query execution.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public int update(int id, String name, String email, String password, UserType type, String address) throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement(UPDATE);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, type.name()); // Converts the enum to its string name
        ps.setString(5, address);
        ps.setInt(6, id);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }

    /**
     * Deletes a user from the database by their unique identifier.
     *
     * @param id The unique ID of the user to be deleted.
     * @return The number of rows affected by the delete operation (typically 1 if a user
     *         with the given ID was found and deleted, 0 otherwise).
     * @throws SQLException If a database access error occurs.
     * @throws ClassNotFoundException If the database driver class is not found.
     */
    public int delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement(DELETE);
        ps.setInt(1, id);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }
    
    /**
     * Authenticates a user by their email and password.
     *
     * @param email The email address of the user attempting to log in.
     * @param password The password provided by the user.
     * @return A {@link User} object if authentication is successful; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public User login(String email, String password) throws SQLException, ClassNotFoundException {
        String LOGIN_QUERY = "SELECT * FROM USER WHERE email = ? AND password = ?";
        Connection connection = db.connect();
        PreparedStatement ps = connection.prepareStatement(LOGIN_QUERY);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    UserType.valueOf(rs.getString("type").toUpperCase()),
                    rs.getString("address")
            );
        }

        rs.close();
        ps.close();
        db.closeConnection(connection);
        return user;
    }
}