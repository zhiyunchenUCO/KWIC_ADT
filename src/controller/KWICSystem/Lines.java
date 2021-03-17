package controller.KWICSystem;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public interface Lines {
    String getLine(int lineIndex);
    ArrayList<String> getLines();
    int getLineCount();
    void setLines(Reader in) throws IOException;
}
