import java.io.*;
import java.net.*;
import java.util.*;

/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This class is responsible for notyfying the clients.
 *   It keeps track of sockets and notifies them if any other class wishes to, by providing public notyfying methods
 *
 */

public class Notifier
{
    private ArrayList<Socket> clientArray; // Keeps track of the sockets
    private int currentAuction;            // To notify about current item auctioned
    
    // Constructor takes no parameters
	public Notifier()
	{
        // Init
        clientArray = new ArrayList<Socket>();
        currentAuction = 0;
	}

    // Add client to the socket arraylist, to keep track of it
    public void addClient(Socket clientSocket){
        clientArray.add(clientSocket);
    }
    
    // This method will notify all the connected clients about the changes
    public void notifyAll(String msg){
        System.out.println("Notifying All Sockets");
        
        for(Socket s : clientArray){
            try{
                PrintWriter output = new PrintWriter(s.getOutputStream(),true); // Create and intialize socket output writer
                output.println(msg); // output the msg
                
            }catch(IOException ioEx){
                ioEx.printStackTrace();
            }
        }
    }
    // Notyfies only one client, specified in the parameter
    public void notifyOne(String msg, int id){
        try{
            PrintWriter output = new PrintWriter(clientArray.get(id).getOutputStream(),true); // Create and intialize socket output writer
            output.println(msg); // output the msg
            
        }catch(IOException ioEx){
            ioEx.printStackTrace();
        }
    }
    
    // Set auction
    public void setCurrentAuction(int a){
        currentAuction = a;
    }
    // Get auction
    public int getCurrentAuction(){
        return currentAuction;
    }
}
