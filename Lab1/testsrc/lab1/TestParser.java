package lab1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import lab1.entity.Beverage;
import lab1.handler.I_Parser;
import lab1.handler.I_Retriever;
import lab1.handler.Parser;
import lab1.handler.Retriever;

public class TestParser {
	private static I_Retriever rtr;
	private static JSONObject dic;
	private static I_Parser parser;

	@BeforeClass
	public static void parserInit() {
		rtr = new Retriever();
		dic = rtr.retrieve();
		parser = new Parser(dic);
	}

	@Test
	public void parseNull() {
		// What if there is no arg?
		String orders[] = {};
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseNameMissing() {
		// What if there is no beverage name?
		String orders[] = { "small", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseNameWrong() {
		// What if there the beverage name is misspelled?
		String orders[] = { "Mokha", "small", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseSizeMissing() {
		// What if there is no size information?
		String orders[] = { "Mocha", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseSizeWrong() {
		// What if the size is misspelled?
		String orders[] = { "Mocha", "smoll", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseIngrWrong() {
		// What if some ingredients are misspelled?
		String orders[] = { "Mocha", "small", "nilk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseNormal() {
		// What if the input is "green tea small" ?
		String orders[] = { "green", "tea", "small" };
		ArrayList<Beverage> result = parser.parse(orders);

		// construct the expected result
		assertEquals(new Beverage("green tea", "small", "tea"), result);
	}

	@Test
	public void parseNormalWithIngrs() {
		// What if the input is "green tea small chocolate" ?
		String orders[] = { "green", "tea", "small", "chocolate" };
		ArrayList<Beverage> result = parser.parse(orders);

		// construct the expected result
		String ingrs[] = { "chocolate" };
		assertEquals(new Beverage("green tea", "small", "tea", ingrs), result);
	}

	@Test
	public void parseMix() {
		// What if the input is "Mocha small" ?
		String orders[] = { "Mocha", "small" };
		ArrayList<Beverage> result = parser.parse(orders);

		// construct the expected result
		String ingrs[] = { "chocolate" };
		assertEquals(new Beverage("espresso", "small", "coffee", ingrs), result);
	}

	@Test
	public void parseMixWithIngrs() {
		// What if the input is "Mocha small whip cream" ?
		String orders[] = { "Mocha", "small", "whip", "cream" };
		ArrayList<Beverage> result = parser.parse(orders);

		// construct the expected result
		String ingrs[] = { "chocolate", "whip cream" };
		assertEquals(new Beverage("espresso", "small", "coffee", ingrs), result);
	}
}