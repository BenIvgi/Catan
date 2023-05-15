package Catan;
import Catan.GUI.*;

import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        // Create the start screen.
        WelcomeFrame welcomeFrame = new WelcomeFrame();

        // Start the game loop.
        while(true){
            // Make frame visible.
            ButtonState state = welcomeFrame.start();

            // Handle Player Desicion.
            /*
            for each option:
             1.close current window
             2.open new window
             3.close when new window stops
             4.restart the loop
             */
            switch (state){
                case NEW_GAME:{
                    welcomeFrame.close();
                    GameFrame gameFrame = new GameFrame();
                    gameFrame.start();
                    gameFrame.close();
                    break;
                }
                case SETTINGS:{
                    welcomeFrame.close();
                    SettingsFrame settingsFrame = new SettingsFrame();
                    settingsFrame.start();
                    settingsFrame.close();
                    break;
                }
                case STATISTICS:{
                    welcomeFrame.close();
                    StatFrame statFrame = new StatFrame();
                    statFrame.start();
                    statFrame.close();
                    break;
                }
            }
        }
    }
}

