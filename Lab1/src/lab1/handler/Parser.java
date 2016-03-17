package lab1.handler;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public ArrayList<Beverage> parse(String[] orders)  {
		// TODO Auto-generated method stub
		ArrayList<Beverage> result = new ArrayList<Beverage>();
/**
 * 		��JSON�ļ��л�ȡ��dic�ļ����зֵõ�һЩJSON����
 * 		1�����ȵ�����
 */
		JSONObject basic = this.dic.getJSONObject("basic");
		JSONObject coffee = basic.getJSONObject("coffee");
		JSONObject coffeeKind = coffee.getJSONObject("kind");
		
//		2���������
		JSONObject tea = basic.getJSONObject("tea");
		JSONObject teaKind = tea.getJSONObject("kind");
		
//		3��������ϵ�����
		JSONObject mix = this.dic.getJSONObject("mix");
		
//		�á������ָ��Ϊ�������ϣ��ֱ���
		String order = "";
		for(int i=0;i<orders.length;i++){
			order += orders[i]+" ";
		}
		String[] allBev = order.split(";"); 
		
		
//		forѭ�����δ������ÿ������
		for(int i = 0;i < allBev.length; i++){
//			ʵ����һ������
			Beverage beverage = new Beverage("","","");
			String[] orderStrs = allBev[i].toLowerCase().split(" ");
			List<String> listOrder = Arrays.asList(orderStrs);
//			��������
			try{
				Integer.parseInt(orderStrs[0]);
				listOrder.remove(0);
			}catch(Exception e){
				
			}
			
//			Set the beverage's name and category
			String maybeBev = listOrder.get(0)+" "+listOrder.get(1);
			if(teaKind.has(maybeBev)){
				beverage.setCategory("tea");
				beverage.setBase(maybeBev);
			}else if(teaKind.has(listOrder.get(0))){
				beverage.setCategory("tea");
				beverage.setBase(listOrder.get(0));
			}else if(coffeeKind.has(listOrder.get(0))){
				beverage.setCategory("coffee");
				beverage.setBase(listOrder.get(0));
			}else if(mix.has(maybeBev)){  
				String mixName = mix.getString(maybeBev);
				String[] nameAndIngre = mixName.split(",");
				if(coffeeKind.has(nameAndIngre[0])){
					beverage.setCategory("coffee");
				}else{
					beverage.setCategory("tea");
				}
				beverage.setBase(maybeBev);
				beverage.addIngredients(nameAndIngre[1]);
			}else if(mix.has(listOrder.get(0))){
				String mixName = mix.getString(listOrder.get(0));
				String[] nameAndIngre = mixName.split(",");
				if(coffeeKind.has(nameAndIngre[0])){
					beverage.setCategory("coffee");
				}else{
					beverage.setCategory("tea");
				}
				beverage.setBase(listOrder.get(0));
				beverage.addIngredients(nameAndIngre[1]);
			}else{
				continue;
			}
			
//			�ж�����������һ���ֻ���������
			if(beverage.getBase().equals(maybeBev)){
				listOrder.remove(0);
				listOrder.remove(1);
			}else{
				listOrder.remove(0);
			}
			
//			Set size of the beverage
			JSONObject size = coffee.getJSONObject("size");
			if(size.has(listOrder.get(0))){
				beverage.setSize(listOrder.get(0));
				listOrder.remove(0);
			}else{
				continue;
			}
			
//			set beverage's ingredients
			JSONObject ingredients = this.dic.getJSONObject("ingredient");
			for(int j = 0;j < listOrder.size(); j++){
				if(ingredients.has(listOrder.get(j))){
					beverage.addIngredients(listOrder.get(j));
				}
			}
			
			result.add(beverage);
		}
		return result;
	}
	

}

