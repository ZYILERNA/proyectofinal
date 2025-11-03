package daxbnb.DAO;

import daxbnb.model.Images;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing data access for the {@link Images} entity.
 * This class handles database interactions for image records.
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class ImagesDAO {

    private DBConnection db;

    /**
     * Constructs an {@code ImagesDAO} and initializes the database connection utility.
     */
    public ImagesDAO() {
        db = new DBConnection();
    }

    /**
     * Retrieves all images from the 'IMAGES' table in the database.
     *
     * @return A {@link List} of {@link Images} objects found in the database.
     *         The list will be empty if no images are found, but never {@code null}.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public List<Images> selectAll() throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        String sentence = "SELECT * FROM IMAGES";
        PreparedStatement ps = connection.prepareStatement(sentence);
        ResultSet resultSet = ps.executeQuery();
        List<Images> imagenes = new ArrayList<>(); // Original variable name kept

        while (resultSet.next()) {
            Images img = new Images(
                    resultSet.getInt("id"),
                    resultSet.getString("url")
                    // Note: The Images constructor used here (id, url, description) does not include apartmentId.
                    // This is consistent if the Images model object itself doesn't store the apartmentId,
                    // and apartmentId is only used for database linking during insert/update.
            );
            imagenes.add(img);
        }

        resultSet.close();
        // Note: ps.close() is not explicitly called here before db.closeConnection().
        // Depending on the JDBC driver, closing the connection might also close associated statements.
        // However, explicit closure of PreparedStatement is a common best practice.
        db.closeConnection(connection);
        return imagenes;
    }

    /**
     * Inserts a new image record into the database and associates it with an apartment.
     *
     * @param url The URL or path to the image.
     * @param apartmentId The ID of the apartment to which this image belongs.
     * @return The auto-generated ID of the newly inserted image if the {@code Statement.RETURN_GENERATED_KEYS}
     *         feature works as expected and a key is retrieved; otherwise, returns -1 if no key is retrieved
     *         (e.g., if {@code generatedKeys.next()} is false after a successful insert).
     * @throws SQLException if a database access error occurs or if the image creation fails (e.g., {@code affectedRows} is 0).
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int insert(String url, int apartmentId) throws SQLException, ClassNotFoundException {
        Connection connection = db.connect();
        String sentence = "INSERT INTO IMAGES (url, apartmentId) VALUES (? , ?)";
        PreparedStatement ps = connection.prepareStatement(sentence, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, url);
        ps.setInt(2, apartmentId);

        int affectedRows = ps.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Error al crear la imagen");
        }

        int generatedId = -1;
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1);
        }

        ps.close();
        db.closeConnection(connection);
        return generatedId;
    }

    /**
     * Updates an existing image record in the database.
     *
     * @param id The unique ID of the image to update.
     * @param url The new URL or path for the image.
     * @param description The new description for the image.
     * @param apartmentId The ID of the apartment associated with this image. This allows
     *                    the image to be re-associated with a different apartment if needed.
     * @return The number of rows affected by the update operation (typically 1 if an image
     *         with the given ID was found and updated, 0 otherwise).
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int update(int id, String url, int apartmentId)
            throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn
                .prepareStatement("UPDATE IMAGES SET url = ?, apartmentId = ? WHERE id = ?");
        ps.setString(1, url);
        ps.setInt(2, apartmentId);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();
        ps.close(); // PreparedStatement is closed here
        db.closeConnection(conn);
        return rows;
    }

    /**
     * Deletes an image from the database by its unique identifier.
     *
     * @param id The unique ID of the image to delete.
     * @return The number of rows affected by the delete operation (typically 1 if an image
     *         with the given ID was found and deleted, 0 otherwise).
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM IMAGES WHERE id = ?");
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        ps.close(); // PreparedStatement is closed here
        db.closeConnection(conn);
        return rows;
    }
    
    /**
     * Retrieves all images associated with a specific apartment by its ID.
     *
     * @param apartmentId The unique ID of the apartment whose images are to be fetched.
     * @return A list of {@link Images} objects related to the specified apartment.
     *         Returns an empty list if no images are found.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public List<Images> getImagesByApartmentId(int apartmentId) throws SQLException, ClassNotFoundException {
        List<Images> images = new ArrayList<>();
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM IMAGES WHERE apartmentId = ?");
        ps.setInt(1, apartmentId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Images img = new Images(rs.getInt("id"), rs.getString("url"));
            images.add(img);
        }

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return images;
    }

}