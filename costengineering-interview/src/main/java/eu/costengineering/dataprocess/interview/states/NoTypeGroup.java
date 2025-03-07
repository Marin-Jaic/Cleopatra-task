package eu.costengineering.dataprocess.interview.states;

import eu.costengineering.dataprocess.interview.Evaluator;

public class NoTypeGroup extends State{
    private String group;

    public NoTypeGroup(Evaluator evaluator, String group){
        super(evaluator);
        this.group = group;
    }

    public double execute(){
        return this.evaluator.getTotalForGroupingKey(this.group);
    }
}
