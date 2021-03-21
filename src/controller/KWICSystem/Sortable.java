package controller.KWICSystem;

import java.util.ArrayList;

public interface Sortable {

    void sort(Shiftable shifter);
    ArrayList<int[]> getSortedWordIndices();
    String getLine(int[] wordIndex);
}
