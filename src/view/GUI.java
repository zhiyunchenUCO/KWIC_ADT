package view;

import controller.KWICSystem.KWICControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame {

    private KWICControl KWICControl = new KWICControl();

    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea noiseWordArea = new JTextArea();
    private JTextArea outputArea = new JTextArea();
    private JButton inputButton = new JButton("Input");
    private JButton clearButton = new JButton("Clear");
    private JLabel noiseWordsLabel = new JLabel("noise words");
    private JLabel outputLabel = new JLabel("Output");

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
        leftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        leftPanel.add(noiseWordsLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 100)));
        leftPanel.add(outputLabel);

        cp.add(BorderLayout.WEST, leftPanel);

        // Add three areas to the center of the display window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        JScrollPane scrollPane1 = new JScrollPane(
                inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane1);

        noiseWordArea.setText("a an the and or of to be is in out by as at off");
        JScrollPane scrollPane2 = new JScrollPane(
                noiseWordArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //noiseWordArea.setEditable(false);
        mainPanel.add(scrollPane2);

        JScrollPane scrollPane3 = new JScrollPane(
                outputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputArea.setEditable(false);
        mainPanel.add(scrollPane3);

        cp.add(BorderLayout.CENTER, mainPanel);

        // Click on the "input" button to transform the lines
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputArea.getText();
                String noiseWords = noiseWordArea.getText();
                try {
                    String outputString = KWICControl.transform(inputString, noiseWords);
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
