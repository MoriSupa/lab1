package lab1;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.json.JSONObject;

import lab1.entity.Beverage;
import lab1.handler.Accounter;
import lab1.handler.I_Accounter;
import lab1.handler.I_Parser;
import lab1.handler.I_Retriever;
import lab1.handler.Parser;
import lab1.handler.Retriever;

public class Main {
	public static void main(String[] args) {
		

		//fetch data
		I_Retriever rtr=new Retriever();
		JSONObject dic=rtr.retrieve();
		
		//init parser and accounter
		I_Parser parser=new Parser(dic);
		I_Accounter accounter=new Accounter(dic);
		

		//parse the  orders
		ArrayList<Beverage> bevs=parser.parse(args);
		
		//what if illegal orders occur?
		if(bevs.size()==0){
			System.out.println("No Valid Order.SORRY");
			return;
		}
		
		
		//compute the total cost
		double outcome=accounter.compute(bevs);
		
		//get some desciption here
		for(int i=0;i<bevs.size();i++){
			bevs.get(i).getDescription();
		}
		
		
		//output the total cost
		DecimalFormat df = new DecimalFormat(".0");
		System.out.println("The total cost of your order is: "
				+ df.format(outcome));
		
	}
}