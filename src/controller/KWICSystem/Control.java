package controller.KWICSystem;

import java.io.IOException;

public interface Control {
    String transform (String inputString, String noiseWords) throws IOException;
}
