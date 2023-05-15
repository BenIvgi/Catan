package Catan.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
public class SettingsFrame extends Frame{
    // Properties.
    private static ButtonState state;
    private JButton exitsettings;
    // Constructor.
    public SettingsFrame() throws IOException{
        super();

        f.setTitle("Settlers Of Catan - Settings");

        // Load Image.
        BufferedImage background_image = ImageIO.read(
                new File("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\SettingsScreen\\framebackground.png"));

        // Rescale Image.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Image scaledImage = background_image.getScaledInstance((int)width,(int)height,Image.SCALE_SMOOTH);
        this.f.setContentPane(new ImagePanel(scaledImage));

        Icon button_image = new ImageIcon("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\SettingsScreen\\buttontexture.png");

        // Exit button.
        this.exitsettings =new JButton("Exit", button_image);
        this.exitsettings.setFont(new Font("Georgia", Font.ITALIC, 16));
        this.exitsettings.setBounds(30,30 + 88*4 + 15*4,222,88);
        this.exitsettings.setHorizontalTextPosition(JButton.CENTER);
        this.exitsettings.setVerticalTextPosition(JButton.CENTER);
        this.exitsettings.setForeground(Color.WHITE);
        this.exitsettings.setIcon(button_image);
        this.f.add(this.exitsettings);
    }

    // Start Window
    public ButtonState start(){
        SettingsFrame.state = null;
        this.f.setVisible(true);
        // Window Loop
        while(SettingsFrame.state == null){
            this.exitsettings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(ButtonState.EXIT);
                }
            });
        }
        return SettingsFrame.state;
    }

    // Close Window
    public void close(){
        this.f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.f.dispatchEvent(new WindowEvent(this.f, WindowEvent.WINDOW_CLOSING));
    }
    public void setState(ButtonState state){
        this.state = state;
    }
}
