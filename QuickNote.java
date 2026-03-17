import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class QuickNote extends Frame implements ActionListener {
    private TextArea textArea;
    private Button saveButton, clearButton;

    public QuickNote() {
        // 1. Frame Setup
        setTitle("AWT Quick Note");
        setSize(400, 450);
        setLayout(new BorderLayout());
        setBackground(new Color(244, 246, 249));

        // 2. Text Area (The main typing space)
        textArea = new TextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(textArea, BorderLayout.CENTER);

        // 3. Button Panel
        Panel buttonPanel = new Panel();
        saveButton = new Button("Save to File");
        clearButton = new Button("Clear All");

        saveButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 4. Window Closing
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveNote(textArea.getText());
        } else if (e.getSource() == clearButton) {
            textArea.setText("");
        }
    }

    private void saveNote(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("note.txt"))) {
            writer.write(content);
            // Show a simple dialog box using a Frame
            final Frame dialog = new Frame();
            System.out.println("Note saved successfully to note.txt");
            Toolkit.getDefaultToolkit().beep(); 
        } catch (IOException ex) {
            System.out.println("An error occurred while saving.");
        }
    }

    public static void main(String[] args) {
        new QuickNote();
    }
}
