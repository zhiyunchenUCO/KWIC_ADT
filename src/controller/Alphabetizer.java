package controller;

import model.Lines;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is another implementation of Filter.  It implements the
 * trivial rot13 cipher on the letters A-Z and a-z.  Rot-13 "rotates"
 * ASCII letters 13 characters through the alphabet.
 **/
public class Alphabetizer extends Filter {
    /** Constructor just calls superclass */
    public Alphabetizer(Pipe sink) throws IOException { super(sink); }

    /**
     * Do the filtering, using a BufferedReader to filter lines read from
     * the Reader, and using a PrintWriter to send those lines back out
     * to the Writer.
     **/
    public void filter(Reader in, Writer out) throws IOException {

        lines.readLines(in);

        Collections.sort(lines);

        lines.write(out);
    }


    private class StringComparator implements Comparator<String> {
        String alphabetString = " aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
        char[] alphabetArray = { ' ',
                'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F',
                'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l', 'L',
                'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R',
                's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X',
                'y', 'Y', 'z', 'Z'
        };
        String[] intStringArray = {"00", "01", "02", "03", "04", "05", "06", "07",
                "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
                "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
                "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40",
                "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
                "52", "53"
        };

        public int compare(String s1, String s2) {
            int s1Index = alphabetString.indexOf(s1.charAt(0));
            int s2Index = alphabetString.indexOf(s2.charAt(0));
            if (s1Index < s2Index) {
                return -1;
            }else if (s1Index > s2Index) {
                return 1;
            } else {
                return 0;
            }
        }

        private String charToInt(String charString) {
            String intString = "";
            for (int i=0; i < charString.length(); i++) {
                int index = alphabetString.indexOf(charString.charAt(i));
                intString += intStringArray[index];
            }
            return intString;
        }


    }
}
