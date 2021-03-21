package controller.KWICSystem;

import java.io.IOException;

public interface Inputtable {
    void read(java.io.Reader in);
    void store(Lines lines) throws IOException;
}
