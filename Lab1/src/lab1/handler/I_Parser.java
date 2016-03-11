package lab1.handler;
// No Tamper unless Informing 'RavenSanstete'


import java.util.ArrayList;
import org.json.JSONObject;
import lab1.entity.Beverage;

// Objective: [Basic] 1.Parse the advanced StarBuzz order with no error(Pass tests of wujm2007)
//			  [Advanced] 2.Better as less 'if-else' or 'dead string' as possible.
//			  [Super] 3.Better not use any knowledge a-prior about the dictionary's content(only the structure), which is quite difficult 
// Note[For Users]:To Initialize a Parser: a JsonObject as a dictionary is essential.

// try to be careful of so many possibilities

public interface I_Parser {
	// Input: orders strings[is just args[] ] with a format according to the lab1.pdf
	// Output: According to the design of beverage, the work of parser is only to parse to phrases(which is better for hashmap to process)
	public ArrayList<Beverage> parse(String[] orders);
}
