package eu.costengineering.dataprocess.interview.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class GroupSelectionListener implements ActionListener{
    private JToggleButton selected = null;
    private ButtonGroup group;
    private ResultListener listener;
    
    public GroupSelectionListener(ButtonGroup group, ResultListener listener){
        this.group = group;
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton currentSelected = (JToggleButton) e.getSource();

        if (this.selected == null){
            this.selected = currentSelected;
        } else if (this.selected.equals(currentSelected)){
            this.group.clearSelection();
            this.selected = null;
        } else {
            this.selected = currentSelected;
        }

        listener.updateType(this.selected);
    }
}
