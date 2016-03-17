package lab1;

import static org.junit.Assert.assertTrue;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import lab1.entity.Beverage;
import lab1.handler.Accounter;
import lab1.handler.I_Accounter;
import lab1.handler.I_Retriever;
import lab1.handler.Retriever;

public class TestAccounter {
	private static I_Retriever rtr;
	private static JSONObject dic;
	private static I_Accounter accounter;

	@BeforeClass
	public static void accountInit() {
		rtr = new Retriever();
		dic = rtr.retrieve();
		accounter = new Accounter(dic);
	}

	@Test
	public void accountNull() {
		// What if there is no argument?
		Beverage nullbev = null;
		double result = accounter.compute(nullbev);
		assertTrue(result == -1);
	}

	@Test
	public void accountBaseNull() {
		// What if there is no beverage name?
		Beverage bev = new Beverage(null, "small", "coffee");
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountBaseWrong() {
		// What if beverage name is misspelled?
		Beverage bev = new Beverage("expresso", "small", "coffee");
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountCategoryNull() {
		// What if there is no category name?
		Beverage bev = new Beverage("espresso", "small", null);
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountCategoryWrong() {
		// What if category name is misspelled?
		Beverage bev = new Beverage("espresso", "small", "caffee");
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountSizeMissing() {
		// What if there is no size information?
		Beverage bev = new Beverage("espresso", null, "coffee");
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountSizeWrong() {
		// What if the size information is misspelled?
		Beverage bev = new Beverage("espresso", "smoll", "coffee");
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountIngrWrong() {
		// What if some ingredients are misspelled?
		String[] ingrs = { "nilk" };
		Beverage bev = new Beverage("espresso", "small", "coffee", ingrs);
		double result = accounter.compute(bev);
		assertTrue(result == -1);
	}

	@Test
	public void accountNormal() {
		// What if the input is "green tea small" ?
		Beverage bev = new Beverage("green tea", "small", "tea");
		double result = accounter.compute(bev);
		assertTrue(result == 1.2);
	}

	@Test
	public void accountNormalWithIngrs() {
		// What if the input is "green tea small chocolate milk" ?
		String[] ingrs = { "chocolate", "milk" };
		Beverage bev = new Beverage("green tea", "small", "tea", ingrs);
		double result = accounter.compute(bev);
		assertTrue(result == 1.8);
	}
}