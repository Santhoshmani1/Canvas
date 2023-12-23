
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasFrame extends JFrame {
    private ArrayList<ArrayList<Point>> strokes = new ArrayList<>();
    private ArrayList<Point> currentStroke;
    private Color currentColor = Color.BLACK;
    private int brushSize = 5;
    private boolean eraserActive = false;

    public CanvasFrame() {
        super("Canvas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(true);

    }

    public void showFrame() {
        this.setVisible(true);
    }

}