import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    // UI Components
    private TextField display;
    private Button[] buttons;
    private String[] labels = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "C", "0", "=", "+"
    };

    // Variables for calculation
    private double result = 0;
    private String operator = "";
    private boolean isNewInput = true;

    public Calculator() {
        // 1. Frame Setup
        setTitle("AWT Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setBackground(new Color(244, 246, 249)); // Matches your preferred hex color!

        // 2. Display Field
        display = new TextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // 3. Button Panel
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        buttons = new Button[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new Button(labels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 18));
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        add(buttonPanel, BorderLayout.CENTER);

        // 4. Window Closing Event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (isNewInput) {
                display.setText(command);
                isNewInput = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("C")) {
            display.setText("0");
            result = 0;
            operator = "";
            isNewInput = true;
        } else if (command.equals("=")) {
            calculate(Double.parseDouble(display.getText()));
            operator = "";
            isNewInput = true;
        } else {
            if (!operator.isEmpty()) {
                calculate(Double.parseDouble(display.getText()));
            } else {
                result = Double.parseDouble(display.getText());
            }
            operator = command;
            isNewInput = true;
        }
    }

    private void calculate(double n) {
        switch (operator) {
            case "+" -> result += n;
            case "-" -> result -= n;
            case "*" -> result *= n;
            case "/" -> {
                if (n != 0) result /= n;
                else display.setText("Error");
            }
            default -> result = n;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        new Calculator();
    }
}