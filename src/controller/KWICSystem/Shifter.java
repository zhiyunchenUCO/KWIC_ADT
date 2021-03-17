package controller.KWICSystem;

import java.io.*;
import java.util.ArrayList;

public class Shifter {

    LineStorage lines;
    ArrayList<int[]> wordIndices = new ArrayList<>();

    public void setup(LineStorage lines) throws IOException {

        this.lines = lines;
        setWordIndices();
    }

    public ArrayList<int[]> getWordIndices() {
        return  wordIndices;
    }

    public LineStorage getLines() {
        return lines;
    }

    private void setWordIndices() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            // Add the position of the first word to word indices
            int[] startWordIndex = new int[]{i, 0};
            wordIndices.add(startWordIndex);

            // Add positions of the following words to word indices
            for (int j=0; j<line.length(); j++) {
                if (line.charAt(j) == ' ') {
                    wordIndices.add(new int[]{i, j+1});
                }
            }
        }
    }

}
