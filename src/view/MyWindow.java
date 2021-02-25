package view;

import controller.InputButtonListener;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {

    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea outputArea = new JTextArea();
    private JButton inputButton = new JButton("Input");



    public void init() {
        setSize(700, 500);
        setLocation(200, 100);
        setTitle("KWIC System");

        Container cp = getContentPane();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2));

        JScrollPane scrollPane1 = new JScrollPane(
                inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(
                outputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(inputButton);
        mainPanel.add(scrollPane1);
        outputArea.setEditable(false);
        mainPanel.add(new JLabel("Output text"));
        mainPanel.add(scrollPane2);

        cp.add(BorderLayout.CENTER, mainPanel);

        inputButton.addActionListener(new InputButtonListener(this));
    }

    public JTextArea getInputArea() {
        return inputArea;
    }

    public JTextArea getOutputArea() {
        return outputArea;
    }


}

