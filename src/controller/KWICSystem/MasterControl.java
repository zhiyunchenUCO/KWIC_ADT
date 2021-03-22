package controller.KWICSystem;

import com.google.common.base.Stopwatch;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

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

        return outputString;
    }

    public String loadTestFile () {
        String fileContent = "";
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {
            fileContent = new Scanner(new File("test_data/test-data-fu.txt")).useDelimiter("\\Z").next();
        }
        catch (IOException e) {
            System.out.println("ERROR in loadTestFile(): " + e);
        }
        return fileContent;
    }

    public String[] runBenchmark (String inputString, String noiseWords) {
        int iterations = 1000;
        String[] results = new String[3];
        String outputString = "";

        // Start timing
        Stopwatch stopwatch = Stopwatch.createStarted();

        /* ... the code being measured starts ... */
        for (int i = 0; i < iterations; i++) {
            try {
                outputString = transform(inputString, noiseWords);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        /* ... the code being measured ends ... */
        stopwatch.stop();

        // get elapsed time, expressed in milliseconds
        long timeElapsed = stopwatch.elapsed(TimeUnit.MILLISECONDS);

        results[0] = outputString;
        results[1] = String.valueOf(iterations);
        results[2] = String.valueOf(timeElapsed);

        return results;
    }

}


