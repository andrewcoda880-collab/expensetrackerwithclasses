import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
public class HomeTab extends JPanel {
    
    public HomeTab(){
        
        setBackground(Constants.APP_COLOR);
    
        JPanel homepagePanel = new JPanel();
        JLabel homepageLabel = new JLabel();

        //JLabel title = new JLabel("Home");
        JLabel title = new JLabel("Home");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        this.add(title);
        title.setBounds(125, 25, 250, 250);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.NORTH);


        homepagePanel.add(homepageLabel);
        homepagePanel.setLayout(new BorderLayout());

        //add My Expenses button to the homepage panel
        JButton myExpensesButton = new JButton("My Expenses");
        this.add(myExpensesButton);
        myExpensesButton.setBounds(100,75,300,50);

        //add the Budget button to the homepage panel
        JButton budgetButton = new JButton("Budget");
        this.add(budgetButton);
        budgetButton.setBounds(100,150,300,50);

        //add investments button to the homepage panel
        JButton investmentsButton = new JButton("Investments");
        this.add(investmentsButton);
        investmentsButton.setBounds(100,225,300,50);

        //add the reports button to the homepage panel
        JButton SubscriptionsButton = new JButton("Subscriptions");
        this.add(SubscriptionsButton);
        SubscriptionsButton.setBounds(100,300,300,50);


        //add the settings button to the homepage panel
        JButton settingsButton = new JButton("Settings");
        this.add(settingsButton);
        settingsButton.setBounds(100,375,300,50);




        this.setLayout(null);
        
    }
}
