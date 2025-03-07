package eu.costengineering.dataprocess.interview.states;

import eu.costengineering.dataprocess.interview.Evaluator;

public abstract class State {
    protected Evaluator evaluator;

    public State(Evaluator evaluator){
        this.evaluator = evaluator;
    }

    abstract public double execute();
}
