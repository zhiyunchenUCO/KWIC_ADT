package controller.KWICSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class LineHandler implements Lines {

    private ArrayList<String> lines = new ArrayList<>();

    public String getLine(int lineIndex) {
        return lines.get(lineIndex);
    }

    public int getLineCount() {
        return lines.size();
    }

    public void setLines(Reader in) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(in);

        // Clean up the input line so that each word is spaced with a single whitespace
        try {
            String[] words;
            String lineString;
            while ((line = br.readLine()) != null) {
                words = line.split("\\s"); // split line into words with whitespaces
                lineString = String.join(" ", words); // combine and space words into one string
                lines.add(lineString);
            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("ERROR in ineStorage.setLines(): " + e);
        }
    }
}
