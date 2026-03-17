import java.awt.*;
import java.awt.event.*;

public class AttendanceSystem extends Frame implements ActionListener {
    private TextField nameInput;
    private List studentList;
    private List presentList;
    private Button addButton, markButton, clearButton;

    public AttendanceSystem() {
        // 1. Frame Setup
        setTitle("Class Attendance System");
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(244, 246, 249)); // Soft background color

        // 2. Header
        Label header = new Label("Student Attendance Tracker", Label.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        // 3. Input Panel (Top)
        Panel inputPanel = new Panel(new FlowLayout());
        inputPanel.add(new Label("Enter Name:"));
        nameInput = new TextField(20);
        inputPanel.add(nameInput);
        
        addButton = new Button("Add Student");
        addButton.addActionListener(this);
        inputPanel.add(addButton);
        add(inputPanel, BorderLayout.SOUTH);

        // 4. Lists Panel (Center)
        Panel listPanel = new Panel(new GridLayout(1, 2, 10, 10));
        
        // Left Side: All Students
        Panel leftPanel = new Panel(new BorderLayout());
        leftPanel.add(new Label("All Students:", Label.CENTER), BorderLayout.NORTH);
        studentList = new List();
        leftPanel.add(studentList, BorderLayout.CENTER);
        
        markButton = new Button("Mark Present >>");
        markButton.addActionListener(this);
        leftPanel.add(markButton, BorderLayout.SOUTH);
        
        // Right Side: Present Students
        Panel rightPanel = new Panel(new BorderLayout());
        rightPanel.add(new Label("Present Today:", Label.CENTER), BorderLayout.NORTH);
        presentList = new List();
        rightPanel.add(presentList, BorderLayout.CENTER);
        
        clearButton = new Button("Clear Attendance");
        clearButton.addActionListener(this);
        rightPanel.add(clearButton, BorderLayout.SOUTH);

        listPanel.add(leftPanel);
        listPanel.add(rightPanel);
        add(listPanel, BorderLayout.CENTER);

        // 5. Window Close Event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameInput.getText().trim();
            if (!name.isEmpty()) {
                studentList.add(name);
                nameInput.setText(""); // Clear input after adding
            }
        } else if (e.getSource() == markButton) {
            String selected = studentList.getSelectedItem();
            if (selected != null) {
                presentList.add(selected);
                studentList.remove(selected); // Move from left to right
            }
        } else if (e.getSource() == clearButton) {
            presentList.removeAll();
        }
    }

    public static void main(String[] args) {
        new AttendanceSystem();
    }
}
