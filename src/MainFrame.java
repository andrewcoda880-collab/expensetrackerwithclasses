import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ExpenseManager expenseManager = new ExpenseManager();
    private AllExpensesTab allExpensesTab;

    public MainFrame() {
        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        add(cardPanel, BorderLayout.CENTER);

        NavigationBar navigationBar = new NavigationBar(cardLayout, cardPanel);
        add(navigationBar, BorderLayout.SOUTH);

        // Create instances
        allExpensesTab = new AllExpensesTab(expenseManager);
        ExpensesTab expensesTab = new ExpensesTab(expenseManager, cardLayout, cardPanel, allExpensesTab);
        
        // Create Income panel
        myIncome incomePanel = new myIncome(cardLayout, cardPanel);

        // Add panels
        cardPanel.add(new HomeTab(cardLayout, cardPanel), "HOME");
        cardPanel.add(allExpensesTab, "ALL EXPENSES");
        cardPanel.add(expensesTab, "EXPENSES");
        cardPanel.add(incomePanel, "MYINCOME");
        cardPanel.add(new SettingsTab(), "SETTINGS");
        cardPanel.add(new GraphsTab(expenseManager), "GRAPHS");
        cardPanel.add(new LoginTab(), "LOGIN");

        cardLayout.show(cardPanel, "LOGIN");
    }
}