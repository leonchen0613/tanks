import greenfoot.*;

/**
 * Displays the instructions and settings.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Panel extends Actor
{
    private int imageX = 300;
    private int imageY = 200;
    public boolean screen;

    /**
     * Constructor of Panel.
     */
    public Panel()
    {
        setImage("Panel.jpg");
    }

    public void act()
    {
        if (imageX != 900 && imageY != 600)
        {
            for (int i = 1; i <= 10; i++)
            {
                imageX += 3;
                imageY += 2;
                getImage().scale(imageX, imageY);
            }
            if (imageX == 900 && imageY == 600)
            {
                if (!screen) // Instructions
                {
                    setImage("Instructions.jpg");
                    if (!MainMenu.inGame)
                    {
                        Close close = new Close();
                        getWorld().addObject(close, 961, 140);
                    }
                    else
                    {
                        Close close = new Close();
                        getWorld().addObject(close, 911, 140);
                    }
                }
                else // Options
                {
                    setImage("Settings.jpg");
                    if (!MainMenu.inGame)
                    {
                        Text forward1 = new Text(0);
                        getWorld().addObject(forward1, 375, 460);
                        Text backward1 = new Text(1);
                        getWorld().addObject(backward1, 375, 505);
                        Text left1 = new Text(2);
                        getWorld().addObject(left1, 375, 550);
                        Text right1 = new Text(3);
                        getWorld().addObject(right1, 375, 595);
                        Text fire1 = new Text(4);
                        getWorld().addObject(fire1, 375, 640);
                        Text forward2 = new Text(5);
                        getWorld().addObject(forward2, 684, 460);
                        Text backward2 = new Text(6);
                        getWorld().addObject(backward2, 684, 505);
                        Text left2 = new Text(7);
                        getWorld().addObject(left2, 684, 550);
                        Text right2 = new Text(8);
                        getWorld().addObject(right2, 684, 595);
                        Text fire2 = new Text(9);
                        getWorld().addObject(fire2, 684, 640);
                        Music music = new Music();
                        getWorld().addObject(music, 825, 406);
                        Sound sound = new Sound();
                        getWorld().addObject(sound, 825, 584);
                        Close close = new Close();
                        getWorld().addObject(close, 961, 140);
                    }
                    else
                    {
                        Text forward1 = new Text(0);
                        getWorld().addObject(forward1, 325, 460);
                        Text backward1 = new Text(1);
                        getWorld().addObject(backward1, 325, 505);
                        Text left1 = new Text(2);
                        getWorld().addObject(left1, 325, 550);
                        Text right1 = new Text(3);
                        getWorld().addObject(right1, 325, 595);
                        Text fire1 = new Text(4);
                        getWorld().addObject(fire1, 325, 640);
                        Text forward2 = new Text(5);
                        getWorld().addObject(forward2, 634, 460);
                        Text backward2 = new Text(6);
                        getWorld().addObject(backward2, 634, 505);
                        Text left2 = new Text(7);
                        getWorld().addObject(left2, 634, 550);
                        Text right2 = new Text(8);
                        getWorld().addObject(right2, 634, 595);
                        Text fire2 = new Text(9);
                        getWorld().addObject(fire2, 634, 640);
                        Music music = new Music();
                        getWorld().addObject(music, 775, 406);
                        Sound sound = new Sound();
                        getWorld().addObject(sound, 775, 584);
                        Close close = new Close();
                        getWorld().addObject(close, 911, 140);
                    }
                }
            }
        }
    }
}