package daxbnb.model;

import java.util.Comparator;

/**
 * Comparator implementation to compare {@link Apartments} objects based on their name.
 * <p>
 * This comparator compares apartment names in a case-insensitive manner.
 * </p>
 * 
 * Usage example:
 * <pre>
 *     Collections.sort(apartmentsList, new ApartmentsNameComparator());
 * </pre>
 * 
 * @author 
 * @version 1.0, 26 May 2025
 */
public class ApartmentsNameComparator implements Comparator<Apartments> {

    /**
     * Compares two {@link Apartments} instances by their names ignoring case differences.
     * 
     * @param o1 the first apartment to compare
     * @param o2 the second apartment to compare
     * @return a negative integer, zero, or a positive integer as the first argument
     *         is less than, equal to, or greater than the second, ignoring case.
     */
    @Override
    public int compare(Apartments o1, Apartments o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
