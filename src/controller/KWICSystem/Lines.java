package controller.KWICSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Lines {

    private ArrayList<String> lines = new ArrayList<>();

    public String getLine(int lineIndex) {
        return lines.get(lineIndex);
    }

    public ArrayList<String> getLines() {
        return  lines;
    }

    public int getLineCount() {
        return lines.size();
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
                lines.add(lineString);
            }
            in.close();
        }
        catch (IOException e) {}
    }
}
