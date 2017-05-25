import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.io.*;
import java.net.*;
/**
 * Thread that manages connections
 * 
 * @author Christian Wang
 * @version 1.0
 */
public class ClientThread extends Thread
{
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Socket serverConnection = null;

    public boolean hasStopped = false;

    private World world;

    /**
     * Create a new worker with a client connection
     * and a world reference
     */
    public ClientThread(Socket serverConnection, World world) {
        this.serverConnection = serverConnection;
        this.world = world;
    }

    public void setup() {
        try {
            output = new ObjectOutputStream(serverConnection.getOutputStream());
            input = new ObjectInputStream(serverConnection.getInputStream());
        } catch (IOException e) {
            System.out.println("Could not create streams");
        }
    }

    /**
     * Send an object through the output stream
     */
    public void sendObject(Object parsed) {
        try {
            if(parsed != null) {
                output.writeObject(parsed);
                output.flush();
                System.out.println("SENT OBJECT :" + parsed.toString());
            }
        } catch (IOException e) {
            System.out.println("sendObject() : could not send object");
            System.err.println(e);
        }
    }

    /**
     * Recieve an object from the input stream and return it
     */
    public Object receiveObject() {
        Object received = null;
        try {
            try {
                //Add object to the world with coordinates
                received = input.readObject();
                System.out.println("RECEIVED OBJECT :" + received.toString());
            } catch(ClassNotFoundException e) {
                System.out.println("CLASS COULD NOT BE FOUND: COULD NOT SEND");
            }
        } catch (IOException e) {
            System.out.println("recieveObject() : could not recieve object");
            System.err.println(e);
        }

        return received;
    }

    public void sendFile(File file) {
        try {
            if(file != null) {
                output.writeObject(file);
                output.flush();
                System.out.println("SENT OBJECT :" + file.toString());
            }
        } catch (IOException e) {
            System.out.println("sendFile() : could not send file");
            System.err.println(e);
        }
    }

    public File receiveFile() {
        File received = null;
        try {
            try {
                //Add object to the world with coordinates
                Object temp = input.readObject();
                if(temp instanceof File) {
                    received = (File) temp;
                }
            } catch(ClassNotFoundException e) {
                System.out.println("CLASS COULD NOT BE FOUND: COULD NOT SEND");
            }
        } catch (IOException e) {
            System.out.println("recieveFile() : could not recieve object");
            System.err.println(e);
        }

        return received;
    }

    /**
     * Stops the server
     */
    public void shutDown() {
        this.hasStopped = true;
        try {
            output.close();
            input.close();
            this.serverConnection.close();
        } catch(IOException e) {
            throw new RuntimeException("CANNOT CLOSE SERVER", e);
        }
    }

    /**
     * Control what happens when you get certain messages
     */
    public void controller() throws EOFException{
        Object control = receiveObject();
        if(control instanceof File) {
            File file = (File) control;
        } else if (control instanceof Message) {
            Message message = (Message) control;

            String ID = "";

            String param = message.param;
            String cmd = message.cmd;
            System.out.println(cmd + " " + param);

            //StringTokenizer st = new StringTokenizer(param, "|");
            String[] st = param.split("\\|");
            ArrayList<Actor> objs = null;

            switch(cmd) {
                case "addObject()" : 
                // switch(st.nextToken()) {
                //switch(st[0]) {
                try {
                    Integer.parseInt(st[2]);
                    switch(st[1]) {
                        //if(st[0].equals("Player")) { 
                        case "Player" :
                        //world.addObject(new Player(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        ((World) world).addObject(new Player(), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
                        break;
                        case "test" :
                        //world.addObject(new test(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        ((World) world).addObject(new test(), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
                        break;
                        case "Berry" :
                        System.out.println("GOT A BERRY");
                        //world.addObject(new test(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        ((World) world).addObject(new Berry(), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
                        break;

                    }
                } catch (NumberFormatException e) {
                    switch(st[1]) {
                        //if(st[0].equals("Player")) { 
                        case "Player" :
                        //world.addObject(new Player(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                        ((World) world).addObject(new Player(st[2]), Integer.parseInt(st[3]), Integer.parseInt(st[4]));
                        break;
                    }
                }
                break;
                case "moveObject()" :
                //ID = st.nextToken();
                ID = st[1];
                objs = new ArrayList<Actor>(world.getObjects(null));
                for(Actor a : objs) {
                    if(a instanceof GameObject) {
                        if(((GameObject) a).ID.equals(ID)) {
                            //a.setLocation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                            a.setLocation(Integer.parseInt(st[2]), Integer.parseInt(st[3]));
                            ((GameObject) a).currentState = States.valueOf(st[4]);
                            if(a instanceof Player) {
                                ((Player) a).controller();
                            }
                            break;
                        }
                    }
                }
                break;
                case "removeObject()" :
                //ID = st.nextToken();
                ID = st[1];
                objs = new ArrayList<Actor>(world.getObjects(null));
                for(Actor a : objs) {
                    if(a instanceof GameObject) {
                        if(((GameObject) a).ID.equals(ID)) {
                            world.removeObject(a);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    /**
     * Executed when a new worker thread is started
     */
    public void run() {
        setup();
        sendObject(new Message("addPlayer()",((ClientWorld) world).ID));
        //Automatically add a new player
        //int x = new Random().nextInt(600-128) + 64;
        //int y = new Random().nextInt(600-128) + 64;
        //Message message = new Message("addObject()","|"+Player.class.toString()+"|"+((ClientWorld) world).ID+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|");
        //sendObject(message);
        //System.out.println(message.cmd+ message.param);
        //world.addObject(new Player(((ClientWorld) world).ID),x,y);

        //Main loop to read and write objects through the stream
        do {
            try {
                if(serverConnection.isClosed()) hasStopped = true;
                controller();
                //Parser temp = receiveObject();
                //if(world.getObjectsAt(temp.x, temp.y, temp.object.getClass()).size() == 0) {
                //    if(temp.object.getClass() == Player.class) {
                //        ((Player) temp.object).create();
                //    }
                //    ((World) world).addObject(temp.object, temp.x, temp.y);
                //}
            } catch (EOFException e) {

            }
        } while(!hasStopped);
        //long timeTaken = System.currentTimeMillis();
        shutDown();
    }

    public void runi() {
        setup();
        //Main loop to read and write objects through the stream
        do {
            File map = receiveFile();
        } while(!hasStopped);
        //long timeTaken = System.currentTimeMillis();
        shutDown();
    }
}