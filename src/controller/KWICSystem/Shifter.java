package controller.KWICSystem;

import java.util.ArrayList;

public interface Shifter {
    void setup(Lines lines);
    ArrayList<int[]> getWordIndices();
    String getCirculatedLine(int[] wordIndex);
}
