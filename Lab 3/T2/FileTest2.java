import java.io.*;
import java.util.*;

public class FileTest2
{
	public static void main(String[] args) throws IOException
	{
		String fileName;
		int mark;
		Scanner input= new Scanner(System.in);

		System.out.print("Enter file name: ");
		fileName = input.nextLine();
		PrintWriter output =
					new PrintWriter(new File(fileName));
		System.out.println("Ten marks needed.\n");
		for (int i=1; i<11; i++)
		{
			System.out.print("Enter mark " + i + ": ");
            try{
                mark = input.nextInt();
                output.println(mark);
            }catch(Exception e){
                
            }
			//* Should really validate entry! *
			
			output.flush();
		}
		output.close();
	}
}
