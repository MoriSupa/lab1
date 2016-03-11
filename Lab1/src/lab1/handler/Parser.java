package lab1.handler;


import java.util.ArrayList;

import org.json.JSONObject;

import lab1.entity.Beverage;

public class Parser implements I_Parser {
	private JSONObject dic;
	
	public Parser(JSONObject _dic){
		dic=_dic;
	}
	
/********************************************************/
	//Do any thing you want here, 'mygithubhlm' 
	@Override
	public ArrayList<Beverage> parse(String[] orders) {
		// TODO Auto-generated method stub
		return null;
	}
}
