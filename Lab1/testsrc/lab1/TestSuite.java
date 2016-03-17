package lab1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestRetriever.class, TestParser.class, TestAccounter.class })
public class TestSuite {
}