import greenfoot.*;

/**
 * Close button on the panel.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Close extends Actor
{
    private final GreenfootImage Close0 = new GreenfootImage("Close0.jpg");
    private final GreenfootImage Close1 = new GreenfootImage("Close1.jpg");
    private boolean mouseOver = false;

    /**
     * Constructor of Close.
     */
    public Close()
    {
        setImage(Close0);
    }

    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            setImage(Close0);
            mouseOver = false;
        }
        else if (!mouseOver && Greenfoot.mouseMoved(this))
        {
            setImage(Close1);
            mouseOver = true;
        }
        if (Greenfoot.mouseClicked(this)) // Closes the panel and all its content.
        {
            MainMenu.display = "Background";
            getWorld().removeObjects(getWorld().getObjects(Text.class));
            getWorld().removeObjects(getWorld().getObjects(Music.class));
            getWorld().removeObjects(getWorld().getObjects(Sound.class));
            getWorld().removeObjects(getWorld().getObjects(Panel.class));
            getWorld().removeObject(this);
        }
    }
}