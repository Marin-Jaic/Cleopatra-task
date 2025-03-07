package eu.costengineering.dataprocess.interview.listeners;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


public class KeyListener implements DocumentListener{
    private ResultListener listener;
    
    public KeyListener(ResultListener listener){
        this.listener = listener;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            alert(e);
        } catch (BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            alert(e);
        } catch (BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            alert(e);
        } catch (BadLocationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void alert(DocumentEvent e) throws BadLocationException{
        Document document = e.getDocument();
        String key = document.getText(0, document.getLength());
        listener.updateKey(key);
    }
    
}
