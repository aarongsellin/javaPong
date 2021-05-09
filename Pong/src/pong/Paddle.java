package pong;

import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Aaron
 */

public class Paddle extends Rectangle{
    
    int id; // To keep check of which paddle is which, since there are two.
    int yVelocity; // How fast the paddle is traveling up or down.
    int speed = 10;
    
    // Paddle constructor
    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }
    
    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1 -> {
                // Player 1 or paddle 1
                if (e.getKeyCode()==KeyEvent.VK_W) { // W
                    setYDirection(-speed); // Move up
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_S) { // S
                    setYDirection(speed); // Move down
                    move();
                }
            }
                
            case 2 -> {
                // Player 2 or paddle 2
                if (e.getKeyCode()==KeyEvent.VK_UP) { // Arrow up
                    setYDirection(-speed); // Move up
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN) { // Arrow down
                    setYDirection(speed); // Move down
                    move();
                }
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1: // Player 1 or paddle 1
                if (e.getKeyCode()==KeyEvent.VK_W) { // W
                    setYDirection(0); // Stop
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_S) { // S
                    setYDirection(0); // Stop
                    move();
                }
                break;
                
            case 2: // Player 2 or paddle 2
                if (e.getKeyCode()==KeyEvent.VK_UP) { // Arrow up
                    setYDirection(0); // Stop
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN) { // Arrow down
                    setYDirection(0); // Stop
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }
    public void move() {
        y = y + yVelocity;
    }
    // Function for drawing the paddle
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }
            
}
