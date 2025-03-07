package eu.costengineering.dataprocess.interview.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JToggleButton;

import eu.costengineering.dataprocess.interview.CostType;
import eu.costengineering.dataprocess.interview.Evaluator;
import eu.costengineering.dataprocess.interview.states.NoTypeGroup;
import eu.costengineering.dataprocess.interview.states.NoTypeNoGroup;
import eu.costengineering.dataprocess.interview.states.State;
import eu.costengineering.dataprocess.interview.states.TypeGroup;
import eu.costengineering.dataprocess.interview.states.TypeNoGroup;

public class ResultListener implements ActionListener{
    private Evaluator evaluator;
    private State state;
     
    private CostType type = null;
    private String key = null;

    private JLabel resultLabel;
    
    public ResultListener(Evaluator evaluator, JLabel resultLabel){
        this.evaluator = evaluator;
        this.resultLabel = resultLabel;
        state = new NoTypeNoGroup(evaluator);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double result = state.execute();
        System.out.println(result);

        resultLabel.setText("Calculated cost: " + result);
    }

    public void updateType(JToggleButton selected){
        if (selected == null){
            this.type = null;
        }  else {
            this.type = CostType.valueOf(selected.getText());
        }

        updateState();
    }

    public void updateKey(String selected){
        if (selected.strip() == ""){
            this.key = null;
        }  else {
            this.key = selected;
        }

        updateState();
    }

    private void updateState(){
        if (key == null && type == null) {
            state = new NoTypeNoGroup(evaluator);
        } else if (key == null && type != null) {
            state = new TypeNoGroup(evaluator, type);
        } else if (key != null && type == null) {
            state = new NoTypeGroup(evaluator, key);
        } else {
            state = new TypeGroup(evaluator, type, key);
        }
    }
}
