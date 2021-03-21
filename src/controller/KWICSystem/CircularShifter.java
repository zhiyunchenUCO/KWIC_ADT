package controller.KWICSystem;

import java.util.ArrayList;

public class CircularShifter implements Shiftable {

    private Lines lines;
    private ArrayList<int[]> wordIndices;

    public void shift(Lines lines) {

        this.lines = lines;
        wordIndices = new ArrayList<>();
        createWordIndices();
    }

    public ArrayList<int[]> getShiftedIndices() {
        return wordIndices;
    }

    private void createWordIndices() {

        // Cycle through each line
        for (int i = 0; i < lines.getLineCount(); i++) {
            String line = lines.getLine(i);

            // Add line number and position of the first char of first word
            int[] startWordIndices = new int[]{i, 0};
            wordIndices.add(startWordIndices);

            // Add line number and positions of first char of each remaining word
            for (int j=0; j<line.length(); j++) {
                if (line.charAt(j) == ' ') {
                    wordIndices.add(new int[]{i, j+1});
                }
            }
        }
    }

    public String getShiftedLine(int[] wordIndices) {

        int lineIndex = wordIndices[0];
        int charIndex = wordIndices[1];

        String line = lines.getLine(lineIndex);

        if (charIndex == 0) { // Return original line
            return line;
        }
        else { // Return shifted line
            int lineLength = line.length();
            return line.substring(charIndex, lineLength) + " " + line.substring(0, charIndex);
        }
    }
}
