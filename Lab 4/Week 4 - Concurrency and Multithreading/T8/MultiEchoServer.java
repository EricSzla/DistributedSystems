import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoServer
{
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException
	{
		try
		{
			serverSocket = new ServerSocket(PORT);
		}
		catch (IOException ioEx)
		{
			System.out.println("\nUnable to set up port!");
			System.exit(1);
		}

		do
		{
			//Wait for client...
			Socket client = serverSocket.accept();

			System.out.println("\nNew client accepted. Waiting for a pair...\n");

            Socket client2 = serverSocket.accept();
			//Create a thread to handle communication with
			//this client and pass the constructor for this
			//thread a reference to the relevant socket...
			ClientHandler handler = new ClientHandler(client,client2);

			handler.start();//As usual, this method calls run.
		}while (true);
	}
}

class ClientHandler extends Thread
{
	private Socket client, client2;
	private Scanner input, input2;
	private PrintWriter output, output2;
    private boolean firstClientOK = true;

	public ClientHandler(Socket socket, Socket socket2)
	{
		//Set up reference to associated socket...
		client = socket;
        client2 = socket2;

		try
		{
			input = new Scanner(client.getInputStream());
            input2 = new Scanner(client2.getInputStream());
			output = new PrintWriter(
                                     client.getOutputStream(),true);
            output2 = new PrintWriter(
                                      client2.getOutputStream(),true);
            
            
		}
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}
	}

	public void run()
	{
		String received;
		do
		{
            if(firstClientOK){
                output.println("1"); // ask first client to send 1st message
                //Accept message from client on
                //the socket's input stream...
                received = input.nextLine();
                //Echo message back to client on
                //the socket's output stream...
                output2.println("ECHO: " + received);
                firstClientOK = false;
            }else{
                // Means second client is OK to communicate
                output2.println("1"); // ask first client to send 1st message
                //Accept message from client on
                //the socket's input stream...
                received = input2.nextLine();
                //Echo message back to client on
                //the socket's output stream...
                output.println("ECHO: " + received);
                firstClientOK = true;
            }
            
			

			

		//Repeat above until 'QUIT' sent by client...
		}while (!received.equals("QUIT"));

		try
		{
			if (client!=null)
			{
				System.out.println(
							"Closing down connection...");
				client.close();
			}
		}
		catch(IOException ioEx)
		{
			System.out.println("Unable to disconnect!");
		}
	}
}
