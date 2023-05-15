package Catan.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
public class StatFrame extends Frame{
    //properties
    private static ButtonState state;
    private JButton exitstats;
    // Constructor.
    public StatFrame() throws IOException{
        super();

        f.setTitle("Settlers Of Catan - Statistics");

        // Load Image.
        BufferedImage background_image = ImageIO.read(
                new File("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\StatScreen\\framebackground.png"));

        // Rescale Image.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Image scaledImage = background_image.getScaledInstance((int)width,(int)height,Image.SCALE_SMOOTH);
        this.f.setContentPane(new ImagePanel(scaledImage));

        Icon button_image = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\StatScreen\\buttontexture.png");

        // Create Exit Button
        this.exitstats =new JButton("Exit", button_image);
        this.exitstats.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.exitstats.setBounds(30,30 + 88*4 + 15*4,222,88);
        this.exitstats.setHorizontalTextPosition(JButton.CENTER);
        this.exitstats.setVerticalTextPosition(JButton.CENTER);
        this.exitstats.setForeground(Color.WHITE);
        this.exitstats.setIcon(button_image);
        this.f.add(this.exitstats);
    }

    // Start Window
    public ButtonState start(){
        StatFrame.state = null;
        this.f.setVisible(true);
        // Window loop
        while(StatFrame.state == null){
            this.exitstats.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(ButtonState.EXIT);
                }
            });
        }
        return StatFrame.state;
    }

    // Close window
    public void close(){
        this.f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.f.dispatchEvent(new WindowEvent(this.f, WindowEvent.WINDOW_CLOSING));
    }
    public void setState(ButtonState state){
        this.state = state;
    }
}
