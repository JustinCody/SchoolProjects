import java.io.*;
import java.util.*;
public class PCB
{
	private int id,aT, burst,pri;
	public PCB(int idi, int aTi, int bursti, int prii)
	{
		id = idi;
		aT = aTi;
		burst =bursti;
		pri = prii;
	}
	public  int getAT()
	{
		return aT;
	}
	public  int getBurst()
	{
		return burst;
	}
	public int getPri()
	{
		return pri;
	}
	public int getId()
	{
		return id;
	}

}
