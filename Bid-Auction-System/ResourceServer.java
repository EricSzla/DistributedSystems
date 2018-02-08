import java.io.*;
import java.net.*;

/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This is the server program, class is responsible for dealing with initialization and managment of server related classes and variables.
 *   Also responsible for the connection with clients
 *
 */

public class ResourceServer
{
	private static ServerSocket servSocket; // Socket
    private static int PORT = 0;

    // Main function, executed when program starts
	public static void main(String[] args)
									throws IOException
	{
        // Receive port from the command line
        // Ensure the length and that its a number not a string
        // Otherwise provide error msg and exit the program.
        if(args.length < 1){
            System.out.println("Please specify port");
            return;
        }
        try{
            PORT = Integer.parseInt(args[0]);
        }catch(Exception e){
            System.out.println("Please provide valid number");
            return;
        }
        
		try
		{
			servSocket = new ServerSocket(PORT); // new socket
		}
		catch (IOException ioEx)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

        //Create a notifier, that will keep track of clients connected
        //and notify them when new bid is received (through resource class)
        Notifier notifier = new Notifier();
        
        
		//Create a Resource object with
		//Price starting at 1
    
        Resource items[] = new Resource[5];
        items[0] = new Resource(0,"Set of kitchen knifes");
        items[1] = new Resource(1,"MacBook Pro Mid-2012 15 inch");
        items[2] = new Resource(2,"Mercedes CLK200 1.9 2002");
        items[3] = new Resource(3,"Samsung Galaxy S8+");
        items[4] = new Resource(4,"Armani Leather Wallet");
         
		//Create a timeReducer thread, passing Resource object as an argument to the thread constructor.
		TimeReducer timeReducer = new TimeReducer(items, notifier);

		//Start the Time Reducer thread.
		timeReducer.start();
		do
		{
			//Wait for a client to make connection...
			Socket client = servSocket.accept();
			System.out.println("\nNew client accepted.\n");
            // Add the client to the notifier, to keep track
            notifier.addClient(client);
            
            // Create a ClientThread that will handle all the communication
            // with the coresponding client, takes three parameters, the Socket, Resource and Notifier objects.
			ClientThread handler =
						new ClientThread(client,items, notifier);

			//Start the ClientThread.
			handler.start();
		}while (true);		//Server will run indefinitely.
	}
}
