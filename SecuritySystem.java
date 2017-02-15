import java.io.*;
import java.util.*;
public class SecuritySystem
{

	public BankSystem bs = new BankSystem();
	
	public boolean checkInfo(String acc, String pin) throws IOException 
	{
		//check if pin entered is correct by talking to BankSystem.java
		//pin is never saved
		if (bs.findUser(acc).compareTo(pin) == 0)
		{
			return true;
		}
		else
		{
			return false; 
		}
	
	}
	public boolean pinUpdate(String old, String news)throws IOException
	{
		//updates pin via Atm interface and Bank System 
		//indirect handling to provide extra security
		bs.exportDb(old,news);
		return true;
	}

}