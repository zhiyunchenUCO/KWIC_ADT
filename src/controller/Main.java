package controller;

import controller.KWICSystem.KWICControl;
import view.GUI;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        GUI win = new GUI();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);

        //test();

    }
    static void test() throws IOException {
        String inputString = "Itzy Bitzy Spider \nitzy bitzy spider \n";
        String noiseWords = "the a";
        KWICControl KWICControl = new KWICControl();
        KWICControl.transform(inputString, noiseWords);
    }
}