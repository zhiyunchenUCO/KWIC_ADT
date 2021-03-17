package controller.KWICSystem;

import java.io.*;
import java.util.ArrayList;

public class Shifter {

    Lines lines;
    ArrayList<int[]> wordIndices = new ArrayList<>();

    public void setup(Lines lines) throws IOException {

        this.lines = lines;
        wordIndices = lines.getAllWordIndices();
    }

    public ArrayList<int[]> getWordIndices() {
        return  wordIndices;
    }

    public Lines getLines() {
        return lines;
    }
}
