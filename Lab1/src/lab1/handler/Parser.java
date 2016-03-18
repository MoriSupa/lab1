package lab1.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import lab1.entity.Beverage;

public class Parser implements I_Parser {
	private JSONObject dic;

	public Parser(JSONObject _dic) {
		dic = _dic;
	}

	/********************************************************/
	// Do any thing you want here, 'mygithubhlm'
	@Override
	public ArrayList<Beverage> parse(String[] orders) {
		// TODO Auto-generated method stub
		ArrayList<Beverage> result = new ArrayList<Beverage>();
		/**
		 * 锟斤拷JSON锟侥硷拷锟叫伙拷取锟斤拷dic锟侥硷拷锟斤拷锟叫分得碉拷一些JSON锟斤拷锟斤拷 1锟斤拷锟斤拷锟饺碉拷锟斤拷锟斤拷
		 */
		JSONObject basic = this.dic.getJSONObject("basic");
		JSONObject coffee = basic.getJSONObject("coffee");
		JSONObject coffeeKind = coffee.getJSONObject("kind");

		// 2锟斤拷锟斤拷锟斤拷锟斤拷锟�
		JSONObject tea = basic.getJSONObject("tea");
		JSONObject teaKind = tea.getJSONObject("kind");

		// 3锟斤拷锟斤拷锟斤拷锟斤拷系锟斤拷锟斤拷锟�
		JSONObject mix = this.dic.getJSONObject("mix");

		// 锟矫★拷锟斤拷锟斤拷锟街割订锟斤拷为锟斤拷锟斤拷锟斤拷锟较ｏ拷锟街憋拷锟斤拷
		if (orders.length == 0) {
			return result;
		}

		String order = "";
		for (int i = 0; i < orders.length; i++) {
			order += orders[i] + " ";
		}
		String[] allBev = order.split(";");

		// for循锟斤拷锟斤拷锟轿达拷锟斤拷锟斤拷锟矫匡拷锟斤拷锟斤拷锟�
		for (int i = 0; i < allBev.length; i++) {
			// 实锟斤拷锟斤拷一锟斤拷锟斤拷锟斤拷
			Beverage beverage = new Beverage("", "", "");
			String[] orderStrs = allBev[i].toLowerCase().split(" ");
			List<String> listOrder = new ArrayList<String>();
			for (int k = 0; k < orderStrs.length; k++) {
				listOrder.add(orderStrs[k]);
			}
			// 锟斤拷锟斤拷锟斤拷锟斤拷
			try {
				Integer.parseInt(orderStrs[0]);
				listOrder.remove(0);
			} catch (Exception e) {

			}

			// Set the beverage's name and category
			String maybeBev = listOrder.get(0) + " " + listOrder.get(1);
			if (teaKind.has(maybeBev)) {
				beverage.setCategory("tea");
				beverage.setBase(maybeBev);
			} else if (teaKind.has(listOrder.get(0))) {
				beverage.setCategory("tea");
				beverage.setBase(listOrder.get(0));
			} else if (coffeeKind.has(listOrder.get(0))) {
				beverage.setCategory("coffee");
				beverage.setBase(listOrder.get(0));
			} else if (mix.has(maybeBev)) {
				String mixName = mix.getString(maybeBev);
				String[] nameAndIngre = mixName.split(",");
				if (coffeeKind.has(nameAndIngre[0])) {
					beverage.setCategory("coffee");
				} else {
					beverage.setCategory("tea");
				}
				beverage.setBase(nameAndIngre[0]);
				beverage.addIngredients(nameAndIngre[1]);
			} else if (mix.has(listOrder.get(0))) {
				String mixName = mix.getString(listOrder.get(0));
				String[] nameAndIngre = mixName.split(" , ");
				if (coffeeKind.has(nameAndIngre[0])) {
					beverage.setCategory("coffee");
				} else {
					beverage.setCategory("tea");
				}
				beverage.setBase(nameAndIngre[0]);
				beverage.addIngredients(nameAndIngre[1]);
			} else {
				continue;
			}

			// 锟叫讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷锟街伙拷锟斤拷锟斤拷锟斤拷锟斤拷
			if (beverage.getBase().equals(maybeBev)) {
				listOrder.remove(0);
				listOrder.remove(0);
			} else {
				listOrder.remove(0);
			}

			if (listOrder.size() == 0) {
				continue;
			}

			// Set size of the beverage
			JSONObject size = coffee.getJSONObject("size");
			if (size.has(listOrder.get(0))) {
				beverage.setSize(listOrder.get(0));
				listOrder.remove(0);
			} else {
				continue;
			}
			
			
			boolean hasWrongIngre = false;
			
//			if the name of the beverage is mix and don't have any the ingredients 
			if (listOrder.size() == 0 && beverage.getIngredients() != null) {
			} else {
				// the name of the beverage is not mix ,set beverage's ingredients
				JSONObject ingredients = this.dic.getJSONObject("ingredient");
				for (int j = 0; j < listOrder.size(); j++) {
					if (ingredients.has(listOrder.get(j))) {
						beverage.addIngredients(listOrder.get(j));
					} else if(j+1<listOrder.size()&&ingredients.has(listOrder.get(j)+" "+listOrder.get(j+1))){
							beverage.addIngredients(listOrder.get(j)+" "+listOrder.get(j+1));
							j++;
					}else{
							hasWrongIngre = true;
							break;
					}
				}
			}
			
			if(hasWrongIngre){
				continue;
			}
			
			result.add(beverage);
			
		}
		return result;
	}

}
