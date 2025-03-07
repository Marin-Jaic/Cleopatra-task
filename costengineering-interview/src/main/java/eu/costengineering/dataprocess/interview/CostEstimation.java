package eu.costengineering.dataprocess.interview;

import java.io.File;
import java.io.FileNotFoundException;

public class CostEstimation {

    /** Calling the main method in this class will initialize the user interface for the application.
     *
     * The user should be able to calculate the following from the input CSV document:
     * Total direct cost
     * Total indirect cost
     * Total cost (total direct + total indirect cost)
     * Total direct cost for grouping key X
     * Total indirect cost for grouping key X
     * Total cost for grouping key X
     *
     * Please see the respective interfaces for the parse and evaluation functionalities.
     * The implementation is up to the developer as long as the declared methods are present and the app adheres to the UI guidelines.
     * CSV file's location will be passed as an argument to the main function to start the application.
     *
     * @param args to-be-parsed CSV file's location
     */
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Parser parser = Parser.createParser(CSVParser::new);
        Evaluator evaluator = parser.parseFile(file);

        App app = new App(CostType.values(), evaluator);
        app.setVisible(true);
    }
}
