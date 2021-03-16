package controller.KWICSystem;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

/**
 * This class is a sink for data from a pipe of threads.  It can be connected
 * to by other Pipe, but its constructor is not passed a Pipe sink for it
 * to connect to.  That is, it must always be at the end or "sink" of a
 * pipe.  It writes the characters into a specified Writer (such as a
 * FileWriter).
 **/
public class Printer {

    Lines lines;
    ArrayList<int[]> sortedWordIndices;
    ArrayList<String> noiseWordsList = new ArrayList<>();

    public Printer(Sorter alphabetizer, String noiseWords) {
        lines = alphabetizer.getLines();
        sortedWordIndices = alphabetizer.getSortedWordIndices();

        String[] noiseWordArray = noiseWords.split("\\s");
        for (int i = 0; i < noiseWordArray.length; i++) {
            noiseWordsList.add(noiseWordArray[i].toLowerCase());
        }
        System.out.println(noiseWordsList);
    }
    private boolean startsWithANoiseWord(String line) {
        for (int i = 0; i < noiseWordsList.size(); i++) {
            if (line.toLowerCase().startsWith(noiseWordsList.get(i))) {
                return true;
            }
        }
        return  false;
    }

    public String print() {
        String outputString = "";
        for (int i = 0; i < sortedWordIndices.size(); i++) {
            int lineIndex = sortedWordIndices.get(i)[0];
            int charIndex = sortedWordIndices.get(i)[1];
            String line = lines.getLine(lineIndex, charIndex);
            //System.out.println(line);
            if (!startsWithANoiseWord(line)) {
                //System.out.println("This line does not start with a noise word");
                outputString += line + '\n';
            }

        }
        return  outputString;
    }

}

