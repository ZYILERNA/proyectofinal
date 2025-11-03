package daxbnb.model;

import java.util.Comparator;

/**
 * Comparator implementation to compare {@link Apartments} objects based on their price per night.
 * <p>
 * This comparator sorts apartments in descending order of their price per night (highest price first).
 * </p>
 * 
 * Usage example:
 * <pre>
 *     Collections.sort(apartmentsList, new ApartmentsPriceComparator());
 * </pre>
 * 
 * @author 
 * @version 1.0, 26 May 2025
 */
public class ApartmentsPriceComparator implements Comparator<Apartments> {

    /**
     * Compares two {@link Apartments} instances by their price per night in descending order.
     * 
     * @param o1 the first apartment to compare
     * @param o2 the second apartment to compare
     * @return a negative integer, zero, or a positive integer as the second argument's
     *         price per night is less than, equal to, or greater than the first's price.
     */
    @Override
    public int compare(Apartments o1, Apartments o2) {
        return Double.compare(o2.getPricePerNight(), o1.getPricePerNight());
    }
}
