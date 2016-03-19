package lab1.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import lab1.entity.Beverage;

public class Parser implements I_Parser {
	private JSONObject dic;
	private final int[] MINSIZE={2,1,0};
	//min length and max of the possible length of kind name
	private final int MAX_KIND_LENGTH=2;
	public Parser(JSONObject _dic) {
		dic = _dic;
	}

	// Do any thing you want here, 'mygithubhlm'
	//!!IMPORTANT PROCEDURE!!
	@Override
	public ArrayList<Beverage> parse(String[] orders) {
		// TODO Auto-generated method stub
		ArrayList<Beverage> result = new ArrayList<Beverage>();
	
		if (orders.length == 0)  return result; //if order is empty, return empty list.

		//organize the raw input of the console
		String[] allBev=this.refineRaw(orders);
		Beverage bev; //a temporary container
		
		//begin to process it one by one.
		for (int i = 0; i < allBev.length; i++) {
			int implicitNum=1; //to store the number indicator in the order
			List<String> listOrder=this.strToList(allBev[i]);
			try{
				implicitNum=Integer.parseInt(listOrder.get(0)); // if the first one is not a numeric, just skip.
				listOrder.remove(0);
			}catch(Exception e){
				
			}
			
			//to process sub-orders one by one(If it has)
			while(implicitNum>0){
				if((bev=parse(listOrder))!=null){
					result.add(bev);
				}
				implicitNum--;
			}
		}

		return result;
	}

	
	
	
	//It's a wrapper.
	@Override
	public Beverage parse(String order) {
		// TODO Auto-generated method stub
		return parse(strToList(order));
		
	}

	
	
	//to parse an order in list form. 
	//divide the procedure into 3 sub-procedure
	private Beverage parse(List<String> listOrder){
		Beverage beverage = new Beverage();
		//as literal indicates parse kind and size and ingre respectively with a short cut and. return in advance
		return (parseKind(listOrder, beverage)&&parseSize(listOrder,beverage)&&parseIngre(listOrder, beverage))? beverage:null;
	}
	
	
	
	
	
	//sub-procedures for parse
	
	//to parse the kind and category(will be promoted later)
	private boolean parseKind(List<String> listOrder,Beverage bev){
		//a rough test for essential length
		if(listOrder.size()<MINSIZE[0]) return false;
		//get data needed
		JSONObject mix = this.dic.getJSONObject("mix");
		JSONObject basic=this.dic.getJSONObject("basic");
	
		String[] outcome;
		int hint;
		//first see whether it's a mix  
		if((outcome=this.referValid(listOrder,mix,true))!=null){
			//if it is, modify the listOrder and fill the beverage
			listOrder.add(outcome[1]); //add the ingredients to the back of the listOrder
			hint=Integer.parseInt(outcome[2]); //have prior knowledge to it
			outcome=outcome[0].split(" ");
			while(hint>0){
				listOrder.remove(0);
				hint--;
			}
			
			while(hint<outcome.length){
				listOrder.add(0, outcome[hint]);
				hint++;
			}
		}
		
		
		
		//tea and coffee or others are equivalent, thus they can be parsed with only a method
		//to refer to category for base name and category(by iterating the basic's key)
		Iterator<String> itr=basic.keys();
		String curCat;
		outcome=null;
		while(itr.hasNext()){
			curCat=itr.next();
			if((outcome=this.referValid(listOrder, basic.getJSONObject(curCat).getJSONObject("kind"),false))!=null){
				bev.setCategory(curCat);
				bev.setBase(this.reorganizeList(listOrder, Integer.parseInt(outcome[0])));
				break;
			}
		}
		
		return (outcome!=null);
	}
	
	
	private boolean parseSize(List<String> listOrder,Beverage bev){
		//rough test for essential words
		if(listOrder.size()<MINSIZE[1]) return false;
		//to get the size options,based on the category
		JSONObject size=this.dic.getJSONObject("basic").getJSONObject(bev.getCategory()).getJSONObject("size"); 
		
		if (size.has(listOrder.get(0))) {
			bev.setSize(listOrder.get(0));
			listOrder.remove(0);
			return true;
		} 
		
		return false;
	}
	
	
	
	//to parse integredients in the listorder as the name indicators
	private boolean parseIngre(List<String> listOrder,Beverage bev){
		JSONObject ingre=this.dic.getJSONObject("ingredient");
		String [] outcome;
		while(listOrder.size()>0){
			if((outcome=referValid(listOrder,ingre,false))!=null){
				bev.addIngredients(this.reorganizeList(listOrder, Integer.parseInt(outcome[0])));
			}else{ //some ingredients name is invalid
				return false; 
			}
		}
		return true;
	}
	


	//to see whether a word or phrase is fitted in the dictionary, to use it the length of listOrder must have been checked
	//option control whether it's for mix or category reference(TURE FOR MIX,FALSE FOR ATOMIC)
	private String[] referValid(List<String> listOrder,JSONObject dic,boolean option){	
		//may not need to think about the future prefix problem, since we don't have such a rule to parse it.
		String maybeBev=""; //can be more generic by use MIN_BOUND,MAX_BOUND
		String outcome="";
		int upperBound=Math.min(MAX_KIND_LENGTH,listOrder.size());
		for(int i=0;i<upperBound;i++){
			maybeBev=(maybeBev+" "+listOrder.get(i)).trim();
			if(dic.has(maybeBev)){
				if(option) outcome+=dic.getString(maybeBev)+",";
				return (outcome+(i+1)).split(",");
			}
		}
		
		return null;
	}
	
	
	//construct the name and at the same time remove word that has been processed from the list order.
	private String reorganizeList(List<String> listOrder,int hint){
		String outcome="";
		for(int i=0;i<hint;i++){
			outcome+=listOrder.get(0);
			if(i<hint-1) outcome+=" ";
			listOrder.remove(0);
		}	
		return outcome;
	}
	
	
	
	
	//TOOL FUNCTIONS
	
	//this function is to convert an order in string to a list of words
	private List<String> strToList(String order){
		String[] aOrder=order.split(" ");
		List<String> listOrder=new ArrayList<String>();
		for(int i=0;i<aOrder.length;i++){
			listOrder.add(aOrder[i]);
		}
		return listOrder;
	}
	
	
	private String[] refineRaw(String[] arg0){
		//organize the raw input of the console
		String orders = "";
		for (int i = 0; i < arg0.length; i++) {
			orders += arg0[i] + " ";
		}
		
		//split into several atomic order.
		return orders.toLowerCase().split(";");
	}
	
	
}
