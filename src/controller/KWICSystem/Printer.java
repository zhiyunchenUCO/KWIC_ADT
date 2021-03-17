package controller.KWICSystem;

import java.util.ArrayList;


public class Printer {

    String[] noiseWordArray;

    public String print(Sorter alphabetizer, String noiseWords) {

        ArrayList<int[]> sortedWordIndices = alphabetizer.getSortedWordIndices();
        noiseWordArray = noiseWords.split("\\s");

        String outputString = "";
        for (int i = 0; i < sortedWordIndices.size(); i++) {
            String line = alphabetizer.getCirculatedLine(sortedWordIndices.get(i));
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

