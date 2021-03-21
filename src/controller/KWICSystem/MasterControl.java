package controller.KWICSystem;

import java.io.*;

public class MasterControl implements Controllable {

    public String transform (String inputString, String noiseWords) throws IOException {

        LineHandler lineHandler = new LineHandler();
        java.io.Reader reader = new BufferedReader(new StringReader(inputString));

        // Process input
        Inputtable inputHandler = new InputHandler();
        inputHandler.read(reader);
        inputHandler.store(lineHandler);

        // Shift the lines
        Shiftable shifter = new CircularShifter();
        shifter.shift(lineHandler);

        // Sort the lines
        Sortable sorter = new Alphabetizer();
        sorter.sort(shifter);

        // Print results
        Outputtable output = new OutputHandler();
        String outputString = output.print(sorter, noiseWords);
//        System.out.println(outputString);
        System.out.println("Done.");

        return outputString;
    }
}
