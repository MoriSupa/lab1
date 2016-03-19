package lab1;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lab1.entity.Beverage;
import lab1.handler.Accounter;
import lab1.handler.I_Accounter;
import lab1.handler.I_Retriever;
import lab1.handler.Retriever;

@RunWith(Parameterized.class)
public class TestAccounter {
	private static I_Retriever rtr;
	private static JSONObject dic;
	private static I_Accounter accounter;

	private double expected;
	private String[] bev;

	@BeforeClass
	public static void accountInit() {
		rtr = new Retriever();
		dic = rtr.retrieve();
		accounter = new Accounter(dic);
	}

	private static Beverage bevConstructor(String[] bevStr) {
		if ((bevStr == null) || (bevStr.length == 0))
			return null;
		Beverage bev = new Beverage(bevStr[0], bevStr[1], bevStr[2]);
		for (int i = 3; i < bevStr.length; i++)
			bev.addIngredients(bevStr[i]);
		return bev;
	}

	@Parameters
	public static Collection<Object[]> words() {
		return Arrays.asList(new Object[][] {
				// What if there is no argument?
				{ -1, null },

				// What if there is no beverage name?
				{ -1, new String[] { null, "small", "coffee" } },

				// What if beverage name is misspelled?
				{ -1, new String[] { "expresso", "small", "coffee" } },

				// What if there is no category name?
				{ -1, new String[] { "espresso", "small", null } },

				// What if category name is misspelled?
				{ -1, new String[] { "espresso", "small", "caffee" } },

				// What if there is no size information?
				{ -1, new String[] { "espresso", null, "coffee" } },

				// What if the size information is misspelled?
				{ -1, new String[] { "espresso", "smoll", "coffee" } },

				// What if some ingredients are misspelled?
				{ -1, new String[] { "espresso", "small", "coffee", "nilk" } },
				
				// What if some ingredients are incompatible?
				{ -1, new String[] { "espresso", "small", "coffee", "ginger" } },

				// What if the input is "green tea small" ?
				{ (1 + 0.2), new String[] { "green tea", "small", "tea" } },

				// What if the input is "houseblend grand" ?
				{ (0.8 + 1.3), new String[] { "houseblend", "grand", "coffee" } },

				// What if the input is "green tea small ginger milk" ?
				{ (1 + 0.2 + 0.6 + 0.3), new String[] { "green tea", "small", "tea", "ginger", "milk" } } });
	}

	public TestAccounter(double expected, String[] bev) {
		this.expected = expected;
		this.bev = bev;
	}

	@Test
	public void testAccounter() {
		assertTrue("expected:" + expected + " but was:" + accounter.compute(bevConstructor(bev)),
				expected == accounter.compute(bevConstructor(bev)));
	}
}