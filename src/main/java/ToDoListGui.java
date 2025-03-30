
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGui extends JFrame implements ActionListener {

  //taskPanel will act as a container for tastComponentPanel
    //tastComponentPanel will store all of the taskComponents
    private JPanel taskPanel, taskComponentPanel;

    public ToDoListGui(){
        super("To Do List App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(CommonConstants.GUI_SIZE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        addGuiComponents();
    }
    private void addGuiComponents(){
        //banner text
        JLabel bannerLable=new JLabel("To-Do List");
       // bannerLable.setFont();
        bannerLable.setBounds(
                (CommonConstants.GUI_SIZE.width-bannerLable.getPreferredSize().width)/2,
                15,
                CommonConstants.BANNER_SIZE.width,
                CommonConstants.BANNER_SIZE.height
        );

         //taskPanel
        taskPanel=new JPanel();
        //tastComponentPanel
        taskComponentPanel=new JPanel();
        taskComponentPanel.setLayout(new BoxLayout(taskComponentPanel,BoxLayout.Y_AXIS));
        taskPanel.add(taskComponentPanel);


        // add scrolling to the taskPanel
        JScrollPane scrollPane=new JScrollPane(taskPanel);
        scrollPane.setBounds(8,70,CommonConstants.TASKPANEL_SIZE.width,CommonConstants.TASKPANEL_SIZE.height);
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.setMaximumSize(CommonConstants.TASKPANEL_SIZE);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //changing the speed of the scroll bar
        JScrollBar verticalScrollBar=new JScrollBar();
        verticalScrollBar.setUnitIncrement(20);

        //add task button
        JButton addTaskButton=new JButton("Add Task");
        addTaskButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addTaskButton.setBounds(-5,CommonConstants.GUI_SIZE.height -88,
                CommonConstants.ADDTASKBUTTON_SIZE.width,CommonConstants.ADDTASKBUTTON_SIZE.height

                );
        addTaskButton.addActionListener(this);

        this.getContentPane().add(bannerLable);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(addTaskButton);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command.equalsIgnoreCase("Add Task")){
            // create a task component
            TaskComponent taskComponent=new TaskComponent(taskComponentPanel);
            taskComponentPanel.add(taskComponent);

            //make the previous task appear diasbled
//            if(taskComponent.getComponentCount()>1)
//            {
//                TaskComponent previous=(TaskComponent) taskComponentPanel.getComponent(
//                        taskComponentPanel.getComponentCount()-2);
//                previous.getTaskField().setBackground(null);
//
//            }


            //make the task field request focus after creation
            taskComponent.getTaskField().requestFocus();
            repaint();
            revalidate();
        }
    }
}
