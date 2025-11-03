package daxbnb.loadData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import daxbnb.DAO.ApartmentDAO;
import daxbnb.DAO.ApartmentsOffersDAO;
import daxbnb.DAO.BookingDAO;
import daxbnb.DAO.ImagesDAO;
import daxbnb.DAO.OffersDAO;
import daxbnb.DAO.UserDAO;
import daxbnb.model.ApartmentType;
import daxbnb.model.UserType;

public class loadDemoData {

    public static void main(String[] args) throws IOException {
    	
        insertUsers_File_CSV();
        insertApartments_File_CSV();
        insertOffers_File_CSV(); 
        insertBooking_File_CSV();
        insertImages_File_CSV();
        insertApartmentsOffers_File_CSV(); 
    }

    /**
     * Reads user data from a CSV file and inserts each user into the database.
     * <p>
     * The CSV file is expected to be located at "./files/users.csv" and have the following format:
     * Each line contains user details separated by semicolons (;):
     * <pre>
     * name;email;password;type;address
     * </pre>
     * The first line of the CSV is skipped (assumed to be a header).
     * 
     * @throws IOException if an I/O error occurs reading from the file.
     */
    public static void insertUsers_File_CSV() throws IOException {
    
    	UserDAO userDao = new UserDAO();

        try {
        	
        	FileReader file = new FileReader("./files/users.csv"); // Abro el archivo .txt
			BufferedReader bf = new BufferedReader(file); // El Buffer mejor la eficiencia al momento de leer el archivo
			
			String line = "";
			
			line = bf.readLine(); // Ignoro la primer linea
			line = bf.readLine();
        	
			while(line != null) {
				
				String lineas[] = line.split(";"); // Divido el array por ; 
				
				String name = lineas[0]; 
				String email = lineas[1]; 
				String password = lineas[2]; 
				String type = lineas[3]; // Type Enum UserType
				String address = lineas[4];
				
				// Casteos
				
				UserType userType = UserType.valueOf(type.toUpperCase());
				
				//User prueba = new UserDAO().insert(field1, field2, field3, userType, field5);
				
				userDao.insert(name, email, password, userType, address);
				//usersSelect.add(prueba);
				
				line = bf.readLine();
				
			}
        	           
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to Data Base.");
            e.printStackTrace();
        }
    }
    
    /**
     * Reads apartment data from a CSV file and inserts each apartment into the database.
     * <p>
     * The CSV file is expected at "./files/apartments.csv" with the following columns separated by semicolons (;):
     * <pre>
     * name;description;location;capacity;bedrooms;beds;baths;pricePerNight;availability;apartmentType
     * </pre>
     * The first line is assumed to be a header and is skipped.
     *
     * @throws IOException if an error occurs reading the file.
     */
    public static void insertApartments_File_CSV() throws IOException {
        ApartmentDAO apartmentDao = new ApartmentDAO();

        try {
            FileReader file = new FileReader("./files/apartments.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = bf.readLine(); // Ignora encabezado

            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(";");

                String name = fields[0];
                String description = fields[1];
                String location = fields[2];
                int capacity = Integer.parseInt(fields[3]);
                int bedrooms = Integer.parseInt(fields[4]);
                int beds = Integer.parseInt(fields[5]);
                int baths = Integer.parseInt(fields[6]);
                double pricePerNight = Double.parseDouble(fields[7]);
                boolean availability = Boolean.parseBoolean(fields[8]);
                ApartmentType apartmentType = ApartmentType.valueOf(fields[9].toUpperCase());

                // Llamar al método insert con el orden correcto de parámetros
                apartmentDao.insert(name, description, location, capacity, bedrooms, beds, baths, pricePerNight, availability, apartmentType);
            }

            bf.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }

    /**
     * Reads booking data from a CSV file and inserts each booking into the database.
     * <p>
     * The CSV file is expected at "./files/bookings.csv" with the following columns separated by semicolons (;):
     * <pre>
     * startDate;endDate;userId;apartmentId;totalPrice;status
     * </pre>
     * Dates should be in ISO_LOCAL_DATE format (e.g., 2025-05-26).
     * The first line is assumed to be a header and is skipped.
     *
     * @throws IOException if an error occurs reading the file.
     */
    public static void insertBooking_File_CSV() throws IOException {
        BookingDAO bookingDAO = new BookingDAO();

        try {
            FileReader file = new FileReader("./files/bookings.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = bf.readLine(); // Ignora encabezado

            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(";");
                
                LocalDate startDate = LocalDate.parse(fields[0]);
                LocalDate endDate = LocalDate.parse(fields[1]);
                int userId = Integer.parseInt(fields[2]);
                int apartmentId = Integer.parseInt(fields[3]);
                double totalPrice = Double.parseDouble(fields[4]);
                String status = fields[5];

                bookingDAO.insert(startDate, endDate, userId, apartmentId, totalPrice, status);
            }

            bf.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }
    

	/**
	 * Reads offer data from a CSV file and inserts each offer into the database.
	 * <p>
	 * The CSV file is expected at "./files/offers.csv" with the following format:
	 * <pre>
	 * name
	 * </pre>
	 * The first line is assumed to be a header and is skipped.
	 *
	 * @throws IOException if an error occurs reading the file.
	 */
    public static void insertOffers_File_CSV() throws IOException {
        OffersDAO offersDAO = new OffersDAO();

        try {
            FileReader file = new FileReader("./files/offers.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = bf.readLine(); // Ignora encabezado

            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(";");
                
                String name = fields[0];

                offersDAO.insert(name);
            }

            bf.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }
    
    /**
     * Reads image data from a CSV file and inserts each image record into the database.
     * <p>
     * The CSV file is expected at "./files/images.csv" with the following format:
     * <pre>
     * url;apartmentId
     * </pre>
     * The first line is assumed to be a header and is skipped.
     *
     * @throws IOException if an error occurs reading the file.
     */
    public static void insertImages_File_CSV() throws IOException {
        ImagesDAO imagesDAO = new ImagesDAO();

        try {
            FileReader file = new FileReader("./files/images.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = bf.readLine(); // Ignora encabezado

            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(";");
            
                String url = fields[0];
                int apartamentId = Integer.parseInt(fields[1]);

                imagesDAO.insert(url, apartamentId);
            }

            bf.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }

	/**
	 * Reads apartment-offer association data from a CSV file and inserts each record into the database.
	 * <p>
	 * The CSV file is expected at "./files/apartments_offers.csv" with the following format:
	 * <pre>
	 * apartmentId;offerId
	 * </pre>
	 * The first line is assumed to be a header and is skipped.
	 *
	 * @throws IOException if an error occurs reading the file.
	 */
    public static void insertApartmentsOffers_File_CSV() throws IOException {
    	ApartmentsOffersDAO apartmentsOffersDAO = new ApartmentsOffersDAO();

        try {
            FileReader file = new FileReader("./files/apartments_offers.csv");
            BufferedReader bf = new BufferedReader(file);

            String line = bf.readLine(); // Ignora encabezado

            while ((line = bf.readLine()) != null) {
                String[] fields = line.split(";");
            
                int apartmentId = Integer.parseInt(fields[0]);
                int offerId = Integer.parseInt(fields[1]);
                
                apartmentsOffersDAO.insert(apartmentId, offerId);
            }

            bf.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database.");
            e.printStackTrace();
        }
    }
}