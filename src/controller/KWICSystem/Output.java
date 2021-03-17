package controller.KWICSystem;

import java.util.ArrayList;


public class Output {

    Lines lines;
    ArrayList<int[]> sortedWordIndices;
    String[] noiseWordArray;

    public Output(Sorter alphabetizer) {
        lines = alphabetizer.getLines();
        sortedWordIndices = alphabetizer.getSortedWordIndices();
    }

    private boolean startsWithANoiseWord(String line) {
        for (int i = 0; i < noiseWordArray.length; i++) {
            if (line.toLowerCase().startsWith(noiseWordArray[i])) {
                return true;
            }
        }
        return  false;
    }

    public String print(String noiseWords) {

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

}

