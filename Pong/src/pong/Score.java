package pong;

import java.awt.*;

/**
 *
 * @author Aaron
 */

public class Score extends Rectangle {
    
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1; // This value holds the score of player one
    int player2; // This vale holds the score of player two
    
    // Score constructor
    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        // Just keep track of how big the window is so we can draw text easier
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas",Font.PLAIN,60));
        g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
        g.drawString(String.valueOf(player1), (GAME_WIDTH/2)-85, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH/2)+20, 50);
    }
}
