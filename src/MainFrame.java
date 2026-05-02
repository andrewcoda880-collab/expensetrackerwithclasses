import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ExpenseManager expenseManager = new ExpenseManager();
    private AllExpensesTab allExpensesTab;
    private UserSettings userSettings = new UserSettings("Weekly", "Pie", true);
    private Notifications notifications = new Notifications(userSettings, expenseManager);
    public MainFrame() {

        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        allExpensesTab = new AllExpensesTab(expenseManager);
        
        add(cardPanel, BorderLayout.CENTER);

        // -------------------------
        // ADD NAVIGATION BAR
        // -------------------------
        NavigationBar navigationBar = new NavigationBar(cardLayout, cardPanel);
        add(navigationBar, BorderLayout.SOUTH);

        // -------------------------
        // ADD PANELS 
        // -------------------------
        cardPanel.add(new HomeTab(cardLayout, cardPanel, userSettings), "HOME");
        cardPanel.add(allExpensesTab, "ALL EXPENSES");
        cardPanel.add(new ExpensesTab(expenseManager, cardLayout, cardPanel, allExpensesTab), "EXPENSES");
        cardPanel.add(new SettingsTab(userSettings), "SETTINGS");
        cardPanel.add(new GraphsTab(expenseManager), "GRAPHS");  
        cardPanel.add(new LoginTab(cardLayout, cardPanel), "LOGIN");
        cardPanel.add(new ForgotPasswordTab(cardLayout, cardPanel), "FORGOT"); 
        cardPanel.add(new myIncome(cardLayout, cardPanel), "MYINCOME");
        // SHOW LOGIN FIRST
        cardLayout.show(cardPanel, "LOGIN");
    }
    public void checkNotifications() {
        notifications.checkAndShowNotification();
    }
}