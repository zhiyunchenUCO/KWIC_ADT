package controller.KWICSystem;

import java.io.*;
import java.util.ArrayList;

public class MasterControl{

    public String transform (String inputString, String noiseWords) throws IOException {

        //Create a Reader to read data from, and a Writer to send data to
        Reader in = new BufferedReader(new StringReader(inputString));

        // Create a new line storage to store input data
        Lines lines = new Lines();

        // Create and call the input to read and store inputString
        Input input = new Input();
        input.read(in);
        input.store(lines);

        // Create and call the circular shifter to setup a word index list
        Shifter circularShifter = new Shifter();
        circularShifter.setup(lines);

        // Create and call the alphabetizer to sort the word index list
        Sorter alphabetizer = new Sorter(circularShifter);
        alphabetizer.sort();

        // Create and call the output to print final lines
        Printer output = new Printer(alphabetizer, noiseWords);
        String outputString = output.print();
        System.out.println(outputString);
        System.out.println("Done.");

        return outputString;
    }
}
