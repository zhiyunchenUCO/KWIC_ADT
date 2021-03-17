package controller.KWICSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class LineStorage extends ArrayList<String>{

    public LineStorage() {
        super();
    }

    public String getLine(int lineIndex) {
        return this.get(lineIndex);
    }

    public String getCirculatedLine(int lineIndex, int charIndex) {
        // Circular read a line, starting at a given char index
        String line = this.get(lineIndex);

        if (charIndex == 0) {
            return  line;
        }
        int lineLength = line.length();
        return line.substring(charIndex, lineLength) + " " + line.substring(0,charIndex);
    }

    public  void setLines(Reader in) throws IOException {

        String line = null;
        BufferedReader br = new BufferedReader(in);

        try {
            // Clean up the input line so that each word is spaced with a single whitespace
            String[] words;
            String lineString = "";
            while ((line = br.readLine()) != null) {
                words = line.split("\\s"); // split line into words with whitespaces
                lineString = String.join(" ", words); // combine words into one string with a whitespace as spacer
                this.add(lineString);
            }
            in.close();
        }
        catch (IOException e) {}
    }
}
