import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private ExpenseManager expenseManager = new ExpenseManager();

    public MainFrame() {

        setTitle(Constants.APP_TITLE);
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        add(cardPanel, BorderLayout.CENTER);

        // -------------------------
        // ADD NAVIGATION BAR
        // -------------------------
        NavigationBar navigationBar = new NavigationBar(cardLayout, cardPanel);
        add(navigationBar, BorderLayout.SOUTH);

        // -------------------------
        // ADD PANELS 
        // -------------------------
        cardPanel.add(new HomeTab(), "HOME");
        cardPanel.add(new ExpensesTab(expenseManager), "EXPENSES");
        cardPanel.add(new SettingsTab(), "SETTINGS");
        cardPanel.add(new GraphsTab(), "GRAPHS");
        cardPanel.add(new LoginTab(), "LOGIN");

        //SHOW LOGIN FIRST
        cardLayout.show(cardPanel, "LOGIN");

        
        
    }
}