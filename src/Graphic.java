import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphic extends JPanel {

    public void drawTree(){

    }

    @Override
    public void paintComponent(Graphics g) {
        // Draw Tree Here
        g.drawOval(100, 100, 25, 25);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.add(new Graphic());
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
    }

}