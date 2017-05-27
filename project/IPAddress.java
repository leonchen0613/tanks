import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class IPAddress here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IPAddress extends GUI
{
    public IPAddress(String host) {
        width = 600;
        height = 30;
        GreenfootImage menu = new GreenfootImage(width, height);
        menu.setColor(new Color(145,176,255));
        menu.fillRect(0,0,width,height);
        //menu.setTransparency(220);

        menu.setColor(new Color(255,255,255));
        menu.setFont(new Font("Segoe UI", 30));
        menu.drawString(String.valueOf(host), 10, 25);
        setImage(menu);
    }
    
    public void update(String host) {
        width = 600;
        height = 30;
        GreenfootImage menu = new GreenfootImage(width, height);
        menu.setColor(new Color(145,176,255));
        menu.fillRect(0,0,width,height);
        //menu.setTransparency(220);

        menu.setColor(new Color(255,255,255));
        menu.setFont(new Font("Segoe UI", 30));
        menu.drawString(String.valueOf(host), 10, 25);
        setImage(menu);
    }
}
