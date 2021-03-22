package view;

import controller.KWICSystem.Controllable;
import controller.KWICSystem.MasterControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

public class GUI extends JFrame {

    private Controllable masterControl = new MasterControl();
//    private JTextArea inputArea = new JTextArea("Type in your lines here");
    private JTextArea inputArea = new JTextArea("");
    private JTextArea noiseWordArea = new JTextArea();
    private JTextArea outputArea = new JTextArea();
    private JButton processButton = new JButton("Process");
    private JButton clearButton = new JButton("Clear");
    private JButton loadButton = new JButton("Load");
    private JButton testButton = new JButton("Test");
//    private JLabel noiseWordsLabel = new JLabel("Noise Words");
//    private JLabel outputLabel = new JLabel("Output");

    public void init() {
        // Display window attributes
        setSize(700, 500);
//        setLocation(200, 100);
        setLocationRelativeTo(null);
        setTitle("KWIC System (Shared Data/OO)");

        Container cp = getContentPane();

        // Construct left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        leftPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        leftPanel.add(processButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        leftPanel.add(clearButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        leftPanel.add(loadButton);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 20)));
        leftPanel.add(testButton);

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
                    String outputString = masterControl.transform(inputString, noiseWords);
                    outputArea.setText(outputString);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // clearButton handling
        clearButton.addActionListener(e -> clearAll());

        // loadButton handling
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String testData = "";
                testData = masterControl.loadTestFile();
                inputArea.setText(testData);
            }
        });

        // testButton handling
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Set up test data
                String testData = masterControl.loadTestFile();
                inputArea.setText(testData);

                String inputString = inputArea.getText();
                String noiseWords = noiseWordArea.getText();

                String[] result = masterControl.runBenchmark(inputString, noiseWords);

                String outputString = result[0];
                String iterations = result[1];
                String timeElapsed = result[2];

                outputArea.setText(outputString);

                // Setup results popup
                String titleBar = "Execution Time";
                String infoMessage = String.valueOf(iterations) + " iterations in " + String.valueOf(timeElapsed) + " ms" ;

//                result = masterControl.runBenchmark();
                JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    // Clear all input, output fields after clearButton is pushed
    private void clearAll() {
        inputArea.setText("");
        outputArea.setText("");
    }
}
