import greenfoot.*;

/**
 * Help button on the main menu.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Help extends Actor
{
    private final GreenfootImage Help0 = new GreenfootImage("QuestionMark0.jpg");
    private final GreenfootImage Help1 = new GreenfootImage("QuestionMark1.jpg");
    private boolean mouseOver = false;

    /**
     * Constructor of Help.
     */
    public Help()
    {
        setImage(Help0);
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                setImage(Help0);
                mouseOver = false;
            }
            else if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                setImage(Help1);
                mouseOver = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                MainMenu.display = "Help";
                Panel panel = new Panel();
                panel.screen = false;
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