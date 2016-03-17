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
		double total = 0.0;
		// add the base value

		JSONObject baseInfo = dic.getJSONObject("basic").getJSONObject(bev.getCategory());
		JSONObject ingre = dic.getJSONObject("ingredient");
		total += baseInfo.getJSONObject("kind").getDouble(bev.getBase())
				+ baseInfo.getJSONObject("size").getDouble(bev.getSize());
		for (int i = 0; i < bev.getIngredients().size(); i++) {
			total += ingre.getDouble(bev.getIngredients().get(i));
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
