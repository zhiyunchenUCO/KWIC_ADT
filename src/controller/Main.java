package controller;

import controller.KWICSystem.MasterControl;
import view.MyWindow;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        MyWindow win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        //test();

    }
    static void test() throws IOException {
        String inputString = "Itzy Bitzy Spider \nitzy bitzy spider \n";
        String noiseWords = "the a";
        MasterControl masterControl = new MasterControl();
        masterControl.transform(inputString, noiseWords);
    }
}