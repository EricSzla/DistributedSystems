import java.io.*;
import java.util.*;

public class FileMethods
{
	public static void main(String[] args) throws IOException
	{
		String filename;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter name of file/directory ");
		System.out.print("or press <Enter> to quit: ");
		filename = input.nextLine();

		while (!filename.equals(""))	//Not <Enter> key.
		{
			File fileDir = new File(filename);

			if (!fileDir.exists())
			{
				System.out.println(
						filename + " does not exist!");
				break;	//Get out of loop.
			}

			System.out.print(filename + " is a ");
			if (fileDir.isFile())
				System.out.println("file.");
			else
				System.out.println("directory.");

			System.out.print("It is ");
			if (!fileDir.canRead())
				System.out.print("not ");
			System.out.println("readable.");

			System.out.print("It is ");
			if (!fileDir.canWrite())
				System.out.print("not ");
			System.out.println("writeable.");

			if (fileDir.isDirectory() && fileDir.canRead())
			{
				System.out.println("Contents:");
				String[] fileList = fileDir.list();
				//Now display list of files in directory...
				for (int i=0;i<fileList.length;i++)
					System.out.println("     " + fileList[i]);
            }else if(fileDir.isDirectory()){
                System.out.println("Cannot access folder's contents.");
            }else
			{
				System.out.print("Size of file: ");
				System.out.println(
							fileDir.length() + " bytes.");
			}

			System.out.print(
					"\n\nEnter name of next file/directory ");
			System.out.print("or press <Enter> to quit: ");
			filename = input.nextLine();
		}
		input.close();
	}
}
