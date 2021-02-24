package controller.KWICSystem;

import controller.Main;

import java.io.*;
import java.util.Collections;

/**
 * This is concrete implementation of the PipeFilter interface.
 **/
public class Shifter extends Filter {


    public Shifter(Pipe sink)
            throws IOException {
        super(sink);
    }

    /**
     * Do the filtering, using a custom-defined arraylist (lines) to accept lines read from
     * the Reader, circulating each line, and then sending those lines back out to the Writer.
     **/
    public void filter(Reader in, Writer out) throws IOException {

        lines.readLines(in);
        Lines circulariseLines = circularShift(lines);
        circulariseLines.write(out);
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
