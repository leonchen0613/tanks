import greenfoot.*;

/**
 * Music button on the options panel.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Music extends Actor
{
    private final GreenfootImage Volume00 = new GreenfootImage("Volume0.0.png");
    private final GreenfootImage Volume01 = new GreenfootImage("Volume0.1.png");
    private final GreenfootImage Volume10 = new GreenfootImage("Volume1.0.png");
    private final GreenfootImage Volume11 = new GreenfootImage("Volume1.1.png");
    private boolean mouseOver = false;

    /**
     * Constructor of Music.
     */
    public Music()
    {
        if (MainMenu.music)
        {
            setImage(Volume10);
        }
        else
        {
            setImage(Volume00);
        }
    }

    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            if (MainMenu.music)
            {
                setImage(Volume10);
                mouseOver = false;
            }
            else
            {
                setImage(Volume00);
                mouseOver = false;
            }
        }
        else if (!mouseOver && Greenfoot.mouseMoved(this))
        {
            if (MainMenu.music)
            {
                setImage(Volume11);
                mouseOver = true;
            }
            else
            {
                setImage(Volume01);
                mouseOver = true;
            }
        }
        if (Greenfoot.mouseClicked(this))
        {
            if (MainMenu.music)
            {
                setImage(Volume01);
                MainMenu.music = false;
            }
            else
            {
                setImage(Volume11);
                MainMenu.music = true;
            }
        }
    }
}