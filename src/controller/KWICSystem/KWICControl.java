package controller.KWICSystem;

import java.io.*;

public class KWICControl {


    public String transform (String inputString, String noiseWords) throws IOException {

        //Create a Reader to read data from
        java.io.Reader in = new BufferedReader(new StringReader(inputString));

        // Create a new line storage to store input data
        Lines lines = new Lines();

        // Call the input to read and store inputString
        Reader input = new Reader();
        input.read(in);
        input.store(lines);

        // Call the circular shifter to setup a word index list
        Shifter circularShifter = new Shifter();
        circularShifter.setup(lines);

        // Call the alphabetizer to sort the word index list
        Sorter alphabetizer = new Sorter();
        alphabetizer.sort(circularShifter);

        // Call the output to print final lines
        Printer output = new Printer();
        String outputString = output.print(alphabetizer, noiseWords);
        System.out.println(lines.getLines());
        System.out.println("Done.");

        return outputString;
    }
}
