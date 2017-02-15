//Justin Cody  Chapter 9, Question 1

/*
	if correct, no error. if uncorrect then there will be an error
	Test inputs:
	
		tested			result
	
		b				correct
		c				correct
		d				correct	
		+bc				correct
		-/bc*cd			correct
		+-* /bcbcd		correct
		bc				error/not correct
		+b				error/not correct
		*bcd			error/not correct
		landa			error/not correct
*/

import java.util.*;  // import Stack and Scanner classes
//======================================================
class Chapter9
{
  public static void main(String[] args)
  {
    // construct token manager
    ArgsTokenMgr tm = new ArgsTokenMgr(args);

    // construct parser, pass it the token manager
   Chapter9Parser parser = new Chapter9Parser(tm); 

    parser.parse();                    // do parse
  }
}                                     
//======================================================
class ArgsTokenMgr
{
  private int index;
  String input;
  //-----------------------------------------
  public ArgsTokenMgr(String[] args) 
  {
    if (args.length > 0)
      input = args[0];
    else  
      input = "";
    index = 0;
    System.out.println("input = " + input);
  }                            
  //-----------------------------------------
  public char getNextToken() 
  {
    if (index < input.length())
      return input.charAt(index++); // return next char
    else
      return '#';              // # signals end of input
  }
}                                 // end of ArgsTokenMgr
//======================================================
class Chapter9Parser
{
  private ArgsTokenMgr tm;         // token manager
  private Stack<Character> stk;    // stack for parser
  private char currentToken;       // current token
  //-----------------------------------------
  public Chapter9Parser(ArgsTokenMgr tm)
  {
    this.tm = tm;                  // save tm  
    advance();                     // prime currentToken
    stk = new Stack<Character>();  // create stack
    stk.push('$');                 // mark stack bottom
    stk.push('S');                 // push start symbol
  }
  //-----------------------------------------
  private void advance()
  {
    // get next token and save in currentToken
    currentToken = tm.getNextToken();
  }
  //-----------------------------------------
  private void consume(char expected)
  {
	  if(currentToken == expected)
		advance();
	  else
		  throw new RuntimeException("Expecting \"" + expected + "\"");
  }
  //-----------------------------------------
  public void parse()
  {
    E();
	if(currentToken != '#')
		throw new RuntimeException("Expecting end of input");
  }
  
  private void E()
  {
	  switch(currentToken)
	  {
		  case '+':
			consume('+');
			E();
			E();
			break;
			
		  case '-':
			consume('-');
			E();
			E();
			break;
			
		  case '*':
			consume('*');
			E();
			E();
			break;
			
		  case '/':
		  	consume('/');
			E();
			E();
			break;
			
		  case 'b':
			consume('b');
			break;
			
		  case 'c':
			consume('c');
			break;
			
		  case 'd':
			consume('d');
			break;
		
		 default:
			throw new RuntimeException("Expecting");
	  }
	  
  }
  
}
