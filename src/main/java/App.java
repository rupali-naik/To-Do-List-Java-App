import javax.swing.*;

public class App {
    public static void main(String[] Args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoListGui().setVisible(true);
            }
        });
    }
}
