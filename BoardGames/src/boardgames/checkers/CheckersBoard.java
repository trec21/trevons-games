/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgames.checkers;

import java.util.*;
/**
 *
 * @author Trevon
 */
public class CheckersBoard {
    
    /* 
     * The board alternates between light and dark cells for every row. 
     * The bottom right cell is always light and pieces live on dark squares.
     * The indexing starts at the top and Player1 is at the bottom. 
     */
    public Integer BOARDSIZE = 8;
    ArrayList< ArrayList<CheckersCell> > board = new ArrayList<>();
    
    
    public CheckersBoard()
    {
        //Create nxn board
        for(int i = 0; i < BOARDSIZE; i++)
        {
            ArrayList<CheckersCell> currentRow = new ArrayList<>();
            for(int j =0; j< BOARDSIZE; j++) 
            {
                currentRow.add(new CheckersCell(Owner.EMPTY,i,j));
            }
            
            board.add(currentRow);
        }
        
        //Place 12 pieces on board for player 1
        for(int i = 0; i<3; i++)
        {
            boolean playerCell;
            
            if(i%2==0)
            {
                playerCell = false;
            }
            else
            {
                playerCell = true;
            }
            
            for(CheckersCell cell: board.get(i))
            {
                if(playerCell) 
                {
                    cell.setOwner(Owner.PLAYER2);
                    Owner.PLAYER2.pieces.add(cell);
                }
                playerCell ^= true;
            }
        }
        
        //Place 12 pieces on board for player 2
        for(int i = 5; i<8; i++)
        {
            boolean playerCell;
            
            if(i%2==0)
            {
                playerCell = false;
            }
            else
            {
                playerCell = true;
            }
            
            for(CheckersCell cell: board.get(i))
            {
                if(playerCell) 
                {
                    cell.setOwner(Owner.PLAYER1);
                    Owner.PLAYER1.pieces.add(cell);
                }
                playerCell ^= true;
            }
        }
        
        for(ArrayList<CheckersCell> row: board)
        {
            for(CheckersCell cell: row)
            {
                System.out.print(cell.toString());
            }
            System.out.print("\n");
        }
    }
    
    
    
    public boolean makeMove(CheckersCell source, CheckersCell dest)
    {
        return true;
    }
    
    public boolean jump(CheckersCell source, CheckersCell dest)
    {
        return true;
    }
    
    /*public static void main(String[] args) 
    {
         System.out.println("Start");
         CheckersBoard b = new CheckersBoard();
    }*/
}
