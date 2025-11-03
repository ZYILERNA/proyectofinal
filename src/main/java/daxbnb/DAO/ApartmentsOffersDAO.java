package daxbnb.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import daxbnb.model.Images;
import daxbnb.model.Offers;
import daxbnb.model.ApartmentOffers;
import daxbnb.model.Apartments;

// DBConnection is implicitly used from the same package if not explicitly imported.
// import daxbnb.DAO.DBConnection;

/**
 * Data Access Object (DAO) for managing operations on the 'APARTMENT_OFFERS'
 * association table. This table likely represents a many-to-many relationship
 * between Apartments and Offers.
 * 
 * This class primarily handles the creation and potentially deletion of
 * associations between an apartment and an offer.
 * 
 *
 * @author Grup_9
 * @version 1.0       
 * @since 2025/05/06
 */
public class ApartmentsOffersDAO {

    private DBConnection db;

    /**
     * Constructs an {@code ApartmentsOffersDAO} and initializes the database connection utility.
     */
    public ApartmentsOffersDAO() {
        db = new DBConnection();
    }

    /**
     * Inserts a new association between an apartment and an offer into the
     * 'APARTMENT_OFFERS' table.
     * This method creates a link indicating that a specific offer applies to a
     * specific apartment.
     *
     * @param apartmentId The unique ID of the apartment to associate.
     * @param offerId The unique ID of the offer to associate with the apartment.
     * @return The number of rows affected by the insert operation (typically 1 if successful, 0 otherwise).
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public int insert(int apartmentId, int offerId) throws ClassNotFoundException, SQLException {
        Connection connection = db.connect();
        String sql = "INSERT INTO APARTMENT_OFFERS (apartmentId, offerId) VALUES (?, ?)";
        // Using Statement.RETURN_GENERATED_KEYS might not be necessary here
        // unless APARTMENT_OFFERS has its own auto-generated primary key (e.g., an 'id' for the association itself).
        // If it only has apartmentId and offerId (possibly as a composite primary key),
        // then generated keys are not typically retrieved from this kind of insert.
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, apartmentId);
        preparedStatement.setInt(2, offerId);

        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();
        db.closeConnection(connection);
        return rows;
    }

    /**
     * Retrieves all offer associations for a given apartment ID from the database.
     * 
     * This method performs a join between the {@code APARTMENT_OFFERS} and {@code OFFERS}
     * tables to fetch the details of each offer associated with the specified apartment.
     * It constructs a list of {@link ApartmentOffers} objects, each containing the linked {@link Offers}
     * information. The {@link Apartments} field within {@code ApartmentOffers} is set to {@code null},
     * assuming apartment details are not required in this context.
     *
     * @param apartmentId The unique identifier of the apartment for which associated offers are to be retrieved.
     * @return A list of {@link ApartmentOffers} representing the offers linked to the specified apartment.
     *         The list is never {@code null}; it will be empty if the apartment has no associated offers.
     * @throws SQLException if a database access error occurs.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public List<ApartmentOffers> getOffersByApartmentId(int apartmentId) throws SQLException, ClassNotFoundException {
        List<ApartmentOffers> associatedOffers = new ArrayList<>();
        Connection conn = db.connect();

        String sql = "SELECT O.id, O.name FROM APARTMENT_OFFERS AP " +
                     "LEFT JOIN OFFERS O ON AP.offerId = O.id " +
                     "WHERE AP.apartmentId = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, apartmentId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Offers offer = new Offers(
                rs.getInt("id"),
                rs.getString("name")
            );

            ApartmentOffers apartmentOffer = new ApartmentOffers(apartmentId, null, offer);

            apartmentOffer.setOffer(offer); // asumiendo que tienes un setOffer
            // puedes también setear el apartmentId si tu clase lo permite

            associatedOffers.add(apartmentOffer);

        }

        rs.close();
        ps.close();
        db.closeConnection(conn);
        return associatedOffers;
    }

    /**
     * Deletes all offer associations related to a specific apartment from the 'APARTMENT_OFFERS' table.
     * 
     * This method removes all entries in the association table that link the given apartment
     * to any offers, effectively dissociating all offers from the specified apartment.
     *
     * @param apartmentId The unique identifier of the apartment whose offer associations are to be deleted.
     * @throws SQLException if a database access error occurs during the deletion.
     * @throws ClassNotFoundException if the database driver class is not found.
     */
    public void deleteByApartmentId(int apartmentId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM APARTMENT_OFFERS WHERE apartmentId = ?";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, apartmentId);
            ps.executeUpdate();
        }
    }
}