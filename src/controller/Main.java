package controller;

import view.MyWindow;

import javax.swing.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        MyWindow win = new MyWindow();
        win.init();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}