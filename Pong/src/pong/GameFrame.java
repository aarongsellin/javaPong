package pong;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Aaron
 */

public class GameFrame extends JFrame { // Extend JFrame so we can treat GameFrame as a JFrame class
    
    GamePanel panel = new GamePanel();
    
    // GameFrame constructor
    GameFrame() {
        panel = new GamePanel();
        
        // Buuunch of settings for the panel
        this.add(panel);
        this.setTitle("Pong game by Aaron"); // Set the title
        this.setResizable(false);            // Window can not be resized
        this.setBackground(Color.BLACK);     // Set the background color
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program if closed, duh
        this.pack(); // Pack the panel
        this.setVisible(true); // Make the window visible
        this.setLocationRelativeTo(null); // Make the window appear in the middle of the screen
    }
}
