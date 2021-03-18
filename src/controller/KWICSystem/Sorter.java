package controller.KWICSystem;

import java.util.ArrayList;

public interface Sorter {

    void sort(Shifter shifter);
    ArrayList<int[]> getSortedWordIndices();
    String getCirculatedLine(int[] wordIndex);
}
