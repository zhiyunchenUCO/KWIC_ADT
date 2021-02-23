package controller;


import controller.KWICSystem.PipeAndFilterController;
import view.MyWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Writer;

public class InputButtonListener implements ActionListener {

    private MyWindow win;
    private JTextArea inputArea;
    private JTextArea circularShiftArea;
    private JTextArea alphabetizedArea;

    public InputButtonListener(MyWindow win) {

        this.win = win;
        inputArea = win.getInputArea();
        circularShiftArea = win.getCircularShiftArea();
        alphabetizedArea = win.getAlphabetizeArea();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String inputString = inputArea.getText();
        try {
            PipeAndFilterController.transform(inputString);
            circularShiftArea.setText(Main.strings[1]);
            alphabetizedArea.setText(Main.strings[2]);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
