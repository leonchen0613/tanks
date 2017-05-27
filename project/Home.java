import greenfoot.*;

/**
 * Home button on the scoreboard.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Home extends Actor
{
    private final GreenfootImage Home0 = new GreenfootImage("Home0.png");
    private final GreenfootImage Home1 = new GreenfootImage("Home1.png");
    private boolean mouseOver = false;

    /**
     * Constructor of Home.
     */
    public Home()
    {
        setImage(Home0);
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                setImage(Home0);
                mouseOver = false;
            }
            else if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                setImage(Home1);
                mouseOver = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                MainMenu.inGame = false;
                Battlefield.redScore = 0;
                Battlefield.blueScore = 0;
                Battlefield.existCrate = false;
                Greenfoot.setWorld(new MainMenu());
            }
        }
    }
}