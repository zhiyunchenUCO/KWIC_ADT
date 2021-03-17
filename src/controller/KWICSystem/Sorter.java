package controller.KWICSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is another implementation of Filter.  It implements the
 * sorting of lines according to a custom-defined sorting algorithm.
 **/
public class Sorter {

    ArrayList<int[]> sortedWordIndices;
    LineStorage lines;

    public void sort(Shifter circularShifter) throws IOException {
        // Retrieve lines from circular shifter
        lines = circularShifter.getLines();

        // Deep copy the word indices from circular shifter
        ArrayList<int[]> wordIndices = circularShifter.getWordIndices();
        sortedWordIndices = new ArrayList<>();
        for (int[] index : wordIndices) {
            sortedWordIndices.add(index.clone());
        }

        Collections.sort(sortedWordIndices, new IntArrayComparator(lines));
    }

    public ArrayList<int[]> getSortedWordIndices() {
        return sortedWordIndices;
    }

    public LineStorage getLines() {
        return lines;
    }

    /**
     * This method defines a comparator that compares two indices based on the
     * strings they refer to.
     **/
    private class IntArrayComparator implements Comparator<int[]>{
        LineStorage lines;
        public IntArrayComparator(LineStorage lines) {
            this.lines = lines;
        }
        public int compare(int[] intArray1, int[] intArray2) {
            String s1 = lines.getCirculatedLine(intArray1[0], intArray1[1]);
            String s2 = lines.getCirculatedLine(intArray2[0], intArray2[1]);
            StringComparator stringComparator = new StringComparator();
            return stringComparator.compare(s1, s2);
        }
    }

    /**
     * This method defines a comparator that compares two strings based on their
     * alphabetic content(sorting algorithm is a<A<b<B<...<z<Z).
     **/
    private class StringComparator implements Comparator<String> {
        String alphabetString = " aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
        LineStorage lines;


        public int compare(String s1, String s2) {
            // If the two strings are identical, then return 0.
            if (s1.equals(s2)) return 0;

            // If the two strings are different, then compare their characters one after
            // the other until the end of the shorter string is reached.
            int minLength = Math.min(s1.length(), s2.length());

            for (int i=0; i<minLength; i++) {
                int s1Index = alphabetString.indexOf(s1.charAt(i));
                int s2Index = alphabetString.indexOf(s2.charAt(i));
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
