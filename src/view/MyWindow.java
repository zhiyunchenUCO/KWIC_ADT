package view;

import controller.InputButtonListener;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea circularShiftArea = new JTextArea();
    private JTextArea alphabetizeArea = new JTextArea();
    private JButton inputButton = new JButton("Input");



    public void init() {
        setSize(700, 500);
        setLocation(200, 100);
        setTitle("KWIC System");

        Container cp = getContentPane();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        JScrollPane scrollPane1 = new JScrollPane(
                inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(
                circularShiftArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane3 = new JScrollPane(
                alphabetizeArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(inputButton);
        mainPanel.add(scrollPane1);
        circularShiftArea.setEditable(false);
        mainPanel.add(new JLabel("Circular shift"));
        mainPanel.add(scrollPane2);
        alphabetizeArea.setEditable(false);
        mainPanel.add(new JLabel("Alphabetize"));
        mainPanel.add(scrollPane3);

        cp.add(BorderLayout.CENTER, mainPanel);

        inputButton.addActionListener(new InputButtonListener(this));
    }

    public JTextArea getInputArea() {
        return inputArea;
    }

    public JTextArea getCircularShiftArea() {
        return circularShiftArea;
    }

    public JTextArea getAlphabetizeArea() {
        return alphabetizeArea;
    }
}

