package lab1;

import org.json.JSONObject;

import lab1.handler.Accounter;
import lab1.handler.I_Accounter;
import lab1.handler.I_Parser;
import lab1.handler.I_Retriever;
import lab1.handler.Parser;
import lab1.handler.Retriever;

public class Main {
	private final static String FILEPATH="";
	public static void main(String[] args) {
		//read the dictionary
		I_Retriever rtr=new Retriever(FILEPATH);
		JSONObject dic=rtr.retrieve();
		
		//init parser and accounter
		I_Parser parser=new Parser(dic);
		I_Accounter accounter=new Accounter(dic);
		
		//compute the total cost
		double outcome=accounter.compute(parser.parse(args));
		
		//print some desciption here
		
		

		//output the total cost
		
		
	}
	
	
}