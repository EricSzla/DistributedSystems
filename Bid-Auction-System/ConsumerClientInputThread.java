import java.io.*;
import java.net.*;
import java.util.*;

/*
*
*   Program Developed By Eryk Szlachetka
*   This class is responsible for dealing with the client's input.
*
 */

public class ConsumerClientInputThread extends Thread
{
    private Socket socket; // Socket
    private Scanner input; // Scanner
    private ConsumerClientListenThread consumerClientListenThread; // Listen Thread which is passed in constructor
    private PrintWriter networkOutput;                              // Printwriter
    
    
    // There are two parameters passed in the constructor, the socket and the listen thread
   public ConsumerClientInputThread(Socket socket, ConsumerClientListenThread consumerClientListenThread)
   {
       // Initialize variables
       this.socket = socket;
       this.consumerClientListenThread = consumerClientListenThread;
       
       try
       {
           networkOutput =
           new PrintWriter(
                           socket.getOutputStream(),true);
       }
       catch(IOException ioEx)
       {
           ioEx.printStackTrace();
       }
   }

    // Run method extended from the Thread class
	public void run()
	{
        //Set up stream for keyboard entry...
        Scanner userEntry = new Scanner(System.in);
        
        String message = ""; // Message
        String[] parts; // Response values.
        int input = -1; // Users input.
        
        // Declare frame messages
        String product, additionalMsg, errorMsg = "";
        
        
        // Keep the loop alive until user decides to exit or time has expired.
        do{
            if(!message.equals("Quit")){
                System.out.println(errorMsg + "\n"); // Print errorMsg (might be empty)
                // Ask user for a bid
                System.out.print(
                                 "Enter a bigger bid. ('QUIT' to exit): ");
                
                message =  userEntry.nextLine(); // Get users input
                try{
                    input= Integer.parseInt(message); // parse input
                    errorMsg = "";
                    // set appropriate error msg if any
                    if(input <= consumerClientListenThread.returnCurrentBid()){
                        errorMsg = "Your bid is too low!";
                    }else if(consumerClientListenThread.returnCurrentTimeLeft() <= 0){
                        errorMsg = "You run out of time!";
                    }else{
                        // otherwise, output the bid to the server
                        networkOutput.println(message);
                        message = "";
                    }
                }catch (Exception e){
                    errorMsg = "Enter a valid number";
                }
                
                
            }
        }while(!message.equals("QUIT"));

		try
		{
			System.out.println(
						"Closing down connection...");
			socket.close();
		}
		catch(IOException ioEx)
		{
			System.out.println(
				"Unable to close connection to server!");
		}
	}
}
