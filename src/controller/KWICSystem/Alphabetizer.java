package controller.KWICSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Alphabetizer implements Sortable {

    private Shiftable shifter;
    private ArrayList<int[]> sortedWordIndices;

    public void sort(Shiftable shifter) {
        this.shifter = shifter;
        createSortedWordIndices();
    }

    private void createSortedWordIndices() {

        // Copy the shifted word indices for creating sorted word indices
        ArrayList<int[]> shiftedWordIndices = shifter.getShiftedIndices();
        sortedWordIndices = new ArrayList<>();

        for (int[] index : shiftedWordIndices) {
            sortedWordIndices.add(index.clone());
        }

        Collections.sort(sortedWordIndices, new IntArrayComparator(shifter));
    }

    public ArrayList<int[]> getSortedWordIndices() {
        return sortedWordIndices;
    }

    public String getLine(int[] wordIndex) {
        return shifter.getShiftedLine(wordIndex);
    }

    /**
     * This method defines a comparator that compares two indices based on the
     * strings they refer to
     **/
    private class IntArrayComparator implements Comparator<int[]>{
        Shiftable shifter;

        public IntArrayComparator(Shiftable shifter) {
            this.shifter = shifter;
        }

        public int compare(int[] intArray1, int[] intArray2) {
            String s1 = shifter.getShiftedLine(intArray1);
            String s2 = shifter.getShiftedLine(intArray2);
//            System.out.println("compare: " + s1 + " to " + s2);
            StringComparator stringComparator = new StringComparator();
            return stringComparator.compare(s1, s2);
        }
    }

    /**
     * This method defines a comparator that compares two strings based on their
     * alphabetic content(sorting algorithm is a<A<b<B<...<z<Z).
     **/
    private class StringComparator implements Comparator<String> {
        String sortingKey = " aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";

        public int compare(String s1, String s2) {

            // If the two strings are identical, then return 0.
            if (s1.equals(s2)) return 0;

            // If the two strings are different, then compare their characters one after
            // the other until the end of the shorter string is reached.
            int minLength = Math.min(s1.length(), s2.length());

            for (int i=0; i<minLength; i++) {
                int s1Index = sortingKey.indexOf(s1.charAt(i));
                int s2Index = sortingKey.indexOf(s2.charAt(i));
                if (s1Index < s2Index) {
                    return -1;
                } else if (s1Index > s2Index) {
                    return 1;
                }
            }

            // The shorter string is smaller.
            if (s1.length() < s2.length()) return -1;
            else return 1;
        }
    }
}
