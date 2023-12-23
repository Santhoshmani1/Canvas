import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CanvasFrame canvas = new CanvasFrame();
            canvas.showFrame();
        });
    }
}
