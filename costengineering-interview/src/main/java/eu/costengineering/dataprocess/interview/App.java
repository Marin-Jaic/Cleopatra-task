package eu.costengineering.dataprocess.interview;

import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import eu.costengineering.dataprocess.interview.listeners.GroupSelectionListener;
import eu.costengineering.dataprocess.interview.listeners.KeyListener;
import eu.costengineering.dataprocess.interview.listeners.ResultListener;

public class App extends JFrame{
    private JToggleButton[] typeButtons;
    private JButton calculateButton;
    
    private JTextField keyField;

    private JLabel resultLabel;
    public App(CostType[] types, Evaluator evaluator){
        super("Data process");

        
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel appPanel = new JPanel();
        appPanel.setLayout(null);
        
        int keyFieldWidth = 300;
        int keyFieldHeight = 30;

        //TextField for getting the grouping key
        keyField = new JTextField();
        keyField.setBounds(150, 20 , keyFieldWidth, keyFieldHeight);
        keyField.setFont(new Font("sans-serif", Font.BOLD, 15));
        appPanel.add(keyField);

        //Button for triggering the evaluator function call
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 20 + keyFieldHeight + 5, keyFieldWidth, keyFieldHeight);
        calculateButton.setFont(new Font("sans-serif", Font.BOLD, 15));
        appPanel.add(calculateButton);

        //Calculated cost output label
        resultLabel = new JLabel("Calculated cost:");
        resultLabel.setBounds(150, 20 + keyFieldHeight * 2 + 5 , keyFieldWidth, keyFieldHeight);
        resultLabel.setFont(new Font("sans-serif", Font.PLAIN, 20));
        appPanel.add(resultLabel);

        //Toggleable button list for selecting a single cost type to be isolated
        typeButtons = new JToggleButton[types.length];
        
        //Button container
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);

        int button_width = 100;
        int button_height = 30;

        JLabel typesTitle = new JLabel("Types:");
        typesTitle.setBounds(0, 0, button_width, button_height);
        typesTitle.setFont(new Font("sans-serif", Font.BOLD, 15));
        buttonPanel.add(typesTitle);

        //Ensures that only one toggleable button is selected at one time
        ButtonGroup group = new ButtonGroup();

        //Tracks the evaluator function to be called upon requesting a result via a state
        //Triggers upon clicking on calculateButton
        //Subscribed to key Listener and GroupSelectionListener so as to be aware of the grouping and cost type
        ResultListener resultListener = new ResultListener(evaluator, resultLabel);
        
        //Triggers on any change in the TextField
        //Notifies ResultListener of any changes to the grouping key
        KeyListener keyListener = new KeyListener(resultListener);
        //Triggers on JToggleButton click
        //Ensures propper functionality of the group, notifies ResultListener of the current active CostType
        GroupSelectionListener groupListener = new GroupSelectionListener(group, resultListener);

        
        calculateButton.addActionListener(resultListener);
        keyField.getDocument().addDocumentListener(keyListener);
        
        //Defines the number of buttons, based on the number of CostTypes
        for(int i = 0; i < types.length; i++){
            typeButtons[i] = new JToggleButton(types[i].name());

            typeButtons[i].addActionListener(groupListener);

            typeButtons[i].setBounds(0, button_height + (button_height + 5) * i, button_width, button_height);
            group.add(typeButtons[i]);

            buttonPanel.add(typeButtons[i]);
        }
        
        buttonPanel.setBounds(20, 10, button_width, (button_height + 5) * (types.length + 1));
        appPanel.add(buttonPanel);
        
        int width = 500;
        int height = Math.max((button_height + 5) * (types.length + 1) + 50, (keyFieldHeight + 5) * 3 + 60);
        setSize(width, height);

        add(appPanel);
    }

    public void updateResult(double result){
        resultLabel.setText("Calculated cost: " + result);
    }
}
