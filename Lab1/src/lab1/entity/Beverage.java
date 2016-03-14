package lab1.entity;

import java.util.ArrayList;

public class Beverage {



	private String base; //name of the beverage
	private String size; //size of the beverage
	private String category; //whether it's a coffee or tea in virtue.
	private ArrayList<String> ingredients; //a list of gradients in name
	
	
	
	
	
	
	
	//this function is for presenting the beverage detail
	public String getDescription(){
		return "";
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



}
