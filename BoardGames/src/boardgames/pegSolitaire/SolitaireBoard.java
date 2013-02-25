package boardgames.pegSolitaire;

import java.util.*;

public class SolitaireBoard
    {

        public int BOARDSIZE = 7;

        //member variables
        ArrayList< ArrayList<SolitaireCoordinate> > coordinates = new ArrayList< ArrayList<SolitaireCoordinate> >();

        //constructors
        public SolitaireBoard()
	    {
            for (int i = 0; i < BOARDSIZE; i++)
            {
                ArrayList<SolitaireCoordinate> list_c = new ArrayList<SolitaireCoordinate>();
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    SolitaireCoordinate c = new SolitaireCoordinate();
                    list_c.add(c);
                }
                coordinates.add(list_c);
            }

		    int x = -3;
		    int y = 3;
		    for(int i = 0; i<BOARDSIZE; i++)
		    {
			    for(int j = 0; j<BOARDSIZE; j++)
			    {
				    boolean o = true;
				    if((y<-1 || y>1) && (x<-1 || x>1))
				    {
					    o = false;
				    }
				    boolean f = true;
				    if(x==0 && y==0)
				    {
					    f = false;
				    }
				
				    SolitaireCoordinate c = new SolitaireCoordinate(x,y,f,o);
				    coordinates.get(i).set(j,c);
				
				    x++;
			    }
			    y--;
			    x=-3;
		    }
	    }
        public SolitaireBoard(SolitaireBoard b)
	    {
            for (int i = 0; i < BOARDSIZE; i++)
            {
                ArrayList<SolitaireCoordinate> list_c = new ArrayList<SolitaireCoordinate>();
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    SolitaireCoordinate c = new SolitaireCoordinate();
                    list_c.add(c);
                }
                coordinates.add(list_c);
            }

		    for(int i = 0; i<BOARDSIZE; i++)
		    {
			    for(int j = 0; j<BOARDSIZE; j++)
			    {
				    coordinates.get(i).set(j,b.coordinates.get(i).get(j));
			    }
		    }
	    }

        //end constructors

        //member functions
        private boolean is_on_board(SolitaireCoordinate c)
        {
            if((c.x < -1 || c.x > 1) && (c.y < 2 || c.y > -2))
                return true;
            return false;
        }

        public boolean win()
        {
            boolean win = false;
            for (int i = 0; i < BOARDSIZE; i++)
            {
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    if (coordinates.get(i).get(j).filled && coordinates.get(i).get(j).onBoard && win == false)
                    {
                        win = true;
                    }
                    else if (coordinates.get(i).get(j).filled && coordinates.get(i).get(j).onBoard && win)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        
        boolean lose()
        {
            if(no_moves_left())
            {
                return true;
            }
            return false;
        }

        /*public ArrayList<Board> getPossibleMoves()
        {
            ArrayList<Board> moves = new ArrayList<Board>();
            for (int i = 0; i < BOARDSIZE; i++)
            {
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    ArrayList<Coordinate> jumps = coordinates[i][j].getJumps(this); //returns a list of all destinations from this src
                    for (int k = 0; k < jumps.Count; k++)
                    {
                        Board b = new Board(this);
                        b.move(coordinates[i][j], jumps[i]);
                        moves.Add(b);
                    }
                }
            }
            return moves;
        }*/

        public SolitaireCoordinate getCoordinate(int x, int y)
        {
            for (int i = 0; i < BOARDSIZE; i++)
            {
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    if (x == coordinates.get(i).get(j).x && y == coordinates.get(i).get(j).y)
                    {
                        return coordinates.get(i).get(j);
                    }
                }
            }
            return null;
        }

        //makes a move and returns true if it was successful
        public SolitaireCoordinate move(SolitaireCoordinate src, SolitaireCoordinate dest)
        {
            SolitaireCoordinate middlePeg = src.canJump(this, dest);

            if (middlePeg != null)
            {
                middlePeg.filled = false;
                src.filled = false;
                dest.filled = true;

                return middlePeg;
            }
            return null;
        }

        public SolitaireCoordinate make_move(SolitaireMove move)
        {
            SolitaireCoordinate srcPeg = this.getCoordinate(move.src.x, move.src.y);
            SolitaireCoordinate destPeg = this.getCoordinate(move.dest.x, move.dest.y);
            SolitaireCoordinate middlePeg = srcPeg.canJump(this, destPeg);

            if (middlePeg != null)
            {
                middlePeg.filled = false;
                srcPeg.filled = false;
                destPeg.filled = true;

                return middlePeg;
            }
            return null;
        }

        public void un_make_move(SolitaireMove move)
        {
            /*Coordinate srcPeg = this.getCoordinate(move.src.x, move.src.y);
            Coordinate destPeg = this.getCoordinate(move.dest.x, move.dest.y);
            Coordinate middlePeg = destPeg.canJump(this, srcPeg,false); //see if you can jump from dest to src

            if (middlePeg != null)
            {
                middlePeg.filled = false;
                srcPeg.filled = false;
                destPeg.filled = true;

                return middlePeg;
            }
            return null;*/

            SolitaireCoordinate srcPeg = this.getCoordinate(move.src.x, move.src.y);
            SolitaireCoordinate destPeg = this.getCoordinate(move.dest.x, move.dest.y);
            SolitaireCoordinate middlePeg = this.getCoordinate(move.middle.x, move.middle.y);
            middlePeg.filled = true;
            srcPeg.filled = true;
            destPeg.filled = false;
        }

        public ArrayList<SolitaireMove> get_all_possible_moves()
        {
            ArrayList<SolitaireMove> moves = new ArrayList<SolitaireMove>();

            //ArrayList<Board> moves = new ArrayList<Board>();
            for (int i = 0; i < BOARDSIZE; i++)
            {
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    if (coordinates.get(i).get(j).onBoard && !coordinates.get(i).get(j).filled) //***********remove the '!' if using getJumpsSrc()
                    {
                        ArrayList<SolitaireMove> jumps = coordinates.get(i).get(j).getJumpsDest(this); //returns a list of all moves for 'this' as src
                        moves.addAll(jumps);
                    }
                }
            }
            return moves;
        }
        
        boolean no_moves_left()
        {
            for (int i = 0; i < BOARDSIZE; i++)
            {
                for (int j = 0; j < BOARDSIZE; j++)
                {
                    if (coordinates.get(i).get(j).onBoard && !coordinates.get(i).get(j).filled) //***********remove the '!' if using getJumpsSrc()
                    {
                        ArrayList<SolitaireMove> jumps = coordinates.get(i).get(j).getJumpsDest(this); //returns a list of all moves for 'this' as src
                        if(jumps.size() > 0)
                        {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        
	
    //print the board to the terminal
    void printBoard()
    {
        for(int i = 0; i<BOARDSIZE; i++)
        {
            if(3-i >= 0)//if positive print two spaces
            {
                    System.out.print("  ");
            }
            else//if negative print one space (the '-' takes up a space)
            {
                    System.out.print(" ");
            }	
            System.out.print(3-i); //prints the y-axis
            System.out.print(" ");

            for(int j = 0; j<BOARDSIZE; j++)
            {
                    System.out.print(coordinates.get(i).get(j) + " ");

            }
            System.out.println();
        }
        System.out.print("   "); //start the axis 3 chars to the right
        for(int i = 0; i<BOARDSIZE; i++)
        {
            if(i-3 >= 0)//if positive print a space between each #
            {
                    System.out.print(" ");
            }
            System.out.print(i-3); //prints the x-axis
        }
        System.out.println();
    }
}
