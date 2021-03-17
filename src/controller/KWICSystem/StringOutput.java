package controller.KWICSystem;

import java.util.ArrayList;


public class StringOutput implements Output{

    String[] noiseWordArray;

    public String print(Sorter sorter, String noiseWords) {

        ArrayList<int[]> sortedWordIndices = sorter.getSortedWordIndices();
        noiseWordArray = noiseWords.split("\\s");

        String outputString = "";
        for (int i = 0; i < sortedWordIndices.size(); i++) {
            String line = sorter.getCirculatedLine(sortedWordIndices.get(i));
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

