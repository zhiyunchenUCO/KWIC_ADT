package view;

import controller.KWICSystem.KWIC;
import controller.KWICSystem.Pipeline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyWindow extends JFrame {

    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea outputArea = new JTextArea();
    private JButton inputButton = new JButton("Input");
    private JButton clearButton = new JButton("Clear");

    public void init() {
        setSize(700, 500);
        setLocation(200, 100);
        setTitle("KWIC System");

        Container cp = getContentPane();

        // Add two buttons to the left part of the display window
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        leftPanel.add(inputButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        leftPanel.add(clearButton);

        cp.add(BorderLayout.WEST, leftPanel);

        // Add two areas to the center of the display window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        JScrollPane scrollPane1 = new JScrollPane(
                inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(
                outputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane1);
        outputArea.setEditable(false);
        mainPanel.add(scrollPane2);

        cp.add(BorderLayout.CENTER, mainPanel);

        // Click on the "input" button to transform the lines
        inputButton.addActionListener(new ActionListener() {
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
        });

        // Click on the "Clear" button to clear input and output
        clearButton.addActionListener(e -> clearAll());
    }

    private  void clearAll() {
        inputArea.setText("");
        outputArea.setText("");
    }
}
