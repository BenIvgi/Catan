package Catan.GUI;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.awt.image.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;
public class WelcomeFrame extends Frame{
    // Constants.
    private static final int BUTTON_TEXT_SIZE = 20;
    private static final int BUTTON_HEIGHT = 88;
    private static final int BUTTON_WIDTH = 278;
    private static final int BUTTON_OFFSET = 120;
    private static final int BUTTON_MID_OFFSET = 139;
    // Declare properties.
    private ButtonState state;
    private JButton start_game;
    private JButton settings;
    private JButton statistics;

    // Constructor.
    public WelcomeFrame() throws IOException{
        super();

        // Set window title.
        f.setTitle("Settlers Of Catan");

        // Load Image.
        BufferedImage background_image = ImageIO.read(
                new File("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\WelcomeScreen\\framebackground.png"));

        // Rescale Image.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Image scaledImage = background_image.getScaledInstance((int)width,(int)height,Image.SCALE_SMOOTH);
        this.f.setContentPane(new ImagePanel(scaledImage));

        // Load button image.
        Icon button_image =
                new ImageIcon("C:\\Users\\User\\IdeaProjects\\Catan\\src\\Catan\\GUI\\Images\\WelcomeScreen\\buttontexture.png");

        // Init window state.
        this.state = null;


        // Set start game button.
        this.start_game =new JButton("Start New Game", button_image);
        this.start_game.setFont(new Font("Georgia", Font.ITALIC, BUTTON_TEXT_SIZE));
        this.start_game.setBounds((int)width/2 - BUTTON_MID_OFFSET,(int)height/2,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.start_game.setHorizontalTextPosition(JButton.CENTER);
        this.start_game.setVerticalTextPosition(JButton.CENTER);
        this.start_game.setForeground(Color.WHITE);
        this.start_game.setIcon(button_image);
        this.f.add(this.start_game);

        // Set settings button.
        this.settings =new JButton("Settings", button_image);
        this.settings.setFont(new Font("Georgia", Font.ITALIC, BUTTON_TEXT_SIZE));
        this.settings.setBounds((int)width/2 - BUTTON_MID_OFFSET,(int)height/2 + BUTTON_OFFSET,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.settings.setHorizontalTextPosition(JButton.CENTER);
        this.settings.setVerticalTextPosition(JButton.CENTER);
        this.settings.setForeground(Color.WHITE);
        this.f.add(this.settings);

        // Set statistics button.
        this.statistics =new JButton("Statistics", button_image);
        this.statistics.setFont(new Font("Georgia", Font.ITALIC, BUTTON_TEXT_SIZE));
        this.statistics.setBounds((int)width/2 - BUTTON_MID_OFFSET,(int)height/2 + BUTTON_OFFSET*2,BUTTON_WIDTH,BUTTON_HEIGHT);
        this.statistics.setHorizontalTextPosition(JButton.CENTER);
        this.statistics.setVerticalTextPosition(JButton.CENTER);
        this.statistics.setForeground(Color.WHITE);
        this.statistics.setIcon(button_image);
        this.f.add(this.statistics);

        // Show a message before closing window.
        this.f.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (state != null) return;
                JOptionPane.showMessageDialog(f,
                        "Game is closing", "Close Window", 1);
                System.exit(0);
            }
        });
    }

    // Start window.
    public ButtonState start(){
        // Init window state.
        this.state = null;

        // Set window to be visible.
        this.f.setVisible(true);

        // Window loop - Set window state to be any state pressed and wait until the state was changed.
        while(this.state == null){
            this.start_game.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(ButtonState.NEW_GAME);
                }
            });
            this.settings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(ButtonState.SETTINGS);
                }
            });
            this.statistics.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setState(ButtonState.STATISTICS);
                }
            });
        }
        return this.state;
    }

    // Close window.
    public void close(){
        this.f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.f.dispatchEvent(new WindowEvent(this.f, WindowEvent.WINDOW_CLOSING));
    }

    // Set window state.
    public void setState(ButtonState state){
        this.state = state;
    }



}
