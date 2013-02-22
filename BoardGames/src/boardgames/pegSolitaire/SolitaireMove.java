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
}
