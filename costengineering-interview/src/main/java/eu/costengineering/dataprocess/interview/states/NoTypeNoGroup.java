package eu.costengineering.dataprocess.interview.states;

import eu.costengineering.dataprocess.interview.Evaluator;

public class NoTypeNoGroup extends State{
    public NoTypeNoGroup(Evaluator evaluator){
        super(evaluator);
    }

    public double execute(){
        return this.evaluator.getTotalCost();
    }
}
