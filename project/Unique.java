import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Gives an ID to World objects
 * 
 * @author Christian Wang
 * @version 1.0
 */
public class Unique extends World
{
    public String ID;
    /**
     * Constructor for objects of class Unique.
     * 
     */
    public Unique(String ID)
    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 600, 1); 
        this.ID = ID;
    }
}
