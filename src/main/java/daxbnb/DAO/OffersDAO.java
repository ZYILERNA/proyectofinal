package daxbnb.DAO;

import daxbnb.model.Images;
import daxbnb.model.Offers;
//import daxbnb.utils.DBConnection; // Original import commented out

// Unused imports - kept as they are in the original code
import java.awt.font.LineMetrics;
import java.nio.channels.SelectableChannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.time.LocalDate; // Unused in this specific DAO, kept as in original if present (not visible here)
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing data access for the {@link Offers} entity.
 * This class handles database interactions for offer records.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class OffersDAO {

    private DBConnection db;

    /**
     * Constructs an {@code OffersDAO} and initializes the database connection utility.
     */
    public OffersDAO() {
        db = new DBConnection();
    }

    /**
     * Inserts a new offer into the 'OFFERS' table and returns the generated ID.
     *
     * <p>This method inserts a new record into the {@code OFFERS} table using the provided offer name.
     * It uses {@code Statement.RETURN_GENERATED_KEYS} to retrieve the auto-incremented ID of the new offer.
     * If the insert is successful but no ID is returned, it will return {@code -1}.</p>
     *
     * @param name The name of the offer to be inserted. Must not be {@code null}.
     * @return The auto-generated ID of the newly inserted offer if successful;
     *         {@code -1} if no ID was generated.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     * @throws SQLException if a database access error occurs or if the insert fails.
     */
    public int insert(String name) throws ClassNotFoundException, SQLException {
        Connection connection = db.connect();
        String sql = "INSERT INTO OFFERS(name) VALUES(?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }

    /**
     * Retrieves all offers from the 'OFFERS' table in the database.
     *
     * <p>This method executes a SELECT statement to fetch all records from the {@code OFFERS} table.
     * It returns a list of {@link Offers} objects populated with the retrieved data.</p>
     *
     * @return A {@link List} of {@link Offers} objects found in the database.
     *         Returns an empty list if no offers exist; never {@code null}.
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs during the operation.
     */
    public List<Offers> SelectAll() throws ClassNotFoundException, SQLException { // Method name starts with uppercase 'S'
        Connection connection = db.connect();
        String sql = "SELECT * FROM OFFERS";
        PreparedStatement ps = connection.prepareStatement(sql);
        // Note: The SQL query string 'sql' is passed again to executeQuery, which is typical for Statement,
        // but for PreparedStatement, executeQuery() without arguments is usually called after parameters are set.
        // Since this PreparedStatement has no parameters, executeQuery() or executeQuery(sql) would work.
        ResultSet resultSet = ps.executeQuery(sql); // Or simply ps.executeQuery()
        List<Offers> offers = new ArrayList<>();

        while (resultSet.next()) {
            // The condition `!resultSet.wasNull()` here is unusual.
            // `resultSet.wasNull()` is typically used *after* a getXxx() call to check if the *last read column* was SQL NULL.
            // If it's intended to check the validity of the entire row, `resultSet.next()` itself handles that.
            // Assuming it's a custom check or a misunderstanding of `wasNull()`.
            if (!resultSet.wasNull()) { // This check might not behave as expected.
                Offers offer = new Offers(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                offers.add(offer);
            }
        }
        resultSet.close();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection((java.sql.Connection) connection); // Explicit cast to java.sql.Connection, original comment: "Cierro la conexion"
        return offers;
    }

    /**
     * Updates an existing offer in the database by its unique identifier.
     *
     * <p>This method modifies the name of an existing offer in the {@code OFFERS} table
     * based on the provided offer ID.</p>
     *
     * @param id The ID of the offer to update.
     * @param name The new name to assign to the offer.
     * @return The number of rows affected by the update operation. Returns 1 if the update was
     *         successful, or 0 if no matching record was found.
     * @throws ClassNotFoundException if the database driver class is not found.
     * @throws SQLException if a database access error occurs during the operation.
     */
    public int update(int id, String name) throws ClassNotFoundException, SQLException {
        Connection connection = db.connect();
        String sql = "UPDATE OFFERS SET name = ? WHERE ID = ?"; // Assuming 'ID' is the correct column name (case-sensitive on some DBs)
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }

    /**
     * Deletes an offer from the database by its unique identifier.
     *
     * <p>This method removes a record from the {@code OFFERS} table where the ID matches
     * the provided value.</p>
     *
     * @param id The unique ID of the offer to delete.
     * @return The number of rows affected by the delete operation. Typically 1 if a record was found and deleted,
     *         or 0 if no matching offer was found.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        String sql = "DELETE FROM OFFERS WHERE id = ?"; // Assuming 'id' is the correct column name
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        int result = ps.executeUpdate();
        // Note: PreparedStatement 'ps' is not explicitly closed here before closing the connection.
        db.closeConnection(connection);
        return result;
    }

}