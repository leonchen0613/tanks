import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.net.*;
import java.io.*;
/**
 * Server that listens and manages connections
 * 
 * @author Christian Wang
 * @version 1.0
 */
public class Server extends Thread
{
    private int port = 25566;
    private ServerSocket serverSocket = null;
    private boolean hasStopped = false;

    private boolean init = true;

    private ServerWorld world;

    private static int maxClients = 10;

    //public static ServerThread[] threads = new ServerThread[maxClients];

    //public static ServerThread[] clients = new ServerThread[maxClients];
    public ArrayList<ServerThread> clients = new ArrayList<ServerThread>();
    /**
     * Constructor for objects of class Server
     */
    public Server(int port, ServerWorld world)
    {
        this.port = port;
        this.world = world;
    }

    public String returnIP() {
        if(serverSocket != null) {
            try {
                return serverSocket.getInetAddress().getLocalHost().toString();
            } catch (Exception e) {}
        }
        return "";
    }

    /**
     * Checks if this server has stopped
     */
    private boolean hasStopped() {
        return this.hasStopped;
    }

    /**
     * Stops the server
     */
    public void shutDown() {
        this.hasStopped = true;
        try {
            this.serverSocket.close();
        } catch(IOException e) {
            throw new RuntimeException("Server cannot be closed", e);
        }
    }

    /**
     * Send an object to the client
     */
    public void sendObject(Object parsed) {
        if(parsed != null) {
            for(ServerThread s: clients) {
                s.sendObject(parsed);
            }
        }
    }

    /**
     * Recieve an object from the server thread and send it to all the other server
     * threads to synchronize stuff
     */
    public Object receiveObject() {
        Object temp = null;
        for(int i = 0; i < clients.size(); i++) {
            if(clients.get(i) != null) {
                temp = clients.get(i).receiveObject();
                for(int j = 0; j < clients.size(); j++) {
                    if(j != i) {
                        System.out.println("SENDING TO OTHER CLIENTS");
                        clients.get(j).sendObject(temp);
                    }
                }
                return temp;    
            }
        }
        return temp;
    }

    /**
     * Recieve an object from the server thread and send it to all the other server
     * threads to synchronize stuff
     */
    public Object receivetObject() {
        Object temp = null;
        //for(int i = 0; i < clients.length; i++) {
        //    if(clients[i] != null) {
        //        temp = clients[i].receiveObject();
        //        if(temp != null) {
        //            return temp;
        //        }
        //    }
        //}
        return temp;
    }

    /**
     * Setups the server socket by attempting to
     * open up a server socket on port
     */
    public void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.port);
            init = false;
            System.out.println("SERVER RUNNING ON PORT " + this.port);
        } catch (IOException e) {
            throw new RuntimeException("Not able to open the server", e);
        } 
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("COULD NOT CLOSE THE SOCKET");   
        }
    }

    /**
     * Run when start() is called on a new Server thread
     */
    public void run() {

        if(init) {
            //Setup the server 
            openServerSocket();
        }

        //Automatically add a new player
        int x = new Random().nextInt(600-128) + 64;
        int y = new Random().nextInt(600-128) + 64;

        sendObject(new Message("addObject()","|"+Player.class.toString()+"|"+((ServerWorld) world).ID+"|"+String.valueOf(x)+"|"+String.valueOf(y)+"|"));
        world.addObject(new Player(((ServerWorld) world).ID),x,y);

        while(!hasStopped()) {
            //System.out.println("RUNNING");
            Socket clientConnection = null;
            try {

                clientConnection = this.serverSocket.accept();
                System.out.println("CONNECTED TO CLIENT " + clientConnection.getInetAddress().toString());

                // for(int i = 0; i < clients.length; i++) {
                //    if(clients[i] == null) {
                //         clients[i] = new ServerThread(clientConnection, world);
                //         clients[i].start();
                //     }
                //   }
                if(clients.size() <= maxClients) {
                    clients.add(new ServerThread(clientConnection, world));
                    clients.get(clients.size() - 1).start();
                    clients.get(clients.size() - 1).clients.add(clients.get(clients.size() -1));

                    //Give enough time to connect
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                    /**
                     * Sends all of the current objects to the new client
                     */
                    ArrayList <Actor> worldOBJ = new ArrayList<Actor>(world.getObjects(null));
                    Message message;
                    for(int i = 0; i < worldOBJ.size(); i++) {
                        if(worldOBJ.get(i) instanceof GameObject) {
                            message = new Message("addObject()","|"+worldOBJ.get(i).getClass().toString().substring(5).trim()+"|"+((GameObject) worldOBJ.get(i)).ID+"|"+String.valueOf(worldOBJ.get(i).getX())+"|"+String.valueOf(worldOBJ.get(i).getY())+"|");
                        } else {
                            message = new Message("addObject()","|"+worldOBJ.get(i).getClass().toString().substring(5).trim()+"|"+String.valueOf(worldOBJ.get(i).getX())+"|"+String.valueOf(worldOBJ.get(i).getY())+"|");
                        }
                        System.out.println(message);
                        clients.get(clients.size() - 1).sendObject(message);
                    }

                }
            }

            catch(IOException e) {
                if(hasStopped()) {
                    System.out.println("SERVER HAS CLOSED");
                    break;
                }
                throw new RuntimeException("Can't connect to client", e);
            }
        }

        //Parser p = receiveObject();
        //world.addObject(p.object, p.x, p.y);

        System.out.println("SERVER IS CLOSED");
        close();
    }
}
