package daxbnb.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import daxbnb.model.ApartmentOffers;
import daxbnb.model.ApartmentType;
import daxbnb.model.Apartments;
import daxbnb.model.Images;
import daxbnb.model.Offers;

/**
 * Data Access Object (DAO) for managing CRUD operations for the {@link Apartments} entity.
 * This class handles all database interactions related to apartments.
 *
 * @author Grup_9
 * @version 1.0      
 * @since 2025/05/06
 */
public class ApartmentDAO {

    private DBConnection db;

    /**
     * Constructs an {@code ApartmentDAO} and initializes the database connection utility.
     */
    public ApartmentDAO() {
        db = new DBConnection();
    }

    /**
     * Retrieves all apartments from the 'APARTMENTS' table in the database.
     * For each apartment, this method also fetches its associated images and 
     * apartment-offer relationships.
     *
     * @return A list of {@link Apartments} retrieved from the database.
     *         The list is never {@code null}, but may be empty if no apartments exist.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class cannot be found.
     */
    public List<Apartments> selectAll() throws SQLException, ClassNotFoundException {
        List<Apartments> apartments = new ArrayList<>();
        Connection conn = db.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM APARTMENTS");

        while (rs.next()) {
            int apartmentId = rs.getInt("id");
            ImagesDAO img = new ImagesDAO();
            // 🔽 Cargar imágenes desde ImagesDAO
            List<Images> apartmentImages = img.getImagesByApartmentId(apartmentId);
            // 🔽 Cargar Offers desde ApartmentOffersDAO
            ApartmentsOffersDAO offersDAO = new ApartmentsOffersDAO();
            List<ApartmentOffers> apartmentOffers = offersDAO.getOffersByApartmentId(apartmentId);
            
            // Añadir aqui una query donde sea el join entre offers y ApartmentOffers
            // Esta query nos devuelve una lista de offers que guardaremose en una variable de tipo List<Offers>
            Apartments a = new Apartments(
                apartmentId,
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getInt("capacity"),
                rs.getInt("bedrooms"),
                rs.getInt("beds"),
                rs.getInt("baths"),
                rs.getDouble("pricePerNight"),
                rs.getBoolean("availability"),
                ApartmentType.valueOf(rs.getString("apartmentType").toUpperCase()),
                apartmentImages,    // imágenes
                apartmentOffers   // Offers vacío si no tienes aún
            );
            apartments.add(a);
        }

        rs.close();
        stmt.close();
        db.closeConnection(conn);
        return apartments;
    }

    /**
     * Retrieves a specific apartment from the database by its unique identifier.
     * This method also fetches associated images and apartment-offer relationships.
     *
     * @param id The unique ID of the apartment to retrieve.
     * @return An {@link Apartments} object if an apartment with the given ID is found;
     *         {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public Apartments selectById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM APARTMENTS WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        Apartments a = null;
        if (rs.next()) {
            ImagesDAO imgDAO = new ImagesDAO();
            List<Images> apartmentImages = imgDAO.getImagesByApartmentId(rs.getInt("id"));
            ApartmentsOffersDAO offersDAO = new ApartmentsOffersDAO();
            List<ApartmentOffers> apartmentOffers = offersDAO.getOffersByApartmentId(rs.getInt("id"));
            a = new Apartments(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getInt("capacity"),
                rs.getInt("bedrooms"),
                rs.getInt("beds"),
                rs.getInt("baths"),
                rs.getDouble("pricePerNight"),
                rs.getBoolean("availability"),
                ApartmentType.valueOf(rs.getString("apartmentType").toUpperCase()),
                apartmentImages,    // ✅ imágenes cargadas
                apartmentOffers  // ofertas vacías si no las usas aún
            );
        }

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return a;
    }

    /**
     * Inserts a new apartment into the database.
     *
     * @param name The name of the apartment.
     * @param description A description of the apartment.
     * @param location The location of the apartment.
     * @param capacity The maximum guest capacity of the apartment.
     * @param pricePerNight The price per night for renting the apartment.
     * @param listedAvailable The availability status of the apartment (true if available, false otherwise).
     * @param apartmentType The type of the apartment.
     * @param bedrooms The number of bedrooms.
     * @param beds The number of beds.
     * @param baths The number of bathrooms.
     * @return The ID of the newly created apartment if insertion is successful; -1 otherwise.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int insert(String name, String description, String location, int capacity, int bedrooms, int beds, int baths, double pricePerNight, boolean listedAvailable, ApartmentType apartmentType)
            throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO APARTMENTS (name, description, location, capacity, bedrooms, beds, baths, pricePerNight, availability, apartmentType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
        );
        
        // Ajusta el orden de los parámetros según lo solicitado
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setInt(4, capacity);
        ps.setInt(5, bedrooms);
        ps.setInt(6, beds);
        ps.setInt(7, baths);
        ps.setDouble(8, pricePerNight);
        ps.setBoolean(9, listedAvailable);
        ps.setString(10, apartmentType.name());  // Guardamos como String (e.g., "STUDIO")
        int rows = ps.executeUpdate();

        if(rows == 0 ) {
            throw new SQLException("Creating apartment failed, no rows affected.");
        }
        
        int generatedId = -1;
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            generatedId = generatedKeys.getInt(1); // Només un ID
        }
        
        db.closeConnection(conn);
        return generatedId;
    }


    /**
     * Updates an existing apartment in the database identified by its ID.
     *
     * @param id The unique ID of the apartment to update.
     * @param name The new name of the apartment.
     * @param description The new description of the apartment.
     * @param location The new location of the apartment.
     * @param capacity The new maximum guest capacity of the apartment.
     * @param pricePerNight The new price per night for the apartment.
     * @param listedAvailable The new availability status of the apartment.
     * @param apartmentType The new type of the apartment.
     * @param bedrooms The new number of bedrooms.
     * @param beds The new number of beds.
     * @param baths The new number of bathrooms.
     * @return The number of rows affected by the update operation (typically 1 if an apartment
     *         with the given ID was found and updated, 0 otherwise).
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int update(int id, String name, String description, String location, int capacity, int bedrooms, int beds, int baths, double pricePerNight, boolean listedAvailable, ApartmentType apartmentType)
            throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE APARTMENTS SET name = ?, description = ?, location = ?, capacity = ?, pricePerNight = ?, availability = ?, apartmentType = ?, bedrooms = ?, beds = ?, baths = ? WHERE id = ?"
        );
        ps.setString(1, name);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setInt(4, capacity);
        ps.setDouble(5, pricePerNight);
        ps.setBoolean(6, listedAvailable);
        ps.setString(7, apartmentType.name());
        ps.setInt(8, bedrooms);
        ps.setInt(9, beds);
        ps.setInt(10, baths);
        ps.setInt(11, id);
        int rows = ps.executeUpdate();
        ps.close();
        db.closeConnection(conn);
        return rows;
    }

    /**
     * Deletes an apartment from the database by its unique identifier.
     *
     * @param id The unique ID of the apartment to delete.
     * @return The number of rows affected by the delete operation (typically 1 if the apartment
     *         was successfully deleted, or 0 if no such apartment exists).
     * @throws SQLException if a database access error occurs or if the deletion fails.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int delete(int id) throws SQLException, ClassNotFoundException {
        Connection conn = db.connect();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM APARTMENTS WHERE id = ?");
        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        
        if(rows == 0 ) {
            throw new SQLException("Deleting apartment failed, no rows affected.");
        }
        ps.close();
        db.closeConnection(conn);
        return rows;
    }
    
}