import java.awt.*;
import java.awt.event.*;

public class PomodoroTimer extends Frame implements Runnable {
    private Label timeLabel;
    private Button startButton, resetButton;
    private int timeLeft = 25 * 60; // 25 minutes in seconds
    private boolean isRunning = false;
    private Thread timerThread;

    public PomodoroTimer() {
        // 1. Setup Frame
        setTitle("AWT Focus Timer");
        setSize(300, 250);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        setBackground(new Color(255, 99, 71)); // Tomato Red

        // 2. Time Display
        timeLabel = new Label("25:00");
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        timeLabel.setForeground(Color.WHITE);
        add(timeLabel);

        // 3. Control Buttons
        startButton = new Button("Start");
        startButton.setPreferredSize(new Dimension(80, 30));
        startButton.addActionListener(e -> toggleTimer());
        
        resetButton = new Button("Reset");
        resetButton.setPreferredSize(new Dimension(80, 30));
        resetButton.addActionListener(e -> resetTimer());

        add(startButton);
        add(resetButton);

        // 4. Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                isRunning = false; // Stop thread
                dispose();
            }
        });

        setVisible(true);
    }

    private void toggleTimer() {
        if (!isRunning) {
            isRunning = true;
            startButton.setLabel("Pause");
            timerThread = new Thread(this);
            timerThread.start();
        } else {
            isRunning = false;
            startButton.setLabel("Resume");
        }
    }

    private void resetTimer() {
        isRunning = false;
        timeLeft = 25 * 60;
        updateLabel();
        startButton.setLabel("Start");
    }

    private void updateLabel() {
        int mins = timeLeft / 60;
        int secs = timeLeft % 60;
        timeLabel.setText(String.format("%02d:%02d", mins, secs));
    }

    @Override
    public void run() {
        while (isRunning && timeLeft > 0) {
            try {
                Thread.sleep(1000); // Wait 1 second
                timeLeft--;
                updateLabel();
            } catch (InterruptedException e) {
                break;
            }
        }
        if (timeLeft == 0) {
            Toolkit.getDefaultToolkit().beep(); // Alert when done
            startButton.setLabel("Done!");
            isRunning = false;
        }
    }

    public static void main(String[] args) {
        new PomodoroTimer();
    }
}
