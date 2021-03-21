package controller.KWICSystem;

import java.io.IOException;

public class InputHandler implements Inputtable {

    private java.io.Reader in;

    public void read(java.io.Reader in) {
        this.in = in;
    }

    public void store(Lines lines) throws IOException {
        lines.setLines(in);
    }
}
