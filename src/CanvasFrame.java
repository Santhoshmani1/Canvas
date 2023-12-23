
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
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

        JPanel drawArea = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(currentColor);
                for (ArrayList<Point> stroke : strokes) {
                    for (Point point : stroke) {
                        g2d.fillRect(point.x, point.y, brushSize, brushSize);
                    }
                }
            }
        };

        drawArea.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (eraserActive) {
                    for (ArrayList<Point> stroke : strokes) {
                        stroke.removeIf(p -> p.distance(e.getPoint()) <= brushSize);
                    }
                } else {
                    if (currentStroke == null) {
                        currentStroke = new ArrayList<>();
                        strokes.add(currentStroke);
                    }
                    currentStroke.add(e.getPoint());
                }
                Point p = e.getPoint();
                drawArea.repaint(p.x - brushSize, p.y - brushSize, brushSize * 3, brushSize * 3);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                currentStroke = null;
            }
        });


        JButton brushButton = new JButton("Brush");
        brushButton.addActionListener(e -> {
            eraserActive = false;
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("../assets/img/marker.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Cursor brushCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "brush");
            drawArea.setCursor(brushCursor);
        });

        JButton eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(e -> {
            eraserActive = true;
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("../assets/img/eraser.jpg"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Cursor eraserCursor = toolkit.createCustomCursor(image, new Point(0, 0), "eraser");
            drawArea.setCursor(eraserCursor);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(brushButton);
        buttonPanel.add(eraserButton);

        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(drawArea, BorderLayout.CENTER);
    }

    public void showFrame() {
        this.setVisible(true);
    }

}