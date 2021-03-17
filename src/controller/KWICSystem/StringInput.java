package controller.KWICSystem;

import java.io.IOException;


public class StringInput implements Input{

    protected java.io.Reader in;  // The Reader we take data from

    public void read(java.io.Reader in) {
        this.in = in;
    }

    public void store(Lines lines) throws IOException {
        lines.setLines(in);
    }
}
