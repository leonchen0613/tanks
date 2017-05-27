import greenfoot.*;
import java.awt.Color;

/**
 * Score of the red tank.
 * 
 * @author Leon Chen
 * @version Final
 */
public class ScoreRed extends Actor
{
    /**
     * Constructor of ScoreRed.
     */
    public ScoreRed(int score)
    {
        setImage(new GreenfootImage(score + "", 60, Color.BLACK, null));
    }
}