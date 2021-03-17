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

    Shifter circularShifter;
    ArrayList<int[]> sortedWordIndices;

    public void sort(Shifter circularShifter) throws IOException {
        this.circularShifter = circularShifter;
        setSortedWordIndices();
    }

    public ArrayList<int[]> getSortedWordIndices() {
        return sortedWordIndices;
    }

    public Lines getLines() {
        return circularShifter.getLines();
    }

    public String getCirculatedLine(int[] wordIndex) {
        return circularShifter.getCirculatedLine(wordIndex);
    }


    private void setSortedWordIndices() {

        // Deep copy the word indices from circular shifter
        ArrayList<int[]> wordIndices = circularShifter.getWordIndices();
        sortedWordIndices = new ArrayList<>();
        for (int[] index : wordIndices) {
            sortedWordIndices.add(index.clone());
        }

        Collections.sort(sortedWordIndices, new IntArrayComparator(circularShifter));
    }


    /**
     * This method defines a comparator that compares two indices based on the
     * strings they refer to.
     **/
    private class IntArrayComparator implements Comparator<int[]>{
        Shifter circularShifter;
        public IntArrayComparator(Shifter circularShifter) {
            this.circularShifter = circularShifter;
        }
        public int compare(int[] intArray1, int[] intArray2) {
            String s1 = circularShifter.getCirculatedLine(intArray1);
            String s2 = circularShifter.getCirculatedLine(intArray2);
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
