/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgames.pegSolitaire;

/**
 *
 * @author Tom
 */
public class SolitaireMove
{
    public SolitaireCoordinate src;
    public SolitaireCoordinate dest;
    public SolitaireCoordinate middle;

    public SolitaireMove(SolitaireCoordinate s, SolitaireCoordinate d, SolitaireCoordinate m)
    {
        src = s;
        dest = d;
        middle = m;
    }
    
    public String toString()
    {
        String source;
        String destination;
        String mid;
        
        if(src.x>=0)
        {
            source = " " + src.toString();
        }
        else
        {
            source = src.toString();
        }
        if(dest.x>=0)
        {
            destination = " " + dest.toString();
        }
        else
        {
            destination = dest.toString();
        }
        if(middle.x>=0)
        {
            mid = " " + middle.toString();
        }
        else
        {
            mid = middle.toString();
        }
        
        return source + destination + mid;
    }
}
