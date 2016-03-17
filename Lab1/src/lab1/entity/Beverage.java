package lab1.entity;

import java.util.ArrayList;

public class Beverage {



	private String base; //name of the beverage
	private String size; //size of the beverage
	private String category; //whether it's a coffee or tea in virtue.
	private ArrayList<String> ingredients; //a list of gradients in name
	
	
	
	//basic constructors
	public Beverage(String _base,String _size,String _category){
		this.base=_base;
		this.size=_size;
		this.category=_category;
		this.ingredients=new ArrayList<String>();
	}
	
	public Beverage(String _base,String _size,String _category,String[] _ingrs){
		this.base=_base;
		this.size=_size;
		this.category=_category;
		this.ingredients=new ArrayList<String>();
		for(int i=0;i<_ingrs.length;i++){
			this.ingredients.add(_ingrs[i]);
		}
	}
	
	
	//this function is for presenting the beverage detail
	public String getDescription(){
		String des=size+" "+base+" with";
		for(int i=0;i<ingredients.size();i++){
			des=des+" "+ingredients.get(i);
		}
		
		return des;
	}
	
	//getter and setter for base
	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	//getter and setter size
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	//getter and setter category
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	//getter for ingredients
	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	//more operations for ingredients
	
	
	//to add an ingredient for a beverage
	public void addIngredients(String ing){
		this.ingredients.add(ing);
	}
	//to clear all ingredients 
	public void clearIngredients(String ing){
		this.ingredients.clear();
	}
	
	
	
	//override for equals, for whether two beverage are equals
	public boolean equals(Object obj){
		Beverage bev=(Beverage)obj;
		boolean ingr_test=true;
		for(int i=0;i<bev.ingredients.size();i++){
			ingr_test=ingr_test&&(this.ingredients.get(i).equals(bev.ingredients.get(i)));
		}
		
		return (this.base.equals(bev.base)&&this.size.equals(bev.size)&&this.category.equals(bev.category)&&ingr_test);
	}


	
	

}
