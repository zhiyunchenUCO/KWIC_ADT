package controller.KWICSystem;

import java.io.IOException;
import java.io.Reader;


public class Input {

    protected Reader in;  // The Reader we take data from

    public void read(Reader in) {
        this.in = in;
    }

    public void store(LineStorage lines) throws IOException {
        lines.setLines(in);
    }
}
