package lab1.handler;
// No Tamper unless Informing 'RavenSanstete'


import java.util.ArrayList;
import org.json.JSONObject;
import lab1.entity.Beverage;

// Objective: [Basic] 1.Parse the advanced StarBuzz order with no error(Pass tests of wujm2007)
//			  [Advanced] 2.Better as less 'if-else' or 'dead string' as possible.
//			  [Super] 3.Better not use any knowledge a-prior about the dictionary's content(only the structure), which is quite difficult 
// Note[For Users]:To Initialize a Parser: a JsonObject as a dictionary is essential.


public interface I_Parser {
	// Input: orders strings with a format according to the lab1.pdf
	public ArrayList<Beverage> parse(String orders);
}
