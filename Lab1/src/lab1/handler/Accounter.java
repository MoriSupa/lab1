package lab1.handler;

import java.util.ArrayList;

import org.json.JSONObject;

import lab1.entity.Beverage;

public class Accounter implements I_Accounter {
	private JSONObject dic;

	public Accounter(JSONObject _dic) {
		dic = _dic;
	}

	@Override
	public double compute(Beverage bev) {
		if(bev==null) return -1; //test whether beverage null
		
		
		double total = 0.0;
		// add the base value
		JSONObject baseInfo;
		
		
		
		
		
		if(dic.getJSONObject("basic").has(bev.getCategory())){
			baseInfo = dic.getJSONObject("basic").getJSONObject(bev.getCategory());
		}else{
			return -1;
		}
		
		JSONObject ingre = dic.getJSONObject("ingredient");
		
		
		//some test for illegal input
		if(baseInfo.getJSONObject("kind").has(bev.getBase())){
			total+=baseInfo.getJSONObject("kind").getDouble(bev.getBase());
		}else{
			return -1;
		}
		
		if(baseInfo.getJSONObject("size").has(bev.getSize())){
			total+=baseInfo.getJSONObject("size").getDouble(bev.getSize());
		}else{
			return -1;
		}
		
		for (int i = 0; i < bev.getIngredients().size(); i++) {
			if(ingre.has(bev.getIngredients().get(i))){
				total += ingre.getDouble(bev.getIngredients().get(i));
			}else{
				return -1;
			}
		}
		
		return total;
	}

	// compute a list of beverage
	@Override
	public double compute(ArrayList<Beverage> bevs) {
		double total = 0.0;
		for (int i = 0; i < bevs.size(); i++) {
			total += this.compute(bevs.get(i));
		}
		return total;
	}
	
	

}
