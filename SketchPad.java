import java.awt.*;
import java.awt.event.*;

public class SketchPad extends Frame {
    private int lastX, lastY;

    public SketchPad() {
        // 1. Frame Setup
        setTitle("AWT Doodle Pad");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 2. Instructions Label
        Label info = new Label("Click and Drag to Draw | Close window to exit", Label.CENTER);
        info.setBackground(new Color(244, 246, 249));
        add(info, BorderLayout.SOUTH);

        // 3. Mouse Event Handling
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Record the starting point of a stroke
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Get current graphics context
                Graphics g = getGraphics();
                
                // Set drawing color and thickness
                g.setColor(Color.BLUE);
                
                // Draw a line from the last position to the current position
                g.drawLine(lastX, lastY, e.getX(), e.getY());
                
                // Update last position to the current one
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        // 4. Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SketchPad();
    }
}
