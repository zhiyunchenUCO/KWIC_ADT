package controller.KWICSystem;

import java.io.*;

public class PipeAndFilterController {

    public static void transform (String inputString) throws IOException {

        //Create a Reader to read data from, and a Writer to send data to
        Reader in = new BufferedReader(new StringReader(inputString));
        Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

        // Now build up the pipe, starting with the sink, and working backwards,
        // through various filters, until we reach the source.
        Sink output = new Sink(out);
        Filter alphabetizer = new Alphabetizer(output);
        Filter circularShift = new CircularShifter(alphabetizer);
        Source input = new Source(circularShift, in);

        // Start the pipe -- start each of the threads in the pipe running.
        System.out.println("Starting pipe...");
        input.startPipe();

        // Wait for the pipe to complete
        try {
            input.joinPipe();
        } catch (InterruptedException e) {}
        System.out.println("Done.");
    }
}
