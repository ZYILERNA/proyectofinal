-- En esta tabla almacenaremos a todos los usuarios
CREATE TABLE USER (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    address VARCHAR(255)
);

-- Esta es la tabla de las ofertas de las propiedades o las comodidades extra que tienen (piscina, ascensor, limpieza, etc...)
CREATE TABLE OFFERS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

-- En esta tabla guardaremos la url de las propiedades
CREATE TABLE IMAGES (
    id INT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(255),
    apartmentId INT,
    FOREIGN KEY (apartmentId) REFERENCES APARTMENTS(id) ON DELETE CASCADE
);

-- Esta tabla es en donde ingresaremos todas las propiedades (Otro nombre opcional es Properties)
CREATE TABLE APARTMENTS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    location VARCHAR(255),
    capacity INT,
    bedrooms INT,
    beds INT,
    baths INT,
    pricePerNight DOUBLE,
    availability BOOLEAN,
    apartmentType VARCHAR(20)
);

-- Esta es la tabla de las reservas o bookings
CREATE TABLE BOOKING (
    id INT PRIMARY KEY AUTO_INCREMENT,
    startDate DATE,
    endDate DATE,
    userId INT,
    apartmentId INT,
    totalPrice DOUBLE,
    status VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES USER(id) ON DELETE CASCADE,
    FOREIGN KEY (apartmentId) REFERENCES APARTMENTS(id) ON DELETE CASCADE
);

-- Esta es la tabla intermedia entre propiedades y reservas
CREATE TABLE APARTMENT_OFFERS (
    id INT PRIMARY KEY AUTO_INCREMENT,
    apartmentId INT,
    offerId INT,
    FOREIGN KEY (apartmentId) REFERENCES APARTMENTS(id) ON DELETE CASCADE,
    FOREIGN KEY (offerId) REFERENCES OFFERS(id) ON DELETE CASCADE
);

-- Procedures

-- 1 Este procedure hace la funcion de agregar los datos almacenados por java, se guarde en las variables del procedure para que se pueda insertarla.

DELIMITER //

DROP PROCEDURE IF EXISTS InsertBooking//

CREATE PROCEDURE InsertBooking (
    IN p_startDate DATE,
    IN p_endDate DATE,
    IN p_userId INT,
    IN p_apartmentId INT,
    IN p_totalPrice DOUBLE,
    IN p_status VARCHAR(20)
)
BEGIN
    INSERT INTO BOOKING (startDate, endDate, userId, apartmentId, totalPrice, status)
    VALUES (p_startDate, p_endDate, p_userId, p_apartmentId, p_totalPrice, p_status);
END //

DELIMITER ;

DROP PROCEDURE IF EXISTS InsertBooking;


--2 Verifica si la fecha introducida está ya reservada, en caso de que lo esté pondrá un mensaje de error impidiendo agregarse.

DELIMITER //

DROP PROCEDURE IF EXISTS check_overlapping_booking//

CREATE PROCEDURE check_overlapping_booking(
    IN apt_id INT,
    IN new_start DATE,
    IN new_end DATE
)
BEGIN
    SELECT COUNT(*) AS overlap_count
    FROM BOOKING
    WHERE apartmentid = apt_id
      AND (startDate <= new_end AND endDate >= new_start);
END//

DELIMITER ;


--3 Consigue los dias totales y los calcula, a través los variables almacenados en la base de datos.

DELIMITER //

DROP PROCEDURE IF EXISTS calculate_total_booking//

CREATE PROCEDURE calculate_total_booking(
    IN p_apartment_id INT,
    IN p_start_date DATE,
    IN p_end_date DATE,
    OUT p_total DECIMAL(10, 2)    
)
BEGIN
    DECLARE days INT;
    DECLARE price DECIMAL(10,2);

    SELECT DATEDIFF(p_end_date, p_start_date) INTO days;
    SELECT pricePerNight INTO price FROM APARTMENTS WHERE id = p_apartment_id;
    SET p_total = days * price;
END//

DELIMITER ;



