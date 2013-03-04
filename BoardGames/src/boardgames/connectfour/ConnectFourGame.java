/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgames.connectfour;

import java.util.Scanner;

/**
 *
 * @author Jonathan
 */
public class ConnectFourGame {
    
    // Used to keep track of what color each player is playing as
    int playerColor = 1, opponentColor =2;
    int colOne, colTwo, colThr, colFor, colFiv, colSix, colSev;
    ConnectFourBoard b;
    
    public void cfGameStart() {
        b = new ConnectFourBoard();
        
        for(int row=0; row<b.numRow; row++) {
            for (int col=0; col<b.numColumn; col++) {
                b.setPiece(row, col, 0);
            }
        }
        
        b.printBoard();
        
        int turn = 1;
        
        while(true) {
            
            move(turn);
            
            b.printBoard();
            turn++;
        }
    }
    
    // Select column to place piece, and make the move
    public void move(int turn) {
        int colMove;
        int turnColor;
        String currentPlayer = "@";
        
        if((turn%2) == 0){
            turnColor=opponentColor;
        } else {
            turnColor=playerColor;
        }
        
        switch((turn%2)){
            case 0:
                currentPlayer = "O";
                break;
            case 1:
                currentPlayer = "@";
                break;
        }
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please select column to place piece player " + currentPlayer + ": ");
        colMove = scanner.nextInt();
        
        switch(colMove){
            case 1:
                if(colOne == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colOne, colMove-1, turnColor);
                    colOne++;
                }
                break;
            case 2:
                if(colTwo == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colTwo, colMove-1, turnColor);
                    colTwo++;
                }
                break;
            case 3:
                if(colThr == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colThr, colMove-1, turnColor);
                    colThr++;
                }
                break;
            case 4:
                if(colFor == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colFor, colMove-1, turnColor);
                    colFor++;
                }
                break;
            case 5:
                if(colFiv == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colFiv, colMove-1, turnColor);
                    colFiv++;
                }
                break;
            case 6:
                if(colSix == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colSix, colMove-1, turnColor);
                    colSix++;
                }
                break;
            case 7:
                if(colSev == 6) {
                    System.out.println("The column is full. Select another.");
                } else {
                    b.setPiece(colSev, colMove-1, turnColor);
                    colSev++;
                }
                break;
        }
        
    }
    
}
