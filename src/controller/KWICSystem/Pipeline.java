package controller.KWICSystem;

import controller.Main;

import java.io.*;

public class Pipeline {

    public static String transform (String inputString) throws IOException {

        //Create a Reader to read data from, and a Writer to send data to
        //Create an outputStream to hold the data
        Reader in = new BufferedReader(new StringReader(inputString));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream));

        // Now build up the pipe, starting with the sink, and working backwards,
        // through various filters, until we reach the source.
        Printer output = new Printer(out);
        Filter alphabetizer = new Sorter(output);
        Filter circularShift = new Shifter(alphabetizer);
        Loader input = new Loader(circularShift, in);

        // Start the pipe -- start each of the threads in the pipe running.
        System.out.println("Starting pipe...");
        input.startPipe();

        // Wait for the pipe to complete
        try {
            input.joinPipe();
        } catch (InterruptedException e) {
            System.out.println("Cannot join pipes");}

        // Dump the data from outputStream to a string
        String outputString = new String(outputStream.toByteArray());
        System.out.println(outputString);
        System.out.println("Done.");

        return outputString;
    }
}
