import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ExpenseManager expenseManager;
    private AllExpensesTab allExpensesTab;
    private UserSettings userSettings;
    public MainFrame() {
        this.expenseManager = new ExpenseManager();
        this.userSettings = new UserSettings("Weekly", "Both", true);
        
        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        setLocationRelativeTo(null);
        add(cardPanel, BorderLayout.CENTER);

        NavigationBar navigationBar = new NavigationBar(cardLayout, cardPanel);
        add(navigationBar, BorderLayout.SOUTH);

        allExpensesTab = new AllExpensesTab(expenseManager);
        ExpensesTab expensesTab = new ExpensesTab(expenseManager, cardLayout, cardPanel, allExpensesTab);
        myIncome incomePanel = new myIncome(cardLayout, cardPanel);
        myInvestments investmentsPanel = new myInvestments(expenseManager);

        cardPanel.add(new HomeTab(cardLayout, cardPanel, expenseManager), "HOME");
        cardPanel.add(allExpensesTab, "ALL EXPENSES");
        cardPanel.add(expensesTab, "EXPENSES");
        cardPanel.add(incomePanel, "MYINCOME");
        cardPanel.add(investmentsPanel, "MYINVESTMENTS");
        cardPanel.add(new myBudget(), "MYBUDGET");
        cardPanel.add(new mySubscriptions(), "MYSUBSCRIPTIONS");
        cardPanel.add(new SettingsTab(userSettings), "SETTINGS");
        //cardPanel.add(new GraphsTab(expenseManager, userSettings), "GRAPHS");
        cardPanel.add(new LoginTab(cardLayout, cardPanel), "LOGIN");
        cardPanel.add(new ForgotPasswordTab(cardLayout, cardPanel), "FORGOT");

        cardLayout.show(cardPanel, "LOGIN");
    }
}