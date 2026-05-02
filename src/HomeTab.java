import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeTab extends JPanel {
    private ExpenseManager expenseManager;
    public HomeTab(CardLayout layout, JPanel container, ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        setBackground(Constants.APP_COLOR);
        setLayout(null);
        addButtons(layout, container);
        addTitle();
    }
    
    public void addTitle() {
        JLabel title = new JLabel("Home");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title);
        title.setBounds(125, 25, 250, 250);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.NORTH);
    }

    public void addButtons(CardLayout layout, JPanel container) {
        JButton myExpensesButton = new JButton("My Expenses");
        myExpensesButton.setBounds(100, 75, 300, 50);

        JButton budgetButton = new JButton("My Budget");
        budgetButton.setBounds(100, 150, 300, 50);

        JButton investmentsButton = new JButton("My Investments");
        investmentsButton.setBounds(100, 225, 300, 50);

        JButton subscriptionsButton = new JButton("My Subscriptions");
        subscriptionsButton.setBounds(100, 300, 300, 50);

        JButton settingsButton = new JButton("My Settings");
        settingsButton.setBounds(100, 375, 300, 50);

        JButton incomeButton = new JButton("My Income");
        incomeButton.setBounds(100, 450, 300, 50);

        add(myExpensesButton);
        add(budgetButton);
        add(investmentsButton);
        add(subscriptionsButton);
        add(settingsButton);
        add(incomeButton);

        myExpensesButton.addActionListener(e -> layout.show(container, "EXPENSES"));
        budgetButton.addActionListener(e -> layout.show(container, "MYBUDGET"));
        investmentsButton.addActionListener(e -> layout.show(container, "MYINVESTMENTS"));
        subscriptionsButton.addActionListener(e -> layout.show(container, "MYSUBSCRIPTIONS"));
        settingsButton.addActionListener(e -> layout.show(container, "SETTINGS"));
        incomeButton.addActionListener(e -> layout.show(container, "MYINCOME"));
    }
}