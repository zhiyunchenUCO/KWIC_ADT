package controller.KWICSystem;

import java.io.*;
import java.util.ArrayList;

public class Shifter {

    LineStorage lines;
    ArrayList<int[]> wordIndices = new ArrayList<>();

    public void setup(LineStorage lines) throws IOException {

        this.lines = lines;
        //wordIndices = getAllWordIndices();
        setWordIndices();
    }

    public ArrayList<int[]> getWordIndices() {
        return  wordIndices;
    }

    public LineStorage getLines() {
        return lines;
    }

    private ArrayList getWordPositionsPerLine(int lineIndex) {
        if (lineIndex >= lines.size()) {
            return  null;
        }
        ArrayList<int[]> wordIndices = new ArrayList<int[]>();
        String line = lines.get(lineIndex);

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
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<int[]> wordIndices = getWordPositionsPerLine(i);
            allWordIndices.addAll(wordIndices);
        }
        return allWordIndices;
    }

    public void setWordIndices() {
        for (int i = 0; i < lines.size(); i++) {
            //ArrayList<int[]> wordPositions = getWordPositionsPerLine(i);
            //wordIndices.addAll(wordPositions);
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
