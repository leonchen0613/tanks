import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * Server World 
 * 
 * @author Christian Wang 
 * @version 1.0
 */
public class ServerWorld extends Unique
{
    Server server;
    int port;

    Timer coolDown;
    IPAddress ip;
    int initC = 0;
    /**
     * Constructor for objects of class ServerWorld.
     * 
     */
    public ServerWorld(String ID, int port)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(ID);
        this.port = port;

        coolDown = new Timer();
    }

    /**
     * Sends the message to add an object through the server 
     */
    public void addObject(Actor actor, int x, int y) {
        Message message;
        if(actor instanceof GameObject) {
            message = new Message("addObject()","|"+actor.getClass().toString().substring(5).trim()+"|"+((GameObject) actor).ID+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
        } else {
            message = new Message("addObject()","|"+actor.getClass().toString().substring(5).trim()+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
        }
        server.sendObject(message);
        super.addObject(actor, x, y);
        //System.out.println("ADDING FROM SERVER");
    }

    /**
     * Sends the message to move an object through the server 
     */
    public void moveObject(GameObject ga, int x, int y, States currentState) {
        Message message = new Message("moveObject()","|"+ga.ID.trim()+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|"+String.valueOf(currentState)+"|");
        server.sendObject(message);
        // System.out.println(message.cmd + " " + message.param);
    }

    /**
     * Sends the message to remove an object through the server 
     */
    public void removeObject(GameObject ga) {
        Message message = new Message("removeObject()","|"+ga.ID+"|");
        server.sendObject(message);
        super.removeObject(ga);
        // System.out.println(message.cmd + " " + message.param);
    }

    /**
     * Starts the server 
     */
    public void started() {
        server = new Server(port, this);
        server.start();

        //System.out.println("WORLD HAS BEEN OPENED");
        super.started();
    }

    /**
     * Stops the server
     */
    public void stopped() {
        server.close();
        //System.out.println("WORLD HAS BEEN CLOSED");
        super.stopped();
    }

    /**
     * Randomly spawn berries
     */
    public void act() {
        if(initC == 0) {
            ip = new IPAddress("");
            super.addObject(ip, 300, 585);
        }
        ip.update(server.returnIP());
        
        initC++;
        if(coolDown.millisElapsed() >= 1000) {
            //Spawn berries randomly around the world
            Berry berry = new Berry();
            int x = new Random().nextInt(getWidth()-128) + 64;
            int y = new Random().nextInt(getHeight()-128) + 64;
            Message message = new Message("addObject()","|"+berry.getClass().toString().substring(5).trim()+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
            server.sendObject(message);
            addObject(berry, x , y);
            coolDown.mark();
        }
    }

}
