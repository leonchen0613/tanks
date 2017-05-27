import greenfoot.*;

/**
 * Play button on the main menu.
 * 
 * @author Leon Chen
 * @version Final
 */
public class PlayButton extends Actor
{
    private final GreenfootImage Button0 = new GreenfootImage("Play0.jpg");
    private final GreenfootImage Button1 = new GreenfootImage("Play1.jpg");
    private boolean mouseOver = false;

    /**
     * Constructor of PlayButton.
     */
    public PlayButton()
    {
        setImage(Button0);
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                setImage(Button0);
                mouseOver = false;
            }
            else if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                setImage(Button1);
                mouseOver = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                MainMenu.inGame = true;
                Greenfoot.setWorld(new Battlefield());
            }
        }
    }
}