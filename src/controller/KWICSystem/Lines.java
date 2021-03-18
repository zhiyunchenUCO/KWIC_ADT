package controller.KWICSystem;

import java.io.IOException;
import java.io.Reader;

public interface Lines {
    String getLine(int lineIndex);
    int getLineCount();
    void setLines(Reader in) throws IOException;
}
