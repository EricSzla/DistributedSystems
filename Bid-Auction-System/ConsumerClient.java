import java.io.*;
import java.net.*;
import java.util.*;

/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This is the main class that is responsible for declaring background threads and initializing host.
 *
 */

public class ConsumerClient
{
	private static InetAddress host; // host
    private static int PORT = 0; // port
    private static ConsumerClientListenThread consumerClientListenThread; // The thread that deals with server
    private static ConsumerClientInputThread consumerClientInputThread;   // The thread that deals with the input
    
    // Main method executed when program starts
	public static void main(String[] args)
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
            // initialize the host
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("\nHost ID not found!\n");
			System.exit(1);
		}
		startThreads();
	}

    // Function to start the threads
	private static void startThreads()
	{
		Socket socket = null;

		try
		{
			socket = new Socket(host,PORT); // Initialize socket
            consumerClientListenThread = new ConsumerClientListenThread(socket); // Initialize listening thread
            consumerClientInputThread = new ConsumerClientInputThread(socket, consumerClientListenThread); // Init input thread
            // Start the threads
            consumerClientListenThread.start();
            consumerClientInputThread.start();
		}
		catch(Exception ioEx)
		{
			ioEx.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println("\nClosing connection...");
				socket.close();
			}
			catch(Exception ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}

