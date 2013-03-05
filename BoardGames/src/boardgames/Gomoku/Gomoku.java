package boardgames.Gomoku;

import boardgames.UnivBoard;
import java.util.ArrayList;
//Crabb
public class Gomoku 
{
    UnivBoard UBoard;
    
    int turnCounter = 0;
    boolean gameOver = false;
    boolean wrongMove = false;
    
    //to keep track of recent past moves for undo
    ArrayList<Integer> recentX;
    ArrayList<Integer> recentY;
    
    public void initGomoku()
    {
        do
        { 
            ++turnCounter;
        
            int row=0;
            int col=0;
            char clr='+'; //to relay which color is playing currently
            
            //needs to check for option buttons
                 //ie: undo, restart, quit
        
            //getMove()
                //push values to row, col
            
            //checkMove()
            //alters value of wrongMove to true if condtions met
        
            if(!wrongMove)
            {
                //UBoard.play(row,col);
                    //recentX.addElement(col); //col value determines x location
                    //recentY.addElement(row); // row value determines y location
                
                gameOver = checkWin(row,col,clr);
            }
        } while (!gameOver);
           
    }
    
    boolean checkWin(int row, int col, char clr)
    {
        int tempR,tempC;
	int currRow = row;
	int currCol = col;

	int UpperLeft	=0;
	int Left		=0;
	int LowerLeft	=0;
	int Down		=0;
	int LowerRight	=0;
	int Right		=0;
	int	UpperRight	=0;
	int Up			=0;

	//read in last played location via col,row
	//increment and decrement to check all 8 values around it
		//if same color, continue in that direction

	//UPPER LEFT
		tempR = currRow;
		tempC = currCol;
		
		while((tempR > 0 && tempC > 0)&&(tempR>currRow-4)&&(tempC>currCol-4))
		{	
			tempR -=1;
			tempC -=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            UpperLeft++;
                        }
			else
                        {
				break;
                        }
		}
	//UP
		tempR = currRow;
		tempC = currCol;
		
		while((tempR > 0)&&(tempR>currRow-4))
		{	
			tempR -=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            Up++;
                        }
			else
                        {
                            break;
                        }
		}
	//UPPER RIGHT
		tempR = currRow;
		tempC = currCol;
		
		while((tempR > 0 && tempC < 15)&&(tempR>currRow-4)&&(tempC<currCol+4))
		{	
			tempR -=1;
			tempC +=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            UpperRight++;
                        }
			else
                        {
                            break;
                        }	
		}
	//LEFT
		tempR = currRow;
		tempC = currCol;
		
		while((tempC > 0)&&(tempC>currCol-4))
		{	
			tempC -=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            Left++;
                        }
			else
                        {
                            break;
                        }
			
		}
	//LOWER LEFT
		tempR = currRow;
		tempC = currCol;
		
		while((tempR < 15 && tempC > 0)&&(tempR<currRow+4)&&(tempC>currCol-4))
		{	
			tempR +=1;
			tempC -=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            LowerLeft++;
                        }
			else 
                        {
                            break;
                        }
			
		}
	//DOWN
		tempR = currRow;
		tempC = currCol;
		while((tempR < 15)&&(tempR<currRow+4))
		{	
			tempR +=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            Down++;
                        }
			else
                        {
                            break;
                        }
			
		}
	//LOWER RIGHT
		tempR = currRow;
		tempC = currCol;
		
		while((tempR < 15 && tempC < 15)&&(tempR<currRow+4)&&(tempC<currCol+4))
		{	
			tempR +=1;
			tempC +=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            LowerRight++;
                        }
			else
                        {
                            break;
                        }
			
		}
	//RIGHT
		tempR = currRow;
		tempC = currCol;
		
		while((tempC < 15)&&(tempC<currCol+4))
		{	
			tempC +=1;

			if(UBoard.getSquareState(tempR,tempC) == clr)
                        {
                            Right++;
                        }
			else
                        {
                            break;
                        }
			
		}

	if(Up+Down >= 4) {return true;}
	else if(Left+Right >= 4) {return true;}
	else if(UpperLeft+LowerRight >= 4) {return true;}
	else if(UpperRight+LowerLeft >= 4){return true;}
	else {return false;}
    }
    
    void undo() 
    {
        int x=1;
        //x corresponds to desired number of turns undone
            //can be made into paramter future development
        
        for(int i=0;i<x;++i)
        {
            UBoard.unplay(recentX.get(recentX.size()-1),recentY.get(recentY.size()-1));
        }
    }
    
    void quit()
    {
        UBoard.empty();
        
        //exit to main menu, close Gomoku GUI screen
    }
    
    
    
    
}
