package controller;

import controller.KWICSystem.KWIC;
import controller.KWICSystem.Pipeline;
import view.MyWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputButtonListener implements ActionListener {

    private MyWindow win;
    private JTextArea inputArea;
    private JTextArea outputArea;

    public InputButtonListener(MyWindow win) {

        this.win = win;
        inputArea = win.getInputArea();
        outputArea = win.getOutputArea();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String inputString = inputArea.getText();
        try {
            KWIC pipeline = new Pipeline();
            String outputString = pipeline.transform(inputString);
            outputArea.setText(outputString);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
