package eu.costengineering.dataprocess.interview.states;

import eu.costengineering.dataprocess.interview.Evaluator;
import eu.costengineering.dataprocess.interview.CostType;

public class TypeGroup extends State{
    private CostType type;
    private String group;

    public TypeGroup(Evaluator evaluator, CostType type, String group){
        super(evaluator);

        this.type = type;
        this.group = group;
    }

    public double execute(){
        return this.evaluator.getCostByTypeAndKey(this.type, this.group);
    }
}
