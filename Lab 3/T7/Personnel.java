import java.io.*;

public class Personnel implements Serializable
//No action required by Serializable interface.
{
	private String address;
	private int age;
	private String firstNames;

	public Personnel(String add, String sName, int age)
	{
		address = add;
		this.age = age;
		firstNames = sName;
	}

	public int getAge()
	{
		return age;
	}

	public String getAddress()
	{
		return address;
	}

	public String getFirstNames()
	{
		return firstNames;
	}

	public void setName(String sName)
	{
		firstNames = sName;
	}
}
