package controller.KWICSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

public class Lines extends ArrayList<String> {

    public Lines() {
        super();
    }

    public Lines readLines(Reader in) throws IOException {
        String line = null;
        BufferedReader br = new BufferedReader(in);

        while ((line = br.readLine()) != null) {
            this.add(line);
        }
        return this;
    }

    public void write(Writer out) throws IOException {
        for (String sortedLine : this) {
            out.write(sortedLine);
            out.write("\n");
        }
    }
}
