import greenfoot.*;

/**
 * The victory screen.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Victory extends World
{
    private final GreenfootImage redVictory = new GreenfootImage("RedWins.jpg");
    private final GreenfootImage blueVictory = new GreenfootImage("BlueWins.jpg");
    public boolean winner;

    /**
     * Constructor of Victory.
     */
    public Victory()
    {
        super(1200, 800, 1);
        prepare();
    }

    /**
     * Prepares the world for the start of the program.
     */
    private void prepare()
    {
        addObject(new Home(), 1125, 725);
    }
    
    /**
     * Displays the winner.
     */
    public void winningScreen()
    {
        if (winner)
        {
            setBackground(redVictory);
        }
        else
        {
            setBackground(blueVictory);
        }
    }
}