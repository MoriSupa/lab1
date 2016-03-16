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
		// What if there is no beverage name?
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
		// What if there is no size information?
		String orders[] = { "Mocha", "smoll", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseNoIngr() {
		// What if there is no ingredient?
		String orders[] = { "Mocha", "small" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseIngrWrong() {
		// What if there is no ingredient?
		String orders[] = { "Mocha", "small", "nilk" };
		ArrayList<Beverage> result = parser.parse(orders);
		assertNull(result);
	}

	@Test
	public void parseNormal() {
		// What if there the input is "Mocha small milk milk" ?
		String orders[] = { "Mocha", "small", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);

		// construct the expected result
		ArrayList<Beverage> expect = new ArrayList<Beverage>();
		String[] ingrs1 = { "milk", "milk" };
		expect.add(new Beverage("Mocha", "small", "Coffee", ingrs1));
		assertEquals(expect, result);
	}
}