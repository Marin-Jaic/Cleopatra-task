package eu.costengineering.dataprocess.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVParser implements Parser {

    public CSVParser(){
    }
    
    @Override
    public Evaluator parseFile(File file) throws FileNotFoundException{
        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(";"));
                values.replaceAll(String::strip);
                data.add(values);
            }
        } catch (IOException e){
            System.out.println("IOException");
        };

        //The class is called CSVEvaluator, but in reality, with this parsed data, any evaluator 
        //can handle the data for any operations it might be defined for.
        return new CSVEvaluator(data);
    }

}   
