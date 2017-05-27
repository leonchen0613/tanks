import greenfoot.*;
import java.util.Random;

/**
 * The actor controlled by Player 2.
 * 
 * @author Leon Chen
 * @version Final
 */
public class BlueTank extends SmoothMover
{
    private final GreenfootImage blueTank = new GreenfootImage("BlueTank.jpg");
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
     * Constructor of BlueTank.
     */
    public BlueTank()
    {
        setImage(blueTank);
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
                setImage("BlueTankSpeed.jpg");
                speed = 3;
                angle = 3;
                crateSpeed = true;
                powerCooldown = 600;
            }
            else if (chance == 2)
            {
                setImage("BlueTankSniper.jpg");
                BlueBullet.bulletSpeed = 5;
                crateSniper = true;
                powerCooldown = 600;
            }
            else if (chance == 3)
            {
                setImage("BlueTankMinigun.jpg");
                reloadTime = 40;
                crateMinigun = true;
                powerCooldown = 400;
            }
            else if (chance == 4)
            {
                setImage("BlueTankShotgun.jpg");
                crateShotgun = true;
                powerCooldown = 400;
            }
            removeTouching(Crate.class);
        }
        // Wall collision.
        if (getOneIntersectingObject(BorderTop.class) != null || getOneIntersectingObject(BorderBottom.class) != null ||
        getOneIntersectingObject(BorderLeft.class) != null || getOneIntersectingObject(BorderRight.class) != null ||
        getOneIntersectingObject(HorizontalWall.class) != null || getOneIntersectingObject(VerticalWall.class) != null ||
        getOneIntersectingObject(Vertex.class) != null || getOneIntersectingObject(RedTank.class) != null)
        {
            setLocation(recentX, recentY);
            setRotation(recentAngle);
        }
        else
        {
            recentX = getExactX();
            recentY = getExactY();
            recentAngle = getRotation();

            if (Greenfoot.isKeyDown(MainMenu.keyBindings[5]))
            {
                move(speed);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[6]))
            {
                move(-speed);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[7]))
            {
                turn(-angle);
            }
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[8]))
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
            if (Greenfoot.isKeyDown(MainMenu.keyBindings[9]))
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Fire.mp3");
                }
                getWorld().addObject(new BlueBullet(getRotation()), getX(), getY());
                cooldown = reloadTime;
                if (crateShotgun) // Spawns two more bullets with each shot.
                {
                    getWorld().addObject(new BlueBullet(getRotation() - 30), getX(), getY());
                    getWorld().addObject(new BlueBullet(getRotation() + 30), getX(), getY());
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
                setImage("BlueTank.jpg");
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
                setImage("BlueTank.jpg");
                BlueBullet.bulletSpeed = 3;
                crateSniper = false;
                Battlefield.existCrate = false;
            }
        }
        if (crateMinigun)
        {
            powerCooldown--;
            if (powerCooldown == 0)
            {
                setImage("BlueTank.jpg");
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
                setImage("BlueTank.jpg");
                crateShotgun = false;
                Battlefield.existCrate = false;
            }
        }
    }
}