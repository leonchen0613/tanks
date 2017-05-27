import greenfoot.*;

/**
 * Refresh button on the scoreboard.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Refresh extends Actor
{
    private final GreenfootImage Refresh0 = new GreenfootImage("Refresh0.jpg");
    private final GreenfootImage Refresh1 = new GreenfootImage("Refresh1.jpg");
    private boolean mouseOver = false;

    /**
     * Constructor of Refresh.
     */
    public Refresh()
    {
        setImage(Refresh0);
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
            {
                setImage(Refresh0);
                mouseOver = false;
            }
            else if (!mouseOver && Greenfoot.mouseMoved(this))
            {
                setImage(Refresh1);
                mouseOver = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                Greenfoot.setWorld(new Battlefield());
                Battlefield.existCrate = false;
                RedTank.crateSpeed = false;
                RedTank.crateSniper = false;
                RedTank.crateMinigun = false;
                RedTank.crateShotgun = false;
                RedTank.speed = 2;
                RedTank.angle = 2;
                RedBullet.bulletSpeed = 3;
                RedTank.reloadTime = 100;
                BlueTank.crateSpeed = false;
                BlueTank.crateSniper = false;
                BlueTank.crateMinigun = false;
                BlueTank.crateShotgun = false;
                BlueTank.speed = 2;
                BlueTank.angle = 2;
                BlueBullet.bulletSpeed = 3;
                BlueTank.reloadTime = 100;
            }
        }
    }
}