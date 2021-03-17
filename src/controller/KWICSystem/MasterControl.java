package controller.KWICSystem;

import java.io.*;

public class MasterControl{

    Input input = new Input();
    Shifter circularShifter = new Shifter();
    Sorter alphabetizer = new Sorter();
    Output output = new Output();

    public String transform (String inputString, String noiseWords) throws IOException {

        //Create a Reader to read data from
        Reader in = new BufferedReader(new StringReader(inputString));

        // Create a new line storage to store input data
        LineStorage lines = new LineStorage();

        // Call the input to read and store inputString

        input.read(in);
        input.store(lines);

        // Call the circular shifter to setup a word index list
        circularShifter.setup(lines);

        // Call the alphabetizer to sort the word index list
        alphabetizer.sort(circularShifter);

        // Call the output to print final lines
        String outputString = output.print(alphabetizer, noiseWords);
        System.out.println(outputString);
        System.out.println("Done.");

        return outputString;
    }
}
