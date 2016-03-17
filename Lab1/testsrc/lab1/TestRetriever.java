package lab1;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import lab1.handler.Retriever;

public class TestRetriever {
	private static Retriever rtr;
	private static JSONObject dic;

	@BeforeClass
	public static void retrieverInit() {
		rtr = new Retriever();
		dic = rtr.retrieve();
	}

	@Test
	public void retrieveBasic() {
		// Test if base is retrieved.

		JSONObject basic = dic.getJSONObject("basic");
		// System.out.println(basic);
		assertNotNull(basic);
	}
}