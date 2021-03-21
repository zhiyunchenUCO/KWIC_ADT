package view;

import controller.KWICSystem.Controllable;
import controller.KWICSystem.MasterControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame {

    private Controllable MasterControl = new MasterControl();
    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea noiseWordArea = new JTextArea();
    private JTextArea outputArea = new JTextArea();
    private JButton processButton = new JButton("Process");
    private JButton clearButton = new JButton("Clear");
    private JLabel noiseWordsLabel = new JLabel("Noise Words");
    private JLabel outputLabel = new JLabel("Output");

    public void init() {
        // Display window attributes
        setSize(700, 500);
        setLocation(200, 100);
        setTitle("KWIC System");

        Container cp = getContentPane();

        // Construct left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(Box.createRigidArea(new Dimension(10, 50)));
        leftPanel.add(processButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        leftPanel.add(clearButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 30)));
        leftPanel.add(noiseWordsLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 100)));
        leftPanel.add(outputLabel);

        cp.add(BorderLayout.WEST, leftPanel);

        // Add three input fields to main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        
        // Add input field
        JScrollPane inputPane = new JScrollPane(
                inputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(inputPane);

        // Add noise words field
        noiseWordArea.setText("a an the and or of to be is in out by as at off");
        JScrollPane noiseWordPane = new JScrollPane(
                noiseWordArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //noiseWordArea.setEditable(false);
        mainPanel.add(noiseWordPane);

        // Add output field
        JScrollPane outputPane = new JScrollPane(
                outputArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        outputArea.setEditable(false);
        mainPanel.add(outputPane);

        cp.add(BorderLayout.CENTER, mainPanel);

        // processButton handling
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = inputArea.getText();
                String noiseWords = noiseWordArea.getText();
                try {
                    String outputString = MasterControl.transform(inputString, noiseWords);
                    outputArea.setText(outputString);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // clearButton handling
        clearButton.addActionListener(e -> clearAll());
    }

    // Clear all input, output fields after clearButton is pushed
    private void clearAll() {
        inputArea.setText("");
        outputArea.setText("");
    }
}
