package lab1;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lab1.entity.Beverage;
import lab1.handler.I_Parser;
import lab1.handler.I_Retriever;
import lab1.handler.Parser;
import lab1.handler.Retriever;

@RunWith(Parameterized.class)
public class TestParser {
	private static I_Retriever rtr;
	private static JSONObject dic;
	private static I_Parser parser;

	private ArrayList<Beverage> expected;
	private String[] args;

	@BeforeClass
	public static void parserInit() {
		rtr = new Retriever();
		dic = rtr.retrieve();
		parser = new Parser(dic);
	}

	private static Beverage bevConstructor(String[] bevStr) {
		if ((bevStr == null) || (bevStr.length == 0))
			return null;
		Beverage bev = new Beverage(bevStr[0], bevStr[1], bevStr[2]);
		for (int i = 3; i < bevStr.length; i++)
			bev.addIngredients(bevStr[i]);
		return bev;
	}

	// Construct an input with 2 beverage orders by args,
	// which is an input with 1 beverage order
	private static String[] multipleInputConstructor(String[] args) {
		String[] s = new String[args.length + 4];
		s[0] = "2";
		int i = 0;
		for (; i < args.length; i++)
			s[i + 1] = args[i];
		s[i + 1] = ";";
		s[i + 2] = "Mocha";
		s[i + 3] = "small";
		return s;
	}

	@Parameters
	public static Collection<Object[]> words() {
		return Arrays
				.asList(new Object[][] {
						// What if there is no argument?
						{ new String[] {}, new String[] {} },

						// What if there is no beverage name?
						{ null, new String[] { "small", "milk", "milk" } },

						// What if there the beverage name is misspelled?
						{ null, new String[] { "Mokha", "small", "milk", "milk" } },

						// What if there is no size information?
						{ null, new String[] { "Mocha", "milk", "milk" } },

						// What if the size is misspelled?
						{ null, new String[] { "Mocha", "smoll", "milk", "milk" } },

						// What if some ingredients are misspelled?
						{ null, new String[] { "Mocha", "small", "nilk" } },

						// What if the input is "green tea small chocolate" ?
						{ null, new String[] { "green", "tea", "small", "chocolate" } },

						// What if the input is "green tea grand" ?
						{ new String[] { "green tea", "grand", "tea" }, new String[] { "green", "tea", "grand" } },

						// What if the input is "green tea grand ginger" ?
						{ new String[] { "green tea", "grand", "tea", "ginger" },
								new String[] { "green", "tea", "grand", "ginger" } },

						// What if the input is "Mocha small" ?
						{ new String[] { "espresso", "small", "coffee", "chocolate" },
								new String[] { "Mocha", "small" } },

						// What if the input is "Mocha small whip cream" ?
						{ new String[] { "espresso", "small", "coffee", "chocolate", "whip cream" },
								new String[] { "Mocha", "small", "whip", "cream" } }, });
	}

	public TestParser(String[] expected, String[] args) {
		this.expected = new ArrayList<Beverage>();
		if (bevConstructor(expected) != null)
			this.expected.add(bevConstructor(expected));
		this.args = args;
	}

	@Test
	// test the parser with an order with one beverage
	public void testParserSingleBev() {
		assertEquals(expected, parser.parse(args));
	}

	@Test
	// test the parser with an order with 2 beverage (adding Mocha small to an
	// order with one beverage)
	public void testParserMultiBev() {
		expected.add(bevConstructor(new String[] { "espresso", "small", "coffee", "chocolate" }));
		assertEquals(expected, parser.parse(multipleInputConstructor(args)));
	}
}