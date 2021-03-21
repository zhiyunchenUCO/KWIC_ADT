package controller.KWICSystem;

import java.util.ArrayList;


public class OutputHandler implements Outputtable {

    private String[] noiseWordArray;

    public String print(Sortable sorter, String noiseWords) {

        String outputString = "";
        ArrayList<int[]> sortedWordIndices = sorter.getSortedWordIndices();
        noiseWordArray = noiseWords.split("\\s");

        // get shifted lines in alphabetical order
        for (int i = 0; i < sortedWordIndices.size(); i++) {
            String line = sorter.getLine(sortedWordIndices.get(i));
            if (!filterNoiseWords(line)) {
                outputString += line + '\n';
            }
        }
        return outputString;
    }

    private boolean filterNoiseWords(String line) {
        for (int i = 0; i < noiseWordArray.length; i++) {
            if (line.toLowerCase().startsWith(noiseWordArray[i] + " ")
                    || line.toLowerCase() == noiseWordArray[i]) {
                return true;
            }
        }
        return false;
    }
}
