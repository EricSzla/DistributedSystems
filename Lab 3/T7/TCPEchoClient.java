import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoClient
{
    // Network variables
	private static InetAddress host;
	private static final int PORT = 1235;

    // Main Method
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		accessServer();
	}
    

    
	private static void accessServer()
	{
		Socket link = null;						//Step 1.
        try{
            link = new Socket(host,PORT);		//Step 1.
            
            ObjectOutputStream output = new ObjectOutputStream(link.getOutputStream());
            
            Personnel staff[] = new Personnel[3];
            
            System.out.println("Enter Data For Staff");
            Scanner userInputScanner = new Scanner(System.in);
            
            for(int i=0; i < staff.length; i++){
                System.out.println("Enter name for staff: " + i);
                String name = userInputScanner.nextLine();
                System.out.println("Enter Age for staff: " + i);
                String ageString;
                ageString = userInputScanner.nextLine();
                int age = 0;
                try{
                    age = Integer.valueOf(ageString);
                }catch(Exception e){
                    System.out.println("Age should be a number!");
                    System.out.println("Please run the program again");
                    System.exit(1);
                }
                System.out.println("Enter Address for staff: " + i);
                String address = userInputScanner.nextLine();
                staff[i] = new Personnel(address,name,age);
            }
            
            for (int i=0; i<staff.length; i++)
                output.writeObject(staff[i]);
            output.close();
        }
		catch(IOException ioEx)
		{
			ioEx.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println(
							"\n* Closing connection... *");
				link.close();					//Step 4.
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
