import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class AtmSystem
{
	static SecuritySystem ss = new SecuritySystem();
	static BankSystem bs = new BankSystem();
	private static boolean loggedin = false;
	 static String account = "";
	
	public static void main(String [] args)  
	{
		 
		String Location = "NewPaltz";
		
		JFrame frame = new JFrame("Atm Login");
		JFrame selection = new JFrame("Selection");
		JLabel label = new JLabel("");
		JTextField tf = new JTextField("1234123412341234");
		JTextField tf2 = new JTextField("2222");
		JButton button = new JButton("Enter");
		JButton ca = new JButton("Checking");
		JButton sa = new JButton("Saving");
		
		frame.add(label,BorderLayout.NORTH);
		frame.add(tf,BorderLayout.CENTER);
		frame.add(tf2,BorderLayout.SOUTH);
		frame.add(button,BorderLayout.EAST);
		
		button.setBackground(Color.RED);
		button.setOpaque(true);
		
		tf.setColumns(80);
		tf2.setColumns(80);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		 button.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) 
            {
				try{
					
					account = tf.getText();
                if( ss.checkInfo(tf.getText(),tf2.getText()) == true)
				{
					frame.dispose();
					loggedin = true;
					JLabel op1 = new JLabel("Select Account");
					selection.add(op1,BorderLayout.NORTH);
					selection.add(ca,BorderLayout.WEST);
					selection.add(sa,BorderLayout.EAST);
					selection.pack();
					selection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					selection.setLocationRelativeTo(null);
					selection.setVisible(true);
					
				}
				else
				{
					label.setText("Wrong info, check again");
						frame.pack();
				}
				}
				catch(IOException r)
				{
					System.out.println("error");
				}
            }
        });

			ca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a)
            {
				
               optionInterface("check");
			   selection.dispose();
			}
			});

			sa.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent b)
            {
				optionInterface("saving");
				selection.dispose();
			}
			}); 
					

	}
	private static void optionInterface(String s)  //string s is account type
	{
		
		JFrame options = new JFrame();
		
		JButton b1 = new JButton("Check Balance");
		JButton b2 = new JButton("Withdraw");
		JButton b3 = new JButton("Deposit");
		JButton b4 = new JButton("Change Pin");
		options.setLayout(new GridLayout(5,5));
		options.add(b1);
		options.add(b2);
		options.add(b3);
		options.add(b4);
		options.pack();
		options.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		options.setLocationRelativeTo(null);
		options.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
            {
				try{
					
				
				checkbalance(s); //checkbalance
				options.dispose();
				}
				catch(IOException al )
				{
					
				}
			}
			});
		b2.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
            {
				withdraw(s);  //withdraw
				options.dispose();
			}
			});	
		b3.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
            {
				deposit(s); //deposit
				options.dispose();
			}
			});	
		b4.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e)
            {
				changePin(); //change pin
				options.dispose();
			}
			});	
	}
	//check current balance
	private static void checkbalance(String s)  throws IOException 
	{
		
			JFrame balance = new JFrame();
			JLabel bal = new JLabel();
			bal.setText("Current balance is $"+bs.getBalance(account,s)); //get balance from account;
			balance.add(bal,BorderLayout.NORTH);
			JButton returnz = new JButton("return");
			balance.add(returnz,BorderLayout.EAST);
			balance.pack();
			balance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			balance.setLocationRelativeTo(null);
			balance.setVisible(true);
			returnz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				balance.dispose();
				optionInterface(s);
			}
			});	
			
		
	}
	//updates database through banksystem for withdraws
	private static void  withdraw(String s)
	{
		JFrame balance = new JFrame();
			JLabel bal = new JLabel();
			bal.setText("How much do you wish to withdraw?");
			JTextField jtf = new JTextField("");
			//bal.setText("Current balance is $"+ bs.withdraw(s,"20")); //get balance from account;
			balance.add(bal,BorderLayout.NORTH);
			JButton enter = new JButton("Enter");
			JButton returnz = new JButton("return");
			balance.add(enter,BorderLayout.WEST);
			balance.add(jtf,BorderLayout.CENTER);
			balance.add(returnz,BorderLayout.EAST);
			balance.pack();
			balance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			balance.setLocationRelativeTo(null);
			balance.setVisible(true);
			returnz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				balance.dispose();
				optionInterface(s);
			}
			});	
			enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				String withdraw = jtf.getText();
				try{
					
				bal.setText("Current balance is $"+ bs.withdraw(s,withdraw));
				}
				catch(IOException ew)
				{

				}
			}
			});	
	}
	//updates database through banksystem with deposit amount
	private static void  deposit(String s)
	{
		JFrame balance = new JFrame();
			JLabel bal = new JLabel();
			bal.setText("How much do you wish to Deposit?");
			JTextField jtf = new JTextField("");
			//bal.setText("Current balance is $"+ bs.withdraw(s,"20")); //get balance from account;
			balance.add(bal,BorderLayout.NORTH);
			JButton enter = new JButton("Enter");
			JButton returnz = new JButton("return");
			balance.add(enter,BorderLayout.WEST);
			balance.add(jtf,BorderLayout.CENTER);
			balance.add(returnz,BorderLayout.EAST);
			balance.pack();
			balance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			balance.setLocationRelativeTo(null);
			balance.setVisible(true);
			returnz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				balance.dispose();
				optionInterface(s);
			}
			});	
			enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {
				String deposit= jtf.getText();
				try{
					
				bal.setText("Current balance is $"+ bs.deposit(s,deposit));
				}
				catch(IOException ew)
				{

				}
			}
			});	
	}
	//change pin, asks secuitysystem to change pin
	private static void changePin() 
	{
		JButton enter = new JButton("Enter");
		JFrame pinchange = new JFrame();
		JTextField old = new JTextField("Old pass");
		JTextField news = new JTextField("new pass");
		pinchange.add(old,BorderLayout.NORTH);
		pinchange.add(news,BorderLayout.SOUTH);
		pinchange.add(enter,BorderLayout.WEST);
		
		pinchange.pack();
		pinchange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pinchange.setLocationRelativeTo(null);
		pinchange.setVisible(true);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
            {	try
				{
				ss.pinUpdate(old.getText(),news.getText());
				}
				catch(IOException i){}
			}
			});	
	}
}

