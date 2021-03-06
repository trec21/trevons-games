package boardgames.checkers;

import java.util.*;
//import java.lang.Math;

/**
 *
 * @author Trevon
 */
public class CheckersCell {

    public enum RowType {

        ODD, EVEN, UNSET;
    }
    //Member variables
    private Owner owner;
    private RowType rowType;
    int x; //x position
    int y; //y position

    //Constructors
    public CheckersCell() {
        owner = Owner.EMPTY;
    }

    public CheckersCell(Owner o) {
        owner = o;
    }

    public CheckersCell(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    public CheckersCell(Owner o, int xPos, int yPos) {
        owner = o;
        x = xPos;
        y = yPos;
    }

    public CheckersCell(Owner o, RowType t) {
        owner = o;
        rowType = t;
    }

    public CheckersCell(CheckersCell c) {
        this.owner = c.owner;
        this.rowType = c.rowType;
        this.x = c.x;
        this.y = c.y;
    }

    //Getters and Setters
    public void setOwner(Owner o) {
        this.owner = o;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setRowType(RowType t) {
        this.rowType = t;
    }

    public RowType getRowType() {
        return rowType;
    }

    //Returns an array of the moves available from a given cell
    public ArrayList<CheckersMove> getMoves() {
        ArrayList<CheckersMove> moves = new ArrayList<>();

        int newX = 0;
        int newY = 0;

        if (this.owner == Owner.PLAYER2) {
            newY = this.y + 1;
        } else {
            newY = this.y - 1;
        }

        //Add left move
        newX = this.x - 1;
        CheckersCell newLocation1 = new CheckersCell();
        newLocation1.x = newX;
        newLocation1.y = newY;
        CheckersMove m1 = new CheckersMove(this, newLocation1);
        if (isValidMove(m1)) {
            moves.add(m1);
        }

        //Add right move
        newX = this.x + 1;
        CheckersCell newLocation2 = new CheckersCell();
        newLocation2.x = newX;
        newLocation2.y = newY;
        CheckersMove m2 = new CheckersMove(this, newLocation2);
        if (isValidMove(m2)) {
            moves.add(m2);
        }

        return moves;
    }

    public CheckersCell getMid(CheckersCell src, CheckersCell dest) {
        int xPos = (src.x + dest.x) / 2;
        int yPos = (src.y + dest.y) / 2;
        CheckersCell mid = CheckersBoard.board.get(yPos).get(xPos);
        return mid;
    }

    public ArrayList<CheckersMove> getJumps() {
        
        ArrayList<CheckersMove> jumps = new ArrayList();
        
        Owner opponent;
        if (owner == Owner.PLAYER1) {
            opponent = Owner.PLAYER2;
        } else if (owner == Owner.PLAYER2) {
            opponent = Owner.PLAYER1;
        } else {
            return new ArrayList<CheckersMove>();
        }

        ArrayList<CheckersCell> jumpDest = new ArrayList();
        jumpDest.add(new CheckersCell(this.x + 2, this.y + 2));
        jumpDest.add(new CheckersCell(this.x - 2, this.y + 2));
        jumpDest.add(new CheckersCell(this.x + 2, this.y - 2));
        jumpDest.add(new CheckersCell(this.x - 2, this.y - 2));

        //delete adjacent cells that are off the board
        Iterator<CheckersCell> it = jumpDest.iterator();
        while (it.hasNext()) {
            if (!isValidCell(it.next())) {
                it.remove();
            }
        }
        
        //add jumps that have the correct opponent
        it = jumpDest.iterator();
        while (it.hasNext())
        {
            CheckersCell j = it.next();
            CheckersCell mid = getMid(this, j);
            if(mid.owner == opponent)
            {
                CheckersMove m = new CheckersMove(this,mid,j);
                jumps.add(m);
            }
        }
        
        return jumps;
    }

    public static boolean isValidCell(CheckersCell c) {
        boolean isValid = true;

        if (c.x < 0 || c.x > 8) {
            isValid = false;
        }

        return isValid;
    }
    //Checks if moving between two cells is valid

    public static boolean isValidMove(CheckersMove m) {
        boolean isValid = true;
        
        if(m.dest.x > m.source.x + 2)
        {
            isValid = false;
        }
        
        if(m.dest.x < m.source.x - 2)
        {
            isValid = false;
        }
        
        if(m.dest.y == m.source.y)
        {
            isValid = false;
        }
        
        if (m.source.owner == Owner.EMPTY) {
            isValid = false;
        }

        if (m.source.x < 0 || m.source.x > 8) {
            isValid = false;
        }

        if (m.dest.x < 0 || m.dest.x > 8) {
            isValid = false;
        }

        if (m.dest.owner != Owner.EMPTY) {
            isValid = false;
        }

        return isValid;
    }

    //makes the calling cell equal to the parameter c
    public void updateCell(CheckersCell c) {
        this.owner = c.owner;
        this.rowType = c.rowType;
        this.x = c.x;
        this.y = c.y;
    }

    //Convert to string for console play
    @Override
    public String toString() {
        if (owner == Owner.EMPTY) {
            return "~";
        } else if (owner == Owner.PLAYER1) {
            return "B";
        } else {
            return "R";
        }
    }
}
