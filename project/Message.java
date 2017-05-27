/**
 * Write a description of class Message here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Message implements java.io.Serializable
{
    public String cmd;
    public String param;
    
    /**
     * Constructor for objects of class Message
     */
    public Message(String cmd, String param)
    {
        this.cmd = cmd;
        this.param = param;
    }
}
