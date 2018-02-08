import java.io.*;
import java.net.*;
import java.util.*;

/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This class is responsible for dealing each client connected to the server.
 *
 */

class ClientThread extends Thread
{
    private Socket client;   // The Client
    private Resource items[]; // The items
    private Scanner input; // input
    private PrintWriter output; // output
    private Notifier notifier; // notifier

    // Constructor takes three parameters
    // Socket - the client
    // Resource[] - the items
    // Notifier - to notify the clients
    public ClientThread(Socket socket, Resource resource[], Notifier notifier)
    {
        // Init variables
        client = socket;
        items = resource;
        this.notifier = notifier;
        try
        {
            //Create input and output streams on the socket...
            input = new Scanner(client.getInputStream());
            output = new PrintWriter(client.getOutputStream(),true);
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    // Method extented from the thread class, runs in the background.
	public void run()
	{
		String request = "";
        String errorMsg = "";
        int c = notifier.getCurrentAuction(); // get current auction
        
        // Send initial information to the freshly connected client.
        // The e.g. information is as follow:
        // Description%60%1%
        // Desc%Time%Price%Empty
        output.println(items[c].getDesc() + "%" + String.valueOf(items[c].getTimeLeft()) + "%" + String.valueOf(items[c].getCurrentBid())+ "% ");
        
        // Do while client doesnt quit or while there is items to be listed
		do
		{
			request = input.nextLine(); // Receive client's request.
            c = notifier.getCurrentAuction(); // get current
            String outputMsg = ""; // init
            // Check if the client's bid is still higher
            if(Integer.parseInt(request) <= items[c].getCurrentBid()){             // If client's bid is lower.
                errorMsg = "Someone bet higher in the meantime, try again!"; // Set error msg.
                // Notify the client about the error specified above
                notifier.notifyOne((items[c].getDesc() + "%" + String.valueOf(items[c].getTimeLeft()) + "%" +
                                   String.valueOf(items[c].getCurrentBid()) + "%" + errorMsg), c);
            }else if(items[c].getTimeLeft() <= 0){
                // If time have expired, notify ALL the clients about the sale.
                notifier.notifyAll(items[c].getDesc() + "%0%" +
                                   String.valueOf(items[c].getCurrentBid()) + "% ---- ITEM SOLD ---");
                request = "0";
            }else{
                int newTime = items[c].setNewTime(); // set new time
                int newBid = items[c].setNewBid(Integer.parseInt(request)); // Set new value to the resource
                // Notify ALL the clients
                notifier.notifyAll(items[c].getDesc() + "%" + String.valueOf(newTime) + "%" +
                                   String.valueOf(newBid) + "% ");
            }
		}while (!request.equals("0") || notifier.getCurrentAuction() != 5);

		try
		{
			System.out.println(
						"Closing down connection...");
			client.close();
		}
		catch(IOException ioEx)
		{
			System.out.println(
				"Unable to close connection to client!");
		}
	}
}
