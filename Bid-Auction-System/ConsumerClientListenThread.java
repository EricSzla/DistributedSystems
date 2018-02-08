import java.io.*;
import java.net.*;
import java.util.*;

/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This class is responsible for dealing with the server, and updating the interface
 *
 */

class ConsumerClientListenThread extends Thread
{
    private Socket read_client; // socker
    private Scanner input;      // scanner
    private int timeLeft, currentBid; // ints
    

    // Constructor accepts socket as parammeter
    public ConsumerClientListenThread(Socket socket)
    {
        // Initialize variables
       read_client = socket;
       timeLeft = currentBid = 0;
       
       try
       {
           //Create input stream on the socket.
           input = new Scanner(read_client.getInputStream());
       }
       catch(IOException ioEx)
       {
           ioEx.printStackTrace();
       }
    }

    // Method extented from the Thread class, executes in the background.
	public void run()
	{
        // Declare response and parts
        String response = "";
        String[] parts = {"","",""};
        
        String product, additionalMsg, errorMsg = "";

		do
		{
            // Response format:
            //       ____________________________________________________________________________
            //      | Descripiton (String) | TIME (int <= 60) | currentBid (int) | additionalMsg |
            //       ----------------------------------------------------------------------------
            // Each value in the response is seperated using % i.e. String % int % int.
            // And stored in String parts[];
        
            try{
                response = input.nextLine(); // Receive server's request.
                parts = response.split("\\%"); // Store splitted msg
                // seperate response
                product = parts[0];
                timeLeft = Integer.parseInt(parts[1]);
                currentBid = Integer.parseInt(parts[2]);
                additionalMsg = parts[3];
                
                // Display information
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Clear the screen.
                System.out.println("\nSERVER>\nCurrent product for bid " + product
                                   +"\nCurrent price: " + currentBid
                                   +"\nTimeleft: " + timeLeft);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Clear the screen.
                response = "";
                System.out.println(additionalMsg + "\n");
            }catch(Exception e){
                e.printStackTrace();
            }
            
            
		}while (!response.equals("0"));

		try
		{
			System.out.println(
						"Closing down connection...");
			read_client.close();
		}
		catch(IOException ioEx)
		{
			System.out.println(
				"Unable to close connection to server!");
		}
	}
    
    // Public getters
    public int returnCurrentBid(){
        return currentBid;
    }
    
    public int returnCurrentTimeLeft(){
        return timeLeft;
    }
    
    public String returnCurrentProduct(){
        return "";
    }
    
}
