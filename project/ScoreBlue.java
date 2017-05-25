import greenfoot.*;
import java.awt.Color;

/**
 * Score of the blue tank.
 * 
 * @author Leon Chen
 * @version Final
 */
public class ScoreBlue extends Actor
{
    /**
     * Constructor of ScoreBlue.
     */
    public ScoreBlue(int score)
    {
        setImage(new GreenfootImage(score + "", 60, Color.BLACK, null));
    }
}