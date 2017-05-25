import greenfoot.*;

/**
 * Default projectile of the blue tank.
 * 
 * @author Leon Chen
 * @version Final
 */
public class BlueBullet extends SmoothMover
{
    private final GreenfootImage blueBullet = new GreenfootImage("BlueBullet.png");
    public int durability = 500;
    private int direction;
    private boolean hitRow = false;
    private boolean hitColumn = false;
    private boolean hitHorizontal = false;
    private boolean hitVertical = false;
    public static int bulletSpeed = 3;

    public BlueBullet(int angle)
    {
        setImage(blueBullet);
        direction = angle;
    }

    public void act()
    {
        if (MainMenu.display == "Background")
        {
            direction();
            move(bulletSpeed);
            life();
        }
    }

    /**
     * Sets the direction of the blue bullet.
     */
    public void direction()
    {
        // Wall bounce physics.
        if (getOneIntersectingObject(BorderTop.class) != null || getOneIntersectingObject(BorderBottom.class) != null)
        {
            if (hitRow == false)
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Bounce.mp3");
                }
                hitRow = true;
                direction = 360 - direction;
                move(10);
            }
        }
        if (getOneIntersectingObject(BorderLeft.class) != null || getOneIntersectingObject(BorderRight.class) != null)
        {
            if (hitColumn == false)
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Bounce.mp3");
                }
                hitColumn = true;
                direction = 180 - direction;
                if (direction < 0)
                {
                    direction = direction + 360;
                }
                move(10);
            }
        }
        if (getOneIntersectingObject(BorderTop.class) == null && getOneIntersectingObject(BorderBottom.class) == null)
        {
            hitRow = false;
        }
        if (getOneIntersectingObject(BorderLeft.class) == null && getOneIntersectingObject(BorderRight.class) == null)
        {
            hitColumn = false;
        }

        if (getOneIntersectingObject(HorizontalWall.class) != null)
        {
            if (hitHorizontal == false)
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Bounce.mp3");
                }
                hitHorizontal = true;
                if (direction == 0)
                {
                    direction = 180;
                }
                else if (direction == 180)
                {
                    direction = 0;
                }
                else if (0 < direction && direction < 20)
                {
                    direction = 340;
                }
                else if (160 < direction && direction < 180)
                {
                    direction = 200;
                }
                else if (180 < direction && direction < 200)
                {
                    direction = 160;
                }
                else if (340 < direction && direction < 360)
                {
                    direction = 20;
                }
                else
                {
                    direction = 360 - direction;
                }
            }
        }
        if (getOneIntersectingObject(VerticalWall.class) != null)
        {
            if (hitVertical == false)
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Bounce.mp3");
                }
                hitVertical = true;
                if (direction == 90)
                {
                    direction = 270;
                }
                else if (direction == 270)
                {
                    direction = 90;
                }
                else if (70 < direction && direction < 90)
                {
                    direction = 110;
                }
                else if (90 < direction && direction < 110)
                {
                    direction = 70;
                }
                else if (250 < direction && direction < 270)
                {
                    direction = 290;
                }
                else if (270 < direction && direction < 290)
                {
                    direction = 250;
                }
                else
                {
                    direction = 180 - direction;
                    if (direction < 0)
                    {
                        direction = direction + 360;
                    }
                }
            }
        }
        if (getOneIntersectingObject(HorizontalWall.class) == null)
        {
            hitHorizontal = false;
        }
        if (getOneIntersectingObject(VerticalWall.class) == null)
        {
            hitVertical = false;
        }

        setRotation(direction);
    }

    /**
     * The status and duration of the blue bullet.
     */
    public void life()
    {
        durability--;
        if (durability <= 0)
        {
            getWorld().removeObject(this);
        }
        else // Shrinks bullet.
        {
            if (durability <= 1)
            {
                getImage().scale(4, 4);
            }
            else if (durability <= 3)
            {
                getImage().scale(8, 8);
            }
            else if (durability <= 6)
            {
                getImage().scale(12, 12);
            }
            else if (durability <= 10)
            {
                getImage().scale(16, 16);
            }

            if (getOneIntersectingObject(RedBullet.class) != null) // Hits a red bullet.
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Explode.mp3");
                }
                removeTouching(RedBullet.class);
                getWorld().removeObject(this);
            }
            else if (getOneIntersectingObject(RedTank.class) != null) // Hits the red tank.
            {
                if (MainMenu.sound)
                {
                    Greenfoot.playSound("Explode.mp3");
                }
                removeTouching(RedTank.class);
                getWorld().removeObject(this);
                Battlefield.blueScore++;
                Greenfoot.delay(100);

                if (Battlefield.blueScore == 10)
                {
                    Victory victory = new Victory();
                    victory.winner = false;
                    victory.winningScreen();
                    Greenfoot.setWorld(victory);
                }
                else
                {
                    Greenfoot.setWorld(new Battlefield());
                    refresh();
                }
            }
        }
    }
    
    /**
     * Resets the map.
     */
    public void refresh()
    {
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