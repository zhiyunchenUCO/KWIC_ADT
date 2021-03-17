package controller.KWICSystem;

import java.io.*;

public class MasterControl implements Control{

    public String transform (String inputString, String noiseWords) throws IOException {

        //Create a Reader to read data from
        java.io.Reader in = new BufferedReader(new StringReader(inputString));

        // Create a new line storage to store input data
        LineStorage lines = new LineStorage();

        // Call the input to read and store inputString
        Input input = new StringInput();
        input.read(in);
        input.store(lines);

        // Call the circular shifter to setup a word index list
        Shifter circularShifter = new CircularShifter();
        circularShifter.setup(lines);

        // Call the alphabetizer to sort the word index list
        Sorter alphabetizer = new Alphabetizer();
        alphabetizer.sort(circularShifter);

        // Call the output to print final lines
        Output output = new StringOutput();
        String outputString = output.print(alphabetizer, noiseWords);
        System.out.println(outputString);
        System.out.println("Done.");

        return outputString;
    }
}
