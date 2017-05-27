import greenfoot.*;

/**
 * Options button on the main menu.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Options extends Actor
{
    private final GreenfootImage Options0 = new GreenfootImage("Gear0.jpg");
    private final GreenfootImage Options1 = new GreenfootImage("Gear1.jpg");
    private boolean mouseOver = false;

    /**
     * Constructor of Options.
     */
    public Options()
    {
        setImage(Options0);
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                setImage(Options0);
                mouseOver = false;
            }
            else if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                setImage(Options1);
                mouseOver = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                MainMenu.display = "Options";
                Panel panel = new Panel();
                panel.screen = true;
                if (!MainMenu.inGame)
                {
                    getWorld().addObject(panel, 550, 400);
                }
                else
                {
                    getWorld().addObject(panel, 500, 400);
                }
            }
        }
    }
}