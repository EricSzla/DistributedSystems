import java.io.*;
import java.net.*;
import java.util.*;

public class TCPEchoServer
{
   private static ServerSocket servSock;
   private static final int PORT = 1235;

   public static void main(String[] args)
   {
      System.out.println("Opening port...\n");
      try
      {
         servSock = new ServerSocket(PORT);      //Step 1.
      }
      catch(IOException ioEx)
      {
         System.out.println("Unable to attach to port!");
         System.exit(1);
      }
      do
      {
         handleClient();
      }while (true);
   }

   private static void handleClient()
   {
      Socket link = null;                        //Step 2.

      try
      {
         link = servSock.accept();               //Step 2.

         /*Scanner input = new Scanner(
			 				link.getInputStream()); //Step 3.*/
          
          ObjectInputStream input = new ObjectInputStream(link.getInputStream());
          
          int staffCount = 0;
          
          try
          {
              do
              {
                  Personnel person =
                  (Personnel) input.readObject();//Typecast.
                  
                  staffCount++;
                  
                  System.out.println(
                                     "\nStaff member " + staffCount);
                  System.out.println(
                                     "Name: " + person.getFirstNames());
                  System.out.println(
                                     "Age: " + person.getAge());
                  System.out.println(
                                     "Address: " + person.getAddress());
              }while (true);
          }
          catch (ClassNotFoundException cnfE){
              System.out.println("Class Personnel not found!");
          }
          catch (EOFException eofEx)
          {
              System.out.println(
                                 "\n\n*** End of file ***\n");
              input.close();
          }
         System.out.println("Done");//Step 4.
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
				link.close();				    //Step 5.
			}
			catch(IOException ioEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
