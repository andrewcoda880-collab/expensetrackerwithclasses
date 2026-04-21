import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myExpenses extends JPanel {


    // This class will represent the summary of the user's expenses and it will be accessed by pressing the "My Expenses" button on the Home tab.
    // It will display a summary of the user's expenses, including total expenses, categories of expenses, and recent transactions.
    //  It will also provide options to add new expenses and view detailed reports.

    //constructor
    public myExpenses() {
        setBackground(Constants.APP_COLOR);
        JLabel label = new JLabel("My Expenses");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);

    }

    
}