package pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Aaron
 */

public class GamePanel extends JPanel implements Runnable {
    
    // Game settings
    // ( Fun fact: Window size is based of a real ping pong table )
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    // Screen size
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    // Ball
    static final int BALL_DIAMETER = 20;  // Ball ´diamater
    // Paddle
    static final int PADDLE_WIDTH = 25;   // Width of a paddle
    static final int PADDLE_HEIGHT = 100; // Height of a paddle
    // Instances
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;
    
    // Game panel constructor
    GamePanel() {
        newPaddles(); // Create paddles
        newBall();    // Create ball
        score = new Score(GAME_WIDTH,GAME_HEIGHT); // Create the scoreboard
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        
        gameThread = new Thread(this); // Create a game thread
        gameThread.start(); // Start the game thread
    }
    // Function for creating a ball
    public void newBall() {
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
    }
    // Function for creating paddles
    public void newPaddles() {
        paddle1 = new Paddle(0, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH, (GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    // Function for painting things on to the screen
    // It basically just puts everything that I want to draw on the screen
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics); // Draw components
        g.drawImage(image, 0, 0, this);
    }
    // Function for drawing things to the screen
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    // Function for moving everything at the end of each frame
    public void move() {
        // Fixes paddle sluggishnes and smoothes their movement
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    // Function for checking collissions
    public void checkCollision() {
        // Stops ball from moving off screen
        if (ball.y <= 0) ball.setYDirection(-ball.yVelocity); // Go in the opposite direction
        if (ball.y >= GAME_HEIGHT-BALL_DIAMETER) ball.setYDirection(-ball.yVelocity); // Go in the opposite direction
        
        // Makes the ball bounce of paddles
        // Paddle 1
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Increase ball speed
            if(ball.yVelocity > 0) {
                ball.yVelocity++;
            } else ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        // Paddle 2
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; // Increase ball speed
            if(ball.yVelocity > 0) {
                ball.yVelocity++;
            } else ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        
        // Stops paddles from moving of screen
        // Paddle 1
        if (paddle1.y <= 0) paddle1.y = 0;
        if (paddle1.y >= GAME_HEIGHT-PADDLE_HEIGHT) paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
        // Paddle 2
        if (paddle2.y <= 0) paddle2.y = 0;
        if (paddle2.y >= GAME_HEIGHT-PADDLE_HEIGHT) paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
        
        // Check for score
        if (ball.x <= 0) {
            score.player2++;
            reset();
        }
        if (ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            reset();
        }
        
    }
    // Function for resetting the game after a player has scored
    public void reset() {
        newPaddles();
        newBall();
    }
    // Function for running the game
    public void run() {
        // Game loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0; // Works as FPS
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        // Loop
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >=1) { 
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    // Class for handeling user input
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e)  {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
 