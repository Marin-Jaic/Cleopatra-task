package eu.costengineering.dataprocess.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Supplier;

public interface Parser {

    /**This method should take in a CSV file as an input, parse the incoming data, and store it in a fitting manner.
     * With this parsed data, it will return an evaluator that will further handle the calculation logic(see Evaluator).
     *
     * @param file input CSV file
     * @return an evaluator that will carry the logical operations for cost calculations
     */
    Evaluator parseFile(File file) throws FileNotFoundException;
    

    //CHANGED, if parseFile already takes file as argument, I saw no reason for there to be one listed
    // in the factory function
    static Parser createParser(Supplier<Parser> constructor){
        return constructor.get();
    }
}
