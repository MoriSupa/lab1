package lab1;

import static org.junit.Assert.assertEquals;

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
	public void parseNormal() {
		String orders[] = { "Mocha", "small", "milk", "milk" };
		ArrayList<Beverage> result = parser.parse(orders);
		ArrayList<Beverage> expect = new ArrayList<Beverage>();
		String[] ingrs1 = { "milk", "milk" };
		expect.add(new Beverage("Mocha", "small", "Coffee", ingrs1));
		assertEquals(expect, result);
	}
}