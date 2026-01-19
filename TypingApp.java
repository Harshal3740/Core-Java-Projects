import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;

public class TypingApp extends JFrame {
    private JTextArea textArea;
    private JLabel speedLabel, accuracyLabel;
    private JButton startButton;
    private String sampleText = "The quick brown fox jumps over the lazy dog.";
    private Instant startTime;
    
    public TypingApp() {
        setTitle("Typing Speed Test");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel promptLabel = new JLabel("Type the following text:");
        JLabel sampleLabel = new JLabel("" + sampleText);
        textArea = new JTextArea(5, 40);
        speedLabel = new JLabel("Speed: 0 WPM");
        accuracyLabel = new JLabel("Accuracy: 0%");
        startButton = new JButton("Start");

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(promptLabel);
        topPanel.add(sampleLabel);
        topPanel.add(startButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(speedLabel);
        bottomPanel.add(accuracyLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> startTypingTest());
    }

    private void startTypingTest() {
        textArea.setText("");
        textArea.setEditable(true);
        textArea.requestFocus();
        startTime = Instant.now();
        
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (textArea.getText().length() >= sampleText.length()) {
                    finishTypingTest();
                }
            }
        });
    }

    private void finishTypingTest() {
        textArea.setEditable(false);
        Duration timeElapsed = Duration.between(startTime, Instant.now());
        long seconds = timeElapsed.getSeconds();
        if (seconds == 0) seconds = 1;
        int words = textArea.getText().split("\\s+").length;
        int wpm = (int) ((words / (double) seconds) * 60);
        speedLabel.setText("Speed: " + wpm + " WPM");
        
        // Calculate accuracy
        String typedText = textArea.getText();
        int correctChars = 0;
        for (int i = 0; i < Math.min(typedText.length(), sampleText.length()); i++) {
            if (typedText.charAt(i) == sampleText.charAt(i)) {
                correctChars++;
            }
        }
        int accuracy = (int) ((correctChars / (double) sampleText.length()) * 100);
        accuracyLabel.setText("Accuracy: " + accuracy + "%");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TypingApp().setVisible(true);
        });
    }
}
