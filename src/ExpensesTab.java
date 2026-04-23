import java.awt.*;
import java.util.List;
import javax.swing.*;
//import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ExpensesTab extends JPanel {

    private JTextField nameField;
    private JTextField amountField;
    private JComboBox<String> categoryMenu;
    private JTable topExpenses;
    private DefaultTableModel topExpenseTableModel;
    private JTable recentExpenses;
    private DefaultTableModel recentExpenseTableModel;
    private ExpenseManager expenseManager;
    private CardLayout layout;
    private JPanel container;
    private JLabel sumOfAllExpenses;
    private Budget budget;
    private JLabel budgetInformation;
    private AllExpensesTab allExpensesTab;

    public ExpensesTab(ExpenseManager expenseManager, CardLayout layout, JPanel container,
            AllExpensesTab allExpensesTab) {

        this.allExpensesTab = allExpensesTab;
        this.expenseManager = expenseManager;
        this.layout = layout;
        this.container = container;

        this.setBackground(Constants.APP_COLOR);
        setLayout(new BorderLayout());

        add(createInputsPanel(), BorderLayout.NORTH);

        add(createTablesPanel(), BorderLayout.CENTER);

        add(createBottomPanel(), BorderLayout.SOUTH);

    }

    private JPanel createBottomPanel() {
        // Create panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Constants.APP_COLOR);

        // view all expenses button
        JButton viewAllExpensesButton = new JButton("View All Expenses");
        viewAllExpensesButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewAllExpensesButton.addActionListener(e -> {
            layout.show(container, "ALL EXPENSES");
            allExpensesTab.refreshTable();
        });
        bottomPanel.add(viewAllExpensesButton);

        // sum of all expenses

        sumOfAllExpenses = new JLabel("Total Spent: $" + String.format("%.2f", expenseManager.getSumOfAllExpenses()));
        sumOfAllExpenses.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sumOfAllExpenses.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(sumOfAllExpenses);

        // remaining budget

        budget = new Budget(45);

        budgetInformation = new JLabel("You have: $"
                + String.format("%.2f", (budget.getBudget() - expenseManager.getSumOfAllExpenses())) + " remaining");
        budgetInformation.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        budgetInformation.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(budgetInformation);

        return bottomPanel;
    }

    private JPanel createTablesPanel() {
        JPanel tablesPanel = new JPanel();
        tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
        tablesPanel.setBackground(Constants.APP_COLOR);

        JPanel topExpensesPanel = new JPanel();
        topExpensesPanel.setLayout(new BoxLayout(topExpensesPanel, BoxLayout.Y_AXIS));
        topExpensesPanel.setBackground(Constants.APP_COLOR);

        JPanel recentExpensesPanel = new JPanel();
        recentExpensesPanel.setLayout(new BoxLayout(recentExpensesPanel, BoxLayout.Y_AXIS));
        recentExpensesPanel.setBackground(Constants.APP_COLOR);

        // -- Top Expenses Label ---

        JLabel topExpensesLabel = new JLabel("Top Expenses:");
        topExpensesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        topExpensesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        topExpensesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // this pushes the label to the left so it matches with the table
        JPanel topExpensesLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        topExpensesLabelPanel.setBackground(Constants.APP_COLOR);
        topExpensesLabelPanel.add(topExpensesLabel);
        topExpensesPanel.add(topExpensesLabelPanel);


        // --- Top Expenses Table ---

        String[] columnsForTable = { "Name", "Amount", "Category" };
        topExpenseTableModel = new DefaultTableModel(columnsForTable, 0);
        topExpenses = new JTable(topExpenseTableModel);
        JScrollPane topExpensesScrollPane = new JScrollPane(topExpenses);
        topExpensesScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constants.APP_COLOR, 10),
                BorderFactory.createLineBorder(Color.BLACK)));
        topExpenses.setGridColor(Color.BLACK);
        //topExpenses.setBackground(Constants.APP_COLOR);

        topExpensesPanel.add(topExpensesScrollPane);
        tablesPanel.add(topExpensesPanel);

        // --- Recent Expenses Label -----

        JLabel recentExpensesLabel = new JLabel("Recent Expenses:");
        recentExpensesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        recentExpensesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recentExpensesLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // pushes to left, same as above with top expenses label
        JPanel recentExpensesLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        recentExpensesLabelPanel.setBackground(Constants.APP_COLOR);
        recentExpensesLabelPanel.add(recentExpensesLabel);
        recentExpensesPanel.add(recentExpensesLabelPanel);

        //recentExpensesPanel.add(recentExpensesLabel);

        // --- Recent Expenses Table -----
        recentExpenseTableModel = new DefaultTableModel(columnsForTable, 0);
        recentExpenses = new JTable(recentExpenseTableModel);
        JScrollPane recentExpensesScrollPane = new JScrollPane(recentExpenses);
        recentExpensesScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Constants.APP_COLOR, 10),
                BorderFactory.createLineBorder(Color.BLACK)));
        //recentExpenses.setBackground(Constants.APP_COLOR);
        recentExpenses.setGridColor(Color.BLACK);
        recentExpensesPanel.add(recentExpensesScrollPane);

        tablesPanel.add(recentExpensesPanel);

        return tablesPanel;
    }

    private JPanel createInputsPanel() {

        JPanel inputsPanel = new JPanel(new GridLayout(4, 2, 10, 2));
        inputsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        inputsPanel.setBackground(Constants.APP_COLOR);

        // ----- Name --------
        JLabel nameLabel = new JLabel("Expense Name:");
        nameField = new JTextField();

        inputsPanel.add(nameLabel);
        inputsPanel.add(nameField);

        // ----- Amount ------
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        inputsPanel.add(amountLabel);
        inputsPanel.add(amountField);

        // ----- Category ----

        JLabel categoryLabel = new JLabel("Category");
        String[] categories = { "", "Food", "Transport", "Entertainment", "Bills", "Other" };
        categoryMenu = new JComboBox<>(categories);

        inputsPanel.add(categoryLabel);
        inputsPanel.add(categoryMenu);

        // ---- Submit --------

        JButton submitButton = new JButton("Submit Expense");

        inputsPanel.add(new JLabel());
        inputsPanel.add(submitButton);

        submitButton.addActionListener(e -> addExpense());

        return inputsPanel;
    }

    private void addExpense() {
        String name = nameField.getText().trim();
        String amountText = amountField.getText().trim();
        String category = (String) categoryMenu.getSelectedItem();

        if (name.isEmpty() || amountText.isEmpty() || category.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            if (amount < 0) {
                JOptionPane.showMessageDialog(this, "Must be a non-negative number");
                return;
            }
            Expense expense = new Expense(name, amount, category);
            expenseManager.addExpense(expense);

            JOptionPane.showMessageDialog(this, "Expense Added");

            clearInputs();
            refreshExpensesTabData();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Must be a valid number");
        }
    }

    private void clearInputs() {
        nameField.setText("");
        amountField.setText("");
        categoryMenu.setSelectedIndex(0);
    }

    private void refreshExpensesTabData() {

        refreshRecentExpensesTable();
        refreshTopExpensesTable();
        refreshSumOfAllExpenses();
        refreshBudget();
        allExpensesTab.refreshTable();
    }

    private void refreshSumOfAllExpenses() {
        sumOfAllExpenses.setText("Total Spent: $" + String.format("%.2f", expenseManager.getSumOfAllExpenses()));
    }

    private void refreshTopExpensesTable() {
        topExpenseTableModel.setRowCount(0);
        List<Expense> sortedExpenses = expenseManager.getSortedExpenses();
        int sortedLimit = Math.min(4, sortedExpenses.size()); // limits our table size to 4 (or less)
        for (int i = 0; i < sortedLimit; i++) {
            topExpenseTableModel.addRow(new Object[] {
                    sortedExpenses.get(i).getName(),
                    String.format("%.2f", sortedExpenses.get(i).getAmount()), // 2 decimal places
                    sortedExpenses.get(i).getCategory(),
            });
        }
    }

    private void refreshBudget() {
        budgetInformation.setText("You have $"
                + String.format("%.2f", (budget.getBudget() - expenseManager.getSumOfAllExpenses())) + " remaining");
    }

    private void refreshRecentExpensesTable() {
        recentExpenseTableModel.setRowCount(0);
        List<Expense> expenses = expenseManager.getExpenses();
        int size = expenses.size();
        int recentLimit = Math.min(4, size);
        for (int i = size - 1; i >= size - recentLimit; i--) {
            recentExpenseTableModel.addRow(new Object[] {
                    expenses.get(i).getName(),
                    String.format("%.2f", expenses.get(i).getAmount()),
                    expenses.get(i).getCategory(),
            });
        }
    }
}
