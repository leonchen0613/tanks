import greenfoot.*;

/**
 * The main menu of the game.
 * 
 * @author Leon Chen
 * @version Final
 */
public class MainMenu extends World
{
    private final GreenfootImage tank1 = new GreenfootImage("MainMenuTank1.jpg");
    private final GreenfootImage tank2 = new GreenfootImage("MainMenuTank2.jpg");
    private final GreenfootImage tank3 = new GreenfootImage("MainMenuTank3.jpg");
    private final GreenfootImage tank4 = new GreenfootImage("MainMenuTank4.jpg");
    private int tankCounter = 0;
    private int pixelTankCounter = 0;
    public static String[] keyBindings = {"w", "s", "a", "d", "v", "i", "k", "j", "l", "/"};
    public static String display = "Background";
    public static boolean inGame = false;
    public static boolean music = true;
    public static boolean sound = true;
    public static GreenfootSound soundtrack = new GreenfootSound("Vainglory Music.mp3");

    /**
     * Constructor of MainMenu.
     */
    public MainMenu()
    {
        super(1200, 800, 1);
        prepare();
    }

    /**
     * Prepares the world for the start of the program.
     */
    private void prepare()
    {
        display = "Background";
        inGame = false;
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
        
        setBackground(tank1);
        setPaintOrder(Text.class, Music.class, Sound.class, Close.class, Panel.class, PixelTank.class);

        BorderTop borderTop = new BorderTop();
        addObject(borderTop, 600, 5);
        BorderRight borderRight = new BorderRight();
        addObject(borderRight, 1195, 400);
        BorderBottom borderBottom = new BorderBottom();
        addObject(borderBottom, 600, 795);
        BorderLeft borderLeft = new BorderLeft();
        addObject(borderLeft, 5, 400);
        Title title = new Title();
        addObject(title, 230, 190);
        PlayButton playButton = new PlayButton();
        addObject(playButton, 665, 110);
        Help help = new Help();
        addObject(help, 120, 420);
        Options options = new Options();
        addObject(options, 120, 565);
    }

    /**
     * Runs this repeatedly.
     */
    public void act()
    {
        if (music)
        {
            soundtrack.playLoop();
            soundtrack.setVolume(50);
        }
        else
        {
            soundtrack.stop();
        }
        animateTank();
        animatePixelTank();
    }

    /**
     * Animates the tank by moving it up and down.
     */
    public void animateTank()
    {
        if (tankCounter == 2)
        {
            setBackground(tank1);
        }
        if (tankCounter == 4)
        {
            setBackground(tank2);
        }
        if (tankCounter == 6)
        {
            setBackground(tank3);
        }
        if (tankCounter == 8)
        {
            setBackground(tank4);
            tankCounter = 0;
        }
        tankCounter++;
    }

    /**
     * Animates the pixel tanks at the bottom of the main menu.
     */
    public void animatePixelTank()
    {
        if (pixelTankCounter == 200)
        {
            PixelTank pixelTank = new PixelTank();
            addObject(pixelTank, 140, 700);
        }
        else if (pixelTankCounter == 400)
        {
            PixelTank pixelTank = new PixelTank();
            addObject(pixelTank, 140, 715);
            pixelTankCounter = 0;
        }
        pixelTankCounter++;
    }
}