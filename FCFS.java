//The orginal FCFS was lost when i shut down my computer in lab before saving it somewhere. I had to redo everything from scratch which is
//why everything may be simplistic, the work will be more complex and cleaner for future projects.
//Justin Cody
import java.io.*;
import java.util.*;


public class FCFS
{

	public static void main(String[] args) throws Exception
	{
	
		File file = new File("input.data");
		

		 InputStream is = new FileInputStream(file);
		BufferedReader br = new BufferedReader((new InputStreamReader(is)));
		int size = Integer.parseInt(br.readLine()); //gets how many proccessers
		String[] rrPreemptive = br.readLine().split(" "); //would be used if there were mutiple methods for different types of sched algs
		
		int [] pcb1 = new int [size];
		int [] pcb2 = new int [size];
		int [] pcb3 = new int [size];
		String line= "";
	
	//storing into pcbs
		for(int x =0; x<size;x++)
		{
			line = br.readLine();
			String[] tokens = line.split(" ");
			pcb1[x] = Integer.parseInt(tokens[0]);
			pcb2[x] = Integer.parseInt(tokens[1]);
			pcb3[x] = Integer.parseInt(tokens[2]);
		}
		
	

	//put into readyqueue - FCFS
		Queue<Integer> queue = new LinkedList<Integer>();
		int index =0;
		int first = Integer.MAX_VALUE;
		int [] pcb2Clone = new int[size];
		int count = 0;
		while(count!=size)          //just to keep things organized but not needed
		{
			pcb2Clone[count] = pcb2[count];
			count++;
		}
			for(int y =0;y<size;y++)
			{
				for(int z =0; z<size;z++)
				{
					if(first > pcb2Clone[z])
					{
						first = pcb2[z];
						index = z; 
					}
					
				}
				queue.add(index);
				pcb2Clone[index] = Integer.MAX_VALUE;
				first= Integer.MAX_VALUE;
			}
		
		
		//dispatcher
		
		FileWriter writer = new FileWriter("output.data");
		for(int j =0; j<size;j++)
		{
			writer.write(pcb1[queue.peek()]+" "+pcb2[queue.peek()]+" "+pcb3[queue.remove()]);
			writer.write("\n");   
		}
		writer.close();
	}
}