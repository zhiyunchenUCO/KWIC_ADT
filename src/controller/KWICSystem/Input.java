package controller.KWICSystem;

import java.io.IOException;

public interface Input {
    void read(java.io.Reader in);
    void store(Lines lines) throws IOException;
}
