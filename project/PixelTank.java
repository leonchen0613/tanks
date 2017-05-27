import greenfoot.*;

/**
 * Pixel tank animation on the main menu.
 * 
 * @author Leon Chen
 * @version Final
 */
public class PixelTank extends Actor
{
    private final GreenfootImage pixelTank1 = new GreenfootImage("PixelTank1.jpg");
    private final GreenfootImage pixelTank2 = new GreenfootImage("PixelTank2.jpg");
    private int positionCounter = 0;

    /**
     * Constructor of PixelTank.
     */
    public PixelTank()
    {
        setImage(pixelTank1);
    }

    public void act() 
    {
        if (positionCounter == 5)
        {
            setLocation(getX(), getY() - 2);
            setImage(pixelTank2);
        }
        else if (positionCounter == 10)
        {
            setLocation(getX(), getY() + 2);
            setImage(pixelTank1);
            positionCounter = 0;
        }
        move(1);
        positionCounter++;
        if (getX() >= 1060)
        {
            getWorld().removeObject(this);
        }
    }    
}