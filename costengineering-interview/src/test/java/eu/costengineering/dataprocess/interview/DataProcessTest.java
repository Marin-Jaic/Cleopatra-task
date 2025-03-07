package eu.costengineering.dataprocess.interview;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link eu.costengineering.dataprocess.interview.CostEstimation}, feel free to add you own!
 */
public class DataProcessTest {

    public static final double DELTA = 0.01;
    public static Evaluator evaluator;

    @BeforeClass
    public static void setup() throws IOException {
        //fill in your Example CSV file's path
        String pathName = "";
        File createdFile = new File(pathName);
        Parser exampleParser = Parser.createParser(CSVParser::new); // TODO: fill in your parser
        evaluator = exampleParser.parseFile(createdFile);
    }

    @Test
    public void exampleTest1() {
        assertEquals(2100.0, evaluator.getCostByType(CostType.DIRECT), DELTA);
    }
}
