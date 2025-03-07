package eu.costengineering.dataprocess.interview.states;

import eu.costengineering.dataprocess.interview.CostType;
import eu.costengineering.dataprocess.interview.Evaluator;

public class TypeNoGroup extends State{
    private CostType type;

    public TypeNoGroup(Evaluator evaluator, CostType type){
        super(evaluator);

        this.type = type;
    }

    public double execute(){
        return this.evaluator.getCostByType(this.type);
    }
}
