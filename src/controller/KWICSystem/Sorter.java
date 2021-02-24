package controller.KWICSystem;

import controller.Main;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;

/**
 * This is another implementation of Filter.  It implements the
 * sorting of lines according to a custom-defined sorting algorithm.
 **/
public class Sorter extends Filter {
    /** Constructor just calls superclass */
    public Sorter(Pipe sink) throws IOException { super(sink); }

    /**
     * Do the filtering, using a custom-defined arraylist (lines) to accept lines read from
     * the Reader, using a custom-defined comparator to sort the lines,
     * and then sending those lines back out to the Writer.
     **/
    public void filter(Reader in, Writer out) throws IOException {

        lines.readLines(in);

        Collections.sort(lines, new StringComparator());

        lines.write(out);
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
