package controller.KWICSystem;

import java.util.ArrayList;


public class Output {

    LineStorage lines;
    ArrayList<int[]> sortedWordIndices;
    String[] noiseWordArray;

    public String print(Sorter alphabetizer, String noiseWords) {

        lines = alphabetizer.getLines();
        sortedWordIndices = alphabetizer.getSortedWordIndices();

        noiseWordArray = noiseWords.split("\\s");

        String outputString = "";
        for (int i = 0; i < sortedWordIndices.size(); i++) {
            int lineIndex = sortedWordIndices.get(i)[0];
            int charIndex = sortedWordIndices.get(i)[1];
            String line = lines.getLine(lineIndex, charIndex);
            if (!startsWithANoiseWord(line)) {
                outputString += line + '\n';
            }

        }
        return  outputString;
    }

    private boolean startsWithANoiseWord(String line) {
        for (int i = 0; i < noiseWordArray.length; i++) {
            if (line.toLowerCase().startsWith(noiseWordArray[i] + " ")
                    || line.toLowerCase() == noiseWordArray[i]) {
                return true;
            }
        }
        return  false;
    }
}

