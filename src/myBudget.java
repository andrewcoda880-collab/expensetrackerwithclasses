import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myBudget extends JPanel {

    //This class represents the user's budget. It will be accessed through the "My Budget" tab on the Home screen.
    // It will display the user's current budget and allow them to set a new budget.

    //constructor
    public myBudget() {
        setBackground(Constants.APP_COLOR);
        JLabel label = new JLabel("Budget");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}