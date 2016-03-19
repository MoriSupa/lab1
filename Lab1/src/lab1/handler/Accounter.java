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
			
		double total=0.0;
		// add the base value
		JSONObject baseInfo,ingre;
			
		if(dic.getJSONObject("basic").has(bev.getCategory())){
			baseInfo = dic.getJSONObject("basic").getJSONObject(bev.getCategory());
			ingre=baseInfo.getJSONObject("ingredient");
		}else{
			return -1;
		}
		
		//refer to kind costs
		total+=robustRefer(baseInfo.getJSONObject("kind"),bev.getBase());
		//refer to size costs
		total+=robustRefer(baseInfo.getJSONObject("size"),bev.getSize());
		
		//refer to ingre costs
		for (int i = 0; i < bev.getIngredients().size(); i++) {
			total+=robustRefer(ingre,bev.getIngredients().get(i));
		}
		return (total<0)? -1:total;
	}

	// compute a list of beverage
	//the behavior is to compute out the total cost only under the condition that all the beverages are legal.
	@Override
	public double compute(ArrayList<Beverage> bevs) {
		double total = 0.0;
		double single;
		for (int i = 0; i < bevs.size(); i++) { 
			if((single=this.compute(bevs.get(i)))<0){
				return -1;
			}else{
				total+=single;
			}
		}
		return total;
	}
	
	
	//Behavior: refer to the cost book, if not have such a key return a quite min constant.
	//Otherwise return the cost it finds.
	private double robustRefer(JSONObject baseObj,String key){
		final double QUITE_MIN=-10000;
		return (baseObj.has(key))? baseObj.getDouble(key):QUITE_MIN;
	}
	
	

}
