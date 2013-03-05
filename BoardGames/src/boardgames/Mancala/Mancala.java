package boardgames.Mancala;

import java.util.*;
import boardgames.UnivBoard;

/**
 *
 * @author Kaitlin
 */
public class Mancala {
    MancalaBoard mancalaBoard;
    
    int playerTurn;
    int lastRow;
    
    boolean gameOver;
    boolean validMove;
    
    public void Mancala() {
        mancalaBoard = new MancalaBoard();
        gameOver = false;
        validMove = true;
        playerTurn = 0;
    }
    
    /*public void play(int r, int c) {
        if(playerTurn == r) {
            
        }
        else {
            validMove = false;
        }
    }*/
}
