import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myIncome extends JPanel {

    // This class represents the summary of the user's income. This can be accessed from the "My Income" button on the homepage.
    // It will display a summary of the user's income, including the total amount earned, the current value of the income,
    // and the profit/loss.
    private JLabel sourceOfIncomeLabel;
    private JLabel totalIncomeLabel;
    private JLabel frequencyLabel;

    //constructor
    public myIncome() {
        setBackground(Constants.APP_COLOR);
        JLabel label = new JLabel("My Income");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        
        add(label);

        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        // Lets the user input the name of their job/occupation/source of income
        sourceOfIncomeLabel = new JLabel("Source of Income:");
        sourceOfIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sourceOfIncomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(sourceOfIncomeLabel);
        
        

        // Lets the user input the total amount of income they earn
        //totalIncomeLabel = new JLabel("Total Income:");
        //totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //totalIncomeLabel.setBounds(50, 80, 200, 30);
        //add(totalIncomeLabel);

        // Lets the user input the frequency of their income
        //frequencyLabel = new JLabel("Frequency:");
        //frequencyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        //frequencyLabel.setBounds(50, 110, 200, 30);
        //add(frequencyLabel);

    }

}
