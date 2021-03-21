package controller.KWICSystem;

import java.io.IOException;

public interface Controllable {
    String transform (String inputString, String noiseWords) throws IOException;
}
