package Catan.GUI;
import javax.swing.*;
import java.awt.*;

class ImagePanel extends JComponent {
    // Properties.
    private Image image;
    // Constructor.
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    // Paint image to graphics.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}