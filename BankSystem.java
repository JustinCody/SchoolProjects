import java.io.*;
import java.util.*;
public class BankSystem  
{
	private static String pass = "";
	private static String checkAcc = "";
	private static String savAcc = "";
	private static String FirstName = "";
	private static String LastName = "";
	private static String save = "";
	private static String account = "";
	
	//finds users based on account number, if found all data from database line will be assigned into temps(above)
	//if account number not found then pass is retured as default and will give an error later on.
	public String findUser(String acc)throws IOException 
	{
		account = acc;
		importDb(acc);
		return pass;
	}
	//reads in data from database, this file is respentation of a highly secured sever 
	private static void importDb(String acc) throws IOException 
	{
		Scanner scan = new Scanner(new File("CustomerDB.txt"));
		
		while(scan.hasNextLine())
		{
			String current = scan.nextLine();
			
			if(current.contains(acc))
			{
				save = current;
				String[] parts = current.split("#");
				pass = parts[1];
				checkAcc = parts[2];
				savAcc = parts[3];
				FirstName = parts[4];
				LastName = parts[5];
				break;
			}
			
		}
		//if reaches here then pass is not set which means input was wrong
	}
	public String getBalance(String acc, String s)  throws IOException 
	{
		importDb(acc);
		if(s.compareTo("check")==0)
		{
			return checkAcc;
		}
		else
		{
			return savAcc;
		}
	}
	//reads in data from file depening on string s which will contain account type
	//subtracts existing data from amount user wishs to withdraw
	public String withdraw(String s,String amount)  throws IOException 
	{
		int amounts = Integer.parseInt(amount);
		
	
		int checkbal = Integer.parseInt(checkAcc);
		int savbal = Integer.parseInt(savAcc);
		String old = "";
		if(s.compareTo("check")==0)
		{	
			old = checkAcc;
			if(amounts > checkbal)
			{
				return checkAcc;
			}
			
			checkAcc = Integer.toString(checkbal - amounts);
			System.out.println(checkAcc);
			exportDb(old, checkAcc);
			return checkAcc;
		}
		else
		{
			old = savAcc;
			if(amounts >savbal)
			{
				return savAcc;
			}
			savAcc = Integer.toString(savbal - amounts);
			System.out.println(savAcc);
			exportDb(old, savAcc);
			return savAcc;
		}
	}
	
	//same as deposit but with addition
	public String deposit(String s,String amount)  throws IOException 
	{
		int amounts = Integer.parseInt(amount);
		
	
		int checkbal = Integer.parseInt(checkAcc);
		int savbal = Integer.parseInt(savAcc);
		String old = "";
		if(s.compareTo("check")==0)
		{	
			old = checkAcc;
			if(amounts > checkbal)
			{
				return checkAcc;
			}
			
			checkAcc = Integer.toString(checkbal + amounts);
			exportDb(old, checkAcc);
			return checkAcc;
		}
		else
		{
			old = savAcc;
			if(amounts >savbal)
			{
				return savAcc;
			}
			savAcc = Integer.toString(savbal + amounts);
			exportDb(old, savAcc);
			return savAcc;
		}
	}

	
	//reads in current database data and then modifies data and inserts all data back into database.
	public static void exportDb(String old, String replace)throws IOException 
	{
		FileReader fileReader = new FileReader("CustomerDB.txt");  
        BufferedReader bufferedReader = new BufferedReader(fileReader);  
	    List <String> hold = new ArrayList <String>();
		String str = "";
		while((str = bufferedReader.readLine()) != null)
		{
			hold.add(str);
		}
	bufferedReader.close();
	FileWriter fw = new FileWriter("CustomerDB.txt");
			BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i = 0; i < hold.size();i++)
		{
			if(hold.get(i).contains(account))
			{
				String hold2 = (hold.get(i)).replaceAll(old,replace);
				bw.write(hold2+System.getProperty("line.separator"));
			}
			else
			{
				bw.write(hold.get(i)+ System.getProperty("line.separator"));
			}
				
			
		}
		bw.close();
			
	}

	


}