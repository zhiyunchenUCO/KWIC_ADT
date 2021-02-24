package controller;

import controller.KWICSystem.*;
import view.MyWindow;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    static String inputString = "This is a test";

    public static void main(String[] args) throws IOException {
        //Pipeline.transform(inputString);
        MyWindow win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }


}