import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.*;
public class HomeTab extends JPanel {



    public HomeTab(CardLayout layout, JPanel Container) {
        
        setBackground(Constants.APP_COLOR);
        this.setLayout(null);
        addButtons(layout, Container);
        addTitle();
    }
    //method to add the title to the homepage panel
    public void addTitle(){
        JLabel title = new JLabel("Home");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(title);
        title.setBounds(125, 25, 250, 250);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.NORTH);

        
    }
    //method to add the buttons to the homepage panel
    public void addButtons(CardLayout layout, JPanel container){
        //add My Expenses button to the homepage panel
        final JButton myExpensesButton = new JButton("My Expenses");
        myExpensesButton.setBounds(100,75,300,50);

        //add the Budget button to the homepage panel
        final JButton budgetButton = new JButton("My Budget");
        budgetButton.setBounds(100,150,300,50);

        //add investments button to the homepage panel
        final JButton investmentsButton = new JButton("My Investments");
        investmentsButton.setBounds(100,225,300,50);

        //add the reports button to the homepage panel
        final JButton SubscriptionsButton = new JButton("My Subscriptions");
        SubscriptionsButton.setBounds(100,300,300,50);


        //add the settings button to the homepage panel
        final JButton settingsButton = new JButton("My Settings");
        settingsButton.setBounds(100,375,300,50);

        final JButton incomeButton = new JButton("My Income");
        incomeButton.setBounds(100,450,300,50);

        

        add(myExpensesButton);
        add(budgetButton);
        add(investmentsButton);
        add(SubscriptionsButton);
        add(settingsButton);
        add(incomeButton);
        
        myExpensesButton.addActionListener(e -> layout.show(container, "MYEXPENSES"));
        budgetButton.addActionListener(e -> layout.show(container, "MYBUDGET"));
        investmentsButton.addActionListener(e -> layout.show(container, "MYINVESTMENTS"));
        SubscriptionsButton.addActionListener(e -> layout.show(container, "MYSUBSCRIPTIONS"));
        settingsButton.addActionListener(e -> layout.show(container, "MYSETTINGS"));
        incomeButton.addActionListener(e -> layout.show(container, "MYINCOME"));

        container.add(new myExpenses(), "MYEXPENSES");
        container.add(new myBudget(), "MYBUDGET");
        container.add(new myInvestments(), "MYINVESTMENTS");
        container.add(new mySubscriptions(), "MYSUBSCRIPTIONS");
        container.add(new mySettings(), "MYSETTINGS");
        container.add(new myIncome(), "MYINCOME");

    }
}
