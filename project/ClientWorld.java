import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.*;
/**
 * Client World
 * 
 * @author Christian Wang 
 * @version 1.0
 */
public class ClientWorld extends Unique
{
    Client client;
    String host;
    int port;
    /**
     * Constructor for objects of class ClientWorld.
     * 
     */
    public ClientWorld(String ID, String host, int port)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(ID); 
        this.host = host;
        this.port = port;

        TileStorage.init();
        TileStorage.loadTiles();
        Tiler tiler = new Tiler("terrain_atlas.png",32,32);
        super.addObject(tiler, getWidth()/2,getHeight()/2);
        TilePlacer placer = new TilePlacer("terrain_atlas.png",32,32,tiler);
        placer.loadMap(new File("arena"));
        //addObject(new Controller(), 300, 300);
    }

    /**
     * Not allowed to use it
     */
    public void addSendObject(Actor actor, int x, int y) {
        Message message;
        if(actor instanceof GameObject) {
            message = new Message("addObject()","|"+actor.getClass().toString().substring(5).trim()+"|"+((GameObject) actor).ID+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
        } else {
            message = new Message("addObject()","|"+actor.getClass().toString().substring(5).trim()+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
        }
        client.sendObject(message);
        super.addObject(actor, x, y);
        //System.out.println("ADDING FROM CLIENT");
    }

    /**
     * Sends a message to move an object through the client
     */
    public void moveObject(GameObject ga, int x, int y, States currentState) {
        Message message = new Message("moveObject()","|"+ga.ID.trim()+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|"+String.valueOf(currentState)+"|");
        client.sendObject(message);
    }

    /**
     * Sends a message to remove an object through the client
     */
    public void removeObject(GameObject ga) {
        Message message = new Message("removeObject()","|"+ga.ID+"|");
        client.sendObject(message);
        super.removeObject(ga);
        // System.out.println(message.cmd + " " + message.param);
    }

    /**
     * Starts the client - connection
     */
    public void started() {
        client = new Client(host, port, this);
        client.start();
        System.out.println("WORLD HAS BEEN OPENED");
        super.started();
    }

    /**
     * Stops the client connection
     */
    public void stopped() {
        client.shutDown();
        System.out.println("WORLD HAS BEEN CLOSED");
        super.stopped();
    }
}
