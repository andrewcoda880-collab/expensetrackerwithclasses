import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;
public class HomeTab extends JPanel {
    
    public HomeTab(CardLayout Homelayout, JPanel HomeContainer) {
        
        setBackground(Constants.APP_COLOR);
    
        JPanel homepagePanel = new JPanel();
        JLabel homepageLabel = new JLabel();

        JLabel title = new JLabel("Home");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        this.add(title);
        title.setBounds(125, 25, 250, 250);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.NORTH);

<<<<<<< HEAD
=======
        
        JPanel homepagePanel = new JPanel();
        JLabel homepageLabel = new JLabel();
>>>>>>> 55c765e16b583f1357d844f6a9500037339b2d21

        homepagePanel.add(homepageLabel);
        homepagePanel.setLayout(new BorderLayout());

        //add My Expenses button to the homepage panel
        JButton myExpensesButton = new JButton("My Expenses");
        myExpensesButton.setBounds(100,75,300,50);

        //add the Budget button to the homepage panel
        JButton budgetButton = new JButton("Budget");
        budgetButton.setBounds(100,150,300,50);

        //add investments button to the homepage panel
        JButton investmentsButton = new JButton("Investments");
        investmentsButton.setBounds(100,225,300,50);

        //add the reports button to the homepage panel
        JButton SubscriptionsButton = new JButton("Subscriptions");
        SubscriptionsButton.setBounds(100,300,300,50);


        //add the settings button to the homepage panel
        JButton settingsButton = new JButton("Settings");
        settingsButton.setBounds(100,375,300,50);


        this.setLayout(null);
        
        myExpensesButton.addActionListener(e -> Homelayout.show(HomeContainer, "MY EXPENSES"));
        budgetButton.addActionListener(e -> Homelayout.show(HomeContainer, "MY BUDGET"));
        investmentsButton.addActionListener(e -> {
            // Code to navigate to the Investments tab
        });
        SubscriptionsButton.addActionListener(e -> {
            // Code to navigate to the Subscriptions tab
        });
        settingsButton.addActionListener(e -> {
            // Code to navigate to the Settings tab
        });

        add(myExpensesButton);
        add(budgetButton);
        add(investmentsButton);
        add(SubscriptionsButton);
        add(settingsButton);
    }
}
