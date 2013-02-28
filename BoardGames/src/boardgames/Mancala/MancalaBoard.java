/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgames.Mancala;

import java.util.*;

/**
 *
 * @author Admin
 */
public class MancalaBoard {
    int num_rows;
    Pebble board[][];
    MancalaBoard() {
        num_rows = 7;
        board = new Pebble[2][num_rows];
    }
}
