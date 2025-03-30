import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TaskComponent extends JPanel implements ActionListener {
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }
    // this panel is made so that we can make updates to the task component panel when deleting tasks
    private JPanel parentPanel;

    public TaskComponent(JPanel parentPanel)
    {
        this.parentPanel=parentPanel;
        taskField = new JTextPane();
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");


        // Add FocusListener to change background on focus gain/loss
        taskField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                taskField.setBackground(Color.lightGray); // Highlight background

            }

            @Override
            public void focusLost(FocusEvent e) {
                taskField.setBackground(Color.WHITE); // Reset background

            }

            });
        // Add DocumentListener to change background dynamically while typing
        taskField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                taskField.setBackground(Color.lightGray); // Typing detected

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                taskField.setBackground(Color.lightGray); // Text removal detected
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Handle styled text updates, if applicable
            }
        });


        //checkbox
        checkBox=new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.addActionListener(this);

        //delete button
        deleteButton=new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETEBUTTON_SIZE);
        deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteButton.addActionListener(this);
        //add to this task component
        add(checkBox);
        add(taskField);
        add(deleteButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkBox.isSelected()){
            //replaces all the html tags to empty string to grab the main text
            String taskText=taskField.getText().replaceAll("<[^>]*>","");

            // add strikethrough text
            taskField.setText("<html><s>"+taskText+"</s></html>");
            taskField.setBackground(Color.green);
        }
        else{
            String taskText=taskField.getText().replaceAll("<[^>]*>","");
            taskField.setText(taskText);
        }
        if(e.getActionCommand().equalsIgnoreCase("X")){
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }


}
