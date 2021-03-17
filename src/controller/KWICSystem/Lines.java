package controller.KWICSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Lines extends ArrayList<String>{

    public Lines() {
        super();
    }

    public String getLine(int lineIndex, int charIndex) {
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

    public ArrayList getWordIndicesPerLine(int lineIndex) {
        if (lineIndex >= this.size()) {
            return  null;
        }
        ArrayList<int[]> wordIndices = new ArrayList<int[]>();
        String line = this.get(lineIndex);

        int[] startWordIndex = new int[]{lineIndex, 0};
        wordIndices.add(startWordIndex);

        for (int i=0; i<line.length(); i++) {
            if (line.charAt(i) == ' ') {
                wordIndices.add(new int[]{lineIndex, i+1});
            }
        }
        return  wordIndices;
    }

    public ArrayList getAllWordIndices() {
        ArrayList<int[]> allWordIndices = new ArrayList<int[]>();
        for (int i = 0; i < this.size(); i++) {
            ArrayList<int[]> wordIndices = getWordIndicesPerLine(i);
            allWordIndices.addAll(wordIndices);
        }
        return allWordIndices;
    }

}
