package controller.KWICSystem;

import java.util.ArrayList;

public class Shifter {

    Lines lines;
    ArrayList<int[]> wordIndices;

    public void setup(Lines lines) {

        this.lines = lines;
        wordIndices = new ArrayList<>();
        setWordIndices();
    }

    private void setWordIndices() {

        for (int i = 0; i < lines.getLineCount(); i++) {
            String line = lines.getLine(i);

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

    public ArrayList<int[]> getWordIndices() {
        return  wordIndices;
    }

    public String getCirculatedLine(int[] wordIndex) {
        // Circular read a line, starting at a given char index
        int lineIndex = wordIndex[0];
        int charIndex = wordIndex[1];
        String line = lines.getLine(lineIndex);

        if (charIndex == 0) {
            return  line;
        }
        int lineLength = line.length();
        return line.substring(charIndex, lineLength) + " " + line.substring(0,charIndex);
    }

    public Lines getLines() {
        return lines;
    }
}
