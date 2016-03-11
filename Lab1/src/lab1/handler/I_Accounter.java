package lab1.handler;

import java.util.ArrayList;

import lab1.entity.Beverage;


// Objective: compute the cost of beverages(Only depends on the structure of the data)

// Note:[for Users]to initialize an Accounter should give it a JsonObject Dictionary



public interface I_Accounter {
	
	//compute cost of one
	public double compute(Beverage bev);

	//compute cost of several
	public double compute(ArrayList<Beverage> bevs);
}
