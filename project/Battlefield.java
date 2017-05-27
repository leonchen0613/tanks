import greenfoot.*;
import java.util.*;

/**
 * The battlefield of the game.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Battlefield extends World
{
    private final GreenfootImage battlefield = new GreenfootImage("Battlefield.jpg");
    public static int redScore = 0;
    public static int blueScore = 0;
    public static boolean existCrate = false;

    /**
     * Constructor of Battlefield.
     */
    public Battlefield()
    {
        super(1200, 800, 1);
        prepare();
    }

    /**
     * Prepares the world for the start of the program.
     */
    private void prepare()
    {
        setBackground(battlefield);

        addObject(new BorderTop(), 600, 0);
        addObject(new BorderBottom(), 600, 800);
        addObject(new BorderLeft(), 0, 400);
        addObject(new BorderRight(), 1000, 400);
        addObject(new BorderRight(), 1200, 400);
        addObject(new Scoreboard(), 1095, 400);
        addObject(new ScoreRed(redScore), 1095, 170);
        addObject(new ScoreBlue(blueScore), 1095, 285);
        addObject(new Help(), 1095, 400);
        addObject(new Options(), 1095, 550);
        addObject(new Home(), 1095, 675);
        addObject(new Refresh(), 1155, 755);

        walls();
        verticies();

        RedTank redTank = new RedTank();
        Random a = new Random();
        int rtx = a.nextInt(10) * 100 + 50;
        Random b = new Random();
        int rty = b.nextInt(8) * 100 + 50;
        addObject(redTank, rtx, rty);

        // Spawns the blue tank in a different location than the red tank.
        int btx;
        int bty;
        do
        {
            Random c = new Random();
            btx = c.nextInt(10) * 100 + 50;
            Random d = new Random();
            bty = d.nextInt(8) * 100 + 50;
        }
        while (rtx == btx && rty == bty);
        BlueTank blueTank = new BlueTank();
        addObject(blueTank, btx, bty);
    }

    /**
     * Runs this repeatedly.
     */
    public void act()
    {
        if (MainMenu.music)
        {
            MainMenu.soundtrack.playLoop();
            MainMenu.soundtrack.setVolume(50);
        }
        else
        {
            MainMenu.soundtrack.stop();
        }
        if (MainMenu.display == "Background")
        {
            spawnCrates();
        }
    }

    /**
     * Randomly generates walls.
     */
    public void walls()
    {
        Random num = new Random();
        int wallNum = num.nextInt(26) + 25;
        for (int i = 1; i <= wallNum; i++)
        {
            Random tf = new Random();
            if (tf.nextBoolean())
            {
                HorizontalWall hw = new HorizontalWall();
                Random hwx = new Random();
                Random hwy = new Random();
                addObject(hw, hwx.nextInt(10) * 100 + 50, (hwy.nextInt(6) + 1) * 100);
            }
            else
            {
                VerticalWall vw = new VerticalWall();
                Random vwx = new Random();
                Random vwy = new Random();
                addObject(vw, (vwx.nextInt(8) + 1) * 100, vwy.nextInt(8) * 100 + 50);
            }
        }
    }

    /**
     * Adds verticies to the walls.
     */
    public void verticies()
    {
        for (int y = 0; y <= 800; y += 100)
        {
            for (int x = 0; x <= 1000; x += 100)
            {
                List up = getObjectsAt(x, y - 50, VerticalWall.class);
                List right = getObjectsAt(x + 50, y, HorizontalWall.class);
                List down = getObjectsAt(x, y + 50, VerticalWall.class);
                List left = getObjectsAt(x - 50, y, HorizontalWall.class);
                if (up.size() > 0 && right.size() > 0 || up.size() > 0 && down.size() > 0 || up.size() > 0 && left.size() > 0 ||
                right.size() > 0 && down.size() > 0 || right.size() > 0 && left.size() > 0 || down.size() > 0 && left.size() > 0)
                {
                    Vertex vertex = new Vertex();
                    addObject(vertex, x, y);
                }
            }
        }
    }

    /**
     * Randomly spawns crates.
     */
    public void spawnCrates()
    {
        if (!existCrate)
        {
            Random chance = new Random();
            if (chance.nextInt(1000) == 0)
            {
                existCrate = true;
                Random x = new Random();
                Random y = new Random();
                addObject(new Crate(), x.nextInt(10) * 100 + 50, x.nextInt(8) * 100 + 50);
            }
        }
    }
}