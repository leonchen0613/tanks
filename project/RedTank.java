import greenfoot.*;
import java.util.Random;

/**
 * The actor controlled by Player 1.
 * 
 * @author Leon Chen
 * @version Final
 */
public class RedTank extends SmoothMover
{
    private final GreenfootImage redTank = new GreenfootImage("RedTank.jpg");
    public static double speed = 2;
    public static int angle = 2;
    private double recentX;
    private double recentY;
    private int recentAngle;
    private int cooldown;
    private int powerCooldown = 0;
    public static int reloadTime = 100;
    public static boolean crateSpeed = false;
    public static boolean crateSniper = false;
    public static boolean crateMinigun = false;
    public static boolean crateShotgun = false;

    /**
     * Constructor of RedTank.
     */
    public RedTank()
    {
        setImage(redTank);
        Random r = new Random();
        setRotation(r.nextInt(4) * 90);
    }

    /**
     * Act - do whatever the BlueTank wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (MainMenu.display == "Background")
        {
            move();
            shoot();
        }
        ability();
    }

    /**
     * Changes the position of the tank.
     */
    public void move()
    {
        // Picks up a crate.
        if (getOneIntersectingObject(Crate.class) != null)
        {
            Random random = new Random();
            int chance = random.nextInt(4) + 1;
            if (chance == 1)
            {
                setImage("RedTankSpeed.jpg");
                speed = 3;
                angle = 3;
                crateSpeed = true;
                powerCooldown = 600;
            }
            else if (chance == 2)
            {
                setImage("RedTankSniper.jpg");
                RedBullet.bulletSpeed = 5;
                crateSniper = true;
                powerCooldown = 600;
            }
            else if (chance == 3)
            {
                setImage("RedTankMinigun.jpg");
                reloadTime = 40;
                crateMinigun = true;
                powerCooldown = 400;
            }
            else if (chance == 4)
            {
                setImage("RedTankShotgun.jpg");
                crateShotgun = true;
                powerCooldown = 400;
            }
            removeTouching(Crate.class);
        }
        // Wall collision.
        if (getOneIntersectingObject(BorderTop.class) != null || getOneIntersectingObject(BorderBottom.class) != null ||
        getOneIntersectingObject(BorderLeft.class) != null || getOneIntersectingObject(BorderRight.class) != null ||
        getOneIntersectingObject(HorizontalWall.class) != null || getOneIntersectingObject(VerticalWall.class) != null ||
        getOneIntersectingObject(Vertex.class) != null || getOneIntersectingObject(BlueTank.class) != null)
        {
            setLocation(recentX, recentY);
            setRotation(recentAngle);
        }
        else
        {
            recentX = getExactX();
            recentY = getExactY();
            recentAngle = getRotation();

            if (Greenfoot.isKeyDown(MainMenu.keyBindings[0]))
            {
                move(speed);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[1]))
            {
                move(-speed);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[2]))
            {
                turn(-angle);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[3]))
            {
                turn(angle);
            }
        }
    }

    /**
     * Shoots a projectile.
     */
    public void shoot()
    {
        if (cooldown > 0)
        {
            cooldown--;
        }
        else if (cooldown == 0)
        {
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[4]))
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Fire.mp3");
                }
                getWorld().addObject(new RedBullet(getRotation()), getX(), getY());
                cooldown = reloadTime;
                if (crateShotgun) // Spawns two more bullets with each shot.
                {
                    getWorld().addObject(new RedBullet(getRotation() - 30), getX(), getY());
                    getWorld().addObject(new RedBullet(getRotation() + 30), getX(), getY());
                }
            }
        }
    }
    
    /**
     * Power-up from crate.
     */
    public void ability()
    {
        if (crateSpeed)
        {
            powerCooldown--;
            if (powerCooldown == 0)
            {
                setImage("RedTank.jpg");
                speed = 2;
                angle = 2;
                crateSpeed = false;
                Battlefield.existCrate = false;
            }
        }
        if (crateSniper)
        {
            powerCooldown--;
            if (powerCooldown == 0)
            {
                setImage("RedTank.jpg");
                RedBullet.bulletSpeed = 3;
                crateSniper = false;
                Battlefield.existCrate = false;
            }
        }
        if (crateMinigun)
        {
            powerCooldown--;
            if (powerCooldown == 0)
            {
                setImage("RedTank.jpg");
                reloadTime = 100;
                crateMinigun = false;
                Battlefield.existCrate = false;
            }
        }
        if (crateShotgun)
        {
            powerCooldown--;
            if (powerCooldown == 0)
            {
                setImage("RedTank.jpg");
                crateShotgun = false;
                Battlefield.existCrate = false;
            }
        }
    }
}