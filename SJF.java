//Justin Cody
import java.io.*;
import java.util.*;

public class SJF
{        
		 	static ArrayList<PCB> queue = new ArrayList<PCB>();
	
	public static void main(String [] args)
	{
		fillPcb();
	}

	public static void fillPcb()
	{
		File file = new File("input.data");
		int size =0;
		BufferedReader br =null;
	try{
			InputStream is = new FileInputStream(file); 
			br = new BufferedReader((new InputStreamReader(is)));
			try{
			 size = Integer.parseInt(br.readLine()); 
			String[] rrPreemptive = br.readLine().split(" "); //would be used if premptive or mutiple alogorithms 	
			}
			catch(IOException k){
			}	 
	}
	catch(FileNotFoundException a){
	}
	
		String line= "";
		int arrival = 0;
		int burst = 0;
		int priority = 0;
		int id = 0;
		Queue queue = null;
		
		for(int x =0; x<size;x++)
		{
			try
			{
				line = br.readLine();
			}
			catch(IOException b)
			{
			}
			
			String[] tokens = line.split(" ");    //name is index+1 since input example proc# is ordered.
	
			arrival = Integer.parseInt(tokens[0]); //arrival
			burst = Integer.parseInt(tokens[1]); //burst
			priority= Integer.parseInt(tokens[2]); //priority
			id++;
			
			//System.out.println(arrival + " " + burst+" "+ priority);
		
			readyQueue(new PCB(id,arrival,burst,priority));
			
		}
		
		Dispatcher(size);
	}
	
	public static void readyQueue(PCB proccess)
	{
		//ArrayList<PCB> queue = new ArrayList<PCB>();
	
		queue.add(proccess); //store index of the pcb into queue so we can access any data.	

	}
	 
	public static void Dispatcher(int s)
	{
		int size = s;
		int time = 0;
		PCB hold1 =null;
		PCB hold2 = null;
		try
		{
			FileWriter writer =new FileWriter("output.data");
			ArrayList<PCB> queue2 = new ArrayList<PCB>();
			ArrayList<PCB> sjf = new ArrayList<PCB>();
			int lowest = Integer.MAX_VALUE;
			int index =0;
			
		
			while(queue.isEmpty()==false)
			{
				for(int x = 0; x<queue.size(); x++)
				{
					if(queue.get(x).getAT() <= time)
					{
						queue2.add(queue.remove(x));
						//System.out.println(queue.remove(x));
						x--;  //adjusts where we are in array
					}
				}
				
				for(int y =0;y<queue2.size();y++)
				{
					hold1 = queue2.get(y);
					if( lowest > hold1.getBurst())
					{
						lowest = hold1.getBurst();
						index = y; 
					}
				}
				if(lowest != Integer.MAX_VALUE)
				{
					//System.out.println("here");
					hold1 =queue2.remove(index);
					sjf.add(hold1);
					time = time + hold1.getBurst();
					//System.out.println(queue2);
					
				}
				else
				{
					time++;
				}
				lowest = Integer.MAX_VALUE;

					
			}
			
			while(queue2.isEmpty()==false)
			{
				//System.out.println("here");
				for(int k =0;k<queue2.size();k++)
				{
					hold1 = queue2.get(k);
					if( lowest > hold1.getBurst())
					{
						lowest = hold1.getBurst();
						index = k; 
					}
				}
				hold1= queue2.remove(index);
				sjf.add(hold1);
				hold1 =null;
				index =0;
				lowest = Integer.MAX_VALUE;
			}
				
				int start = 0;
				int end =0;
				int id = 0;
				while(!sjf.isEmpty())
				{
					hold1 = sjf.remove(0);
					id = hold1.getId();
					if(start ==0)
					{
						start = hold1.getAT();
					}
					end = hold1.getBurst() + start;
					writer.write(new Integer(start).toString());
					writer.write(" ");
					writer.write(new Integer(end).toString());
					writer.write(" ");
					writer.write(new Integer(id).toString());
					writer.write("\n");
					start = end; 
				}
				writer.close();
				
			}
		catch(IOException x)
		{
			
		}

		
	
	}
	
	
	
}