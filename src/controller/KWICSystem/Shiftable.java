package controller.KWICSystem;

import java.util.ArrayList;

public interface Shiftable {
    void shift(Lines lines);
    ArrayList<int[]> getShiftedIndices();
    String getShiftedLine(int[] wordIndex);
}
