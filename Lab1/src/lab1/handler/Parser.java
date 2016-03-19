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
	
		if (orders.length == 0)  return result; //if order is empty, return empty.

		//organize the raw input of the console
		String order = "";
		for (int i = 0; i < orders.length; i++) {
			order += orders[i] + " ";
		}
		
		//split into several atomic order.
		String[] allBev = order.toLowerCase().split(";");
		
		//begin to process it one by one.
		for (int i = 0; i < allBev.length; i++) {
			int implicitNum=1; //to store the number indicator in the order
			List<String> listOrder=this.strToList(allBev[i]);
			try{
				implicitNum=Integer.parseInt(listOrder.get(0));
				listOrder.remove(0);
				
			}catch(Exception e){
				
			}
			
			Beverage bev;
			while(implicitNum>0){
				if((bev=parse(listOrder))!=null){
					result.add(bev);
				}
				implicitNum--;
			}
		}

		return result;
	}

	
	
	
	
	@Override
	public Beverage parse(String order) {
		// TODO Auto-generated method stub
		return parse(strToList(order));
		
	}

	
	
	
	
	
	
	
	
	private Beverage parse(List<String> listOrder){
		//to get the data this procedure needs
		JSONObject basic = this.dic.getJSONObject("basic");
		JSONObject coffee = basic.getJSONObject("coffee");
		JSONObject coffeeKind = coffee.getJSONObject("kind");
		JSONObject tea = basic.getJSONObject("tea");
		JSONObject teaKind = tea.getJSONObject("kind");
		JSONObject mix = this.dic.getJSONObject("mix");
		JSONObject ingredients = this.dic.getJSONObject("ingredient");
		
		
		
		//to re-organize each order into a word-list
		Beverage beverage = new Beverage("", "", "");

		
		


		//a long assembly line, isn't it. Better than the former program since it only depends on the 
		// structure of the data but not the details.
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
			return null;
		}
		
		//END of assembly, this part is better to be restructured.

		//to remove words have already been removed
		if (beverage.getBase().equals(maybeBev)) {
			listOrder.remove(0);
			listOrder.remove(0);
		} else {
			listOrder.remove(0);
		}

		if (listOrder.size() == 0) {
			return null;
		}

		//parse the size of the beverage
		//has some latent bug here, what if coffee and tea have different size category.
		JSONObject size = coffee.getJSONObject("size");
		if (size.has(listOrder.get(0))) {
			beverage.setSize(listOrder.get(0));
			listOrder.remove(0);
		} else {
			return null;
		}
		
		
		boolean hasWrongIngre = false;
		
//		if the name of the beverage is mix and don't have any the ingredients 
		if (listOrder.size() == 0 && beverage.getIngredients() != null) {
		} else {
			// the name of the beverage is not mix ,set beverage's ingredients
			
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
			return null;
		}
		return beverage;
	}
	
	
	
	
	
	
	//this function is to convert an order in string to a list of words
	private List<String> strToList(String order){
		String[] aOrder=order.split(" ");
		List<String> listOrder=new ArrayList<String>();
		for(int i=0;i<aOrder.length;i++){
			listOrder.add(aOrder[i]);
		}
		return listOrder;
	}
	
	
	
}
