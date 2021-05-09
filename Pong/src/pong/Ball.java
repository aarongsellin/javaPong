package pong;

import java.awt.*;
import java.util.*;

/**
 *
 * @author Aaron
 */

public class Ball extends Rectangle{
    
    Random random;  // Random value
    int xVelocity; // How fast the ball is going to move side to side.
    int yVelocity; // How fast the ball is going to move up and down.
    int speed = 2; // Speed multiplier
    
    // Ball constructor
    Ball(int x, int y, int width, int height) {
        super(x,y,width,height);
        random = new Random();
        
        // Make the ball go in a random direction
        // x value
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0) randomXDirection--;
        setXDirection(randomXDirection*speed);
        // y value
        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0) randomYDirection--;
        setYDirection(randomYDirection*speed);
    }
    // Function for setting the x direction of a ball
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }
    // Function for setting the y direction of a ball
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }
    // Move the ball
    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
    // Draw the ball
    public void draw(Graphics g) {
        g.setColor(Color.WHITE); // White ball
        g.fillRect(x, y, width, height); // Make It a square
    }
}
