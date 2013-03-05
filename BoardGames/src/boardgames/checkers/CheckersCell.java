package boardgames.checkers;

import java.util.*;
/**
 *
 * @author Trevon
 */
public class CheckersCell {
    
    public enum RowType
    {
        ODD, EVEN, UNSET;
    }
  
    
    
    //Member variables
    private Owner owner;
    private RowType rowType;
    int x; //x position
    int y; //y position
  
    //Constructors
    public CheckersCell()
    {
        rowType = RowType.UNSET;
        owner = Owner.EMPTY;
    }
    
    public CheckersCell(Owner o)
    {
        owner = o;
    }
    
    public CheckersCell(int xPos, int yPos)
    {
        x = xPos;
        y = yPos;
    }
    
    public CheckersCell(Owner o, int xPos, int yPos)
    {
        owner = o;
        x = xPos;
        y = yPos;
    }
    
    public CheckersCell(Owner o, RowType t)
    {
        owner = o;
        rowType = t;
    }
    
    public CheckersCell(CheckersCell c)
    {
        this.owner = c.owner;
        this.rowType = c.rowType;
        this.x = c.x;
        this.y = c.y;
    }
    
    //Getters and Setters
    public void setOwner(Owner o)
    {
        this.owner = o;
    }
    
    public Owner getOwner()
    {
        return owner;
    }
    
    public void setRowType(RowType t)
    {
        this.rowType = t;
    }
    
    public RowType getRowType()
    {
        return rowType;
    }
    
    ArrayList<CheckersCell> getMoves()
    {   
        ArrayList<CheckersCell> moves = new ArrayList<>();
        
        int newX = 0;
        int newY = 0;
        
        if(this.owner == Owner.PLAYER1)
        {
            newY = this.y + 1;
        }
        else
        {
            newY = this.y - 1;
        }
         
        //Add left move
        newX = this.x - 1;
        CheckersCell newLocation1 = new CheckersCell(this);
        newLocation1.x = newX;
        newLocation1.y = newY; 
        if(isValid(this, newLocation1))
        {
            moves.add(newLocation1);
        }
        
        //Add right move
        newX = this.x + 1;
        CheckersCell newLocation2 = new CheckersCell(this);
        newLocation2.x = newX;
        newLocation2.y = newY; 
        if(isValid(this, newLocation2))
        {
            moves.add(newLocation2);
        }
        
        return moves;
    }
    
    //Checks if moving between two cells is valid
    boolean isValid(CheckersCell oldC, CheckersCell newC)
    {      
        boolean isValid = true;
        
        if(oldC.x<0 || oldC.x>8)
        {
            isValid = false;
        }
        
        if(oldC.y<0 || oldC.y>8)
        {
            isValid = false;
        }
        
        if(newC.owner != Owner.EMPTY) 
        {
            isValid = false;
        }

        return isValid;
    }
    
    //makes the calling cell equal to the parameter c
    public void updateCell(CheckersCell c)
    {
        this.owner = c.owner;
        this.rowType = c.rowType;
        this.x = c.x;
        this.y = c.y;
    }
    
    //Convert to string for console play
    @Override
    public String toString()
    {
        if(owner == Owner.EMPTY)
        {
            return "~";
        }
        else if(owner == Owner.PLAYER1)
        {
            return "B";
        }
        else
        {
            return "R";
        }
    }
}
