import greenfoot.*;
import java.awt.Color;
import java.util.Scanner;

/**
 * Displays words.
 * 
 * @author Leon Chen
 * @version Final
 */
public class Text extends Actor
{
    private boolean mouseOver = false;
    private int keyNumber;

    /**
     * Constructor of Text.
     */
    public Text(int number)
    {
        // Sets the image of the text.
        setImage(new GreenfootImage(MainMenu.keyBindings[number], 40, Color.BLACK, null));
        keyNumber = number;
    }

    public void act() 
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            setImage(new GreenfootImage(MainMenu.keyBindings[keyNumber], 40, Color.BLACK, null));
            mouseOver = false;
        }
        else if (!mouseOver && Greenfoot.mouseMoved(this))
        {
            setImage(new GreenfootImage(MainMenu.keyBindings[keyNumber], 40, Color.YELLOW, null));
            mouseOver = true;
        }
        if (Greenfoot.mousePressed(this))
        {
            if (Greenfoot.isKeyDown("["))
            {
                MainMenu.keyBindings[keyNumber] = "[";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown("]"))
            {
                MainMenu.keyBindings[keyNumber] = "]";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown("\\"))
            {
                MainMenu.keyBindings[keyNumber] = "\\";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown(";"))
            {
                MainMenu.keyBindings[keyNumber] = ";";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown("'"))
            {
                MainMenu.keyBindings[keyNumber] = "'";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown(","))
            {
                MainMenu.keyBindings[keyNumber] = ",";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown("."))
            {
                MainMenu.keyBindings[keyNumber] = ".";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            if (Greenfoot.isKeyDown("/"))
            {
                MainMenu.keyBindings[keyNumber] = "/";
                setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
            }
            else
            {
                for (int i = 97; i <= 122; i++) // Letters a to z.
                {
                    if (Greenfoot.isKeyDown((char)i + ""))
                    {
                        MainMenu.keyBindings[keyNumber] = (char)i + "";
                        setImage(new GreenfootImage(MainMenu.keyBindings[this.keyNumber], 40, Color.GREEN, null));
                        break;
                    }
                }
            }
        }
    }
}