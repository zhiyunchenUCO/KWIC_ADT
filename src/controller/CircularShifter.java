package controller;

import model.Lines;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is concrete implementation of the PipeFilter interface.
 * It uses the GrepReader we defined elsewhere to do the filtering.
 **/
public class CircularShifter extends Filter {

    /**
     * Create a GrepFilter, will search its input for the specified pattern
     * and send the results to the specified Pipe.
     **/
    public CircularShifter(Pipe sink)
            throws IOException {
        super(sink);
    }

    /**
     * Do the filtering, using a BufferedReader to filter lines read from
     * the Reader, and using a PrintWriter to send those lines back out
     * to the Writer.
     **/
    public void filter(Reader in, Writer out) throws IOException {

        lines.readLines(in);
        Lines cicularshiftedLines = circularShift(lines);
        cicularshiftedLines.write(out);
    }

    protected Lines  circularShift(Lines lines) throws IOException {

        Lines circularShiftedLines = new Lines();
        for (int i=0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            circularShiftedLines.add(currentLine);  // Add the current line to the circular shifted lines

            String[] words = currentLine.split("\\s"); // split line into words with whitespaces
            Lines wordList = new Lines();
            Collections.addAll(wordList, words);

            for (int j=0; j < wordList.size()-1; j++) {
                String firstWord = wordList.get(0);
                wordList.remove(0);  // Remove the first word from the beginning
                wordList.add(firstWord); // Add the first word to the end
                String listString = String.join(" ", wordList);
                circularShiftedLines.add(listString);
            }
        }
        return circularShiftedLines;
    }
}
