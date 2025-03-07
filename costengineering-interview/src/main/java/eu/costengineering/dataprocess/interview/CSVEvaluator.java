package eu.costengineering.dataprocess.interview;

import java.util.HashMap;
import java.util.List;

public class CSVEvaluator implements Evaluator{
    private List<List<String>> data;
    
    public CSVEvaluator(List<List<String>> data){
        this.data = data;
    }    

    public double getCostByType(CostType type){
        if(type == CostType.DIRECT){
            return getDirectCost();
        } else {
            return getIndirectCost();
        }
    }

    public double getCostByTypeAndKey(CostType type, String key){
        if(type == CostType.DIRECT){
            return getDirectForGroupingKey(key);
        } else {
            return getIndirectForGroupingKey(key);
        }
    }
    @Override
    public double getTotalCost() {
        return getIndirectCost() + getDirectCost();
    }
    
    @Override
    public double getTotalForGroupingKey(String key) {
        return getDirectForGroupingKey(key) + getIndirectForGroupingKey(key);
    }

    
    @Override
    public double getDirectForGroupingKey(String key) {
        double cost = 0;

        for(List<String> row: data){
            if (row.get(1).equals("DIRECT") && row.get(3).equals(key)){
                cost+= Double.parseDouble(row.get(2));
            }
        }

        return cost;
    }

    @Override
    public double getIndirectForGroupingKey(String key) {
        double factor = 0;

        for(List<String> row: data){
            if (row.get(1).equals("INDIRECT") && row.get(3).equals(key)){
                factor = Double.parseDouble(row.get(2));
            }
        }
        return getDirectForGroupingKey(key) * factor / 100;
    }

    @Override
    public double getDirectCost() {
        double cost = 0;

        for(List<String> row: data){
            if (row.get(1).equals("DIRECT")){
                cost += Double.parseDouble(row.get(2));
            }
        }

        return cost;
    }

    @Override
    public double getIndirectCost(){
        HashMap<String, Double> values = new HashMap<>();
        HashMap<String, Double> factors = new HashMap<>();

    
        for(List<String> row: data){
            String key = row.get(3);

            if (row.get(1).equals("DIRECT")){
                if (values.containsKey(key)){
                    values.put(key, values.get(key) + Double.parseDouble(row.get(2)));
                } else {
                    values.put(row.get(3), Double.parseDouble(row.get(2)));
                    factors.put(row.get(3), 0.);
                }
            } else {
                factors.put(row.get(3), Double.parseDouble(row.get(2)) / 100);
            }
        }

        double cost = 0;

        for(String key: values.keySet()){
            cost += values.get(key) * factors.get(key);
        }

        return cost;
    }
}
