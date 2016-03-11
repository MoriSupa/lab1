package lab1.handler;

import java.util.ArrayList;

import org.json.JSONObject;

import lab1.entity.Beverage;

public class Accounter implements I_Accounter {
	private JSONObject dic;
	
	public Accounter(JSONObject _dic){
		dic=_dic;
	}
	@Override
	public double compute(Beverage bev) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double compute(ArrayList<Beverage> bevs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
