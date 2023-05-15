package Catan.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public abstract class Frame {
    // Properties.
    protected JFrame f;
    // Constructor.
    public Frame() throws IOException {
        this.f = new JFrame();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add icon.
        f.setIconImage(ImageIO.read(
                new File("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\frameicon.png")
        ));

        this.f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.f.setLayout(null);

    }

     abstract public ButtonState start();

}
