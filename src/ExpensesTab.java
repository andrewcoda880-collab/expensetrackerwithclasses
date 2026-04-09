import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpensesTab extends JPanel {

    private JTextField nameField;
    private JTextField amountField;
    private JComboBox<String> categoryMenu;
    private JTable topExpenses;
    private DefaultTableModel tableModel;
    private ExpenseManager expenseManager;

    public ExpensesTab(ExpenseManager expenseManager) {

        this.expenseManager = expenseManager;
        this.setBackground(Constants.APP_COLOR);

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBackground(Constants.APP_COLOR);

        // ----- Name --------
        JLabel nameLabel = new JLabel("Expense Name:");
        nameField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        // ----- Amount ------
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        formPanel.add(amountLabel);
        formPanel.add(amountField);

        // ----- Category ----

        JLabel categoryLabel = new JLabel("Category");
        String[] categories = { "", "Food", "Transport", "Entertainment", "Bills", "Other" };
        categoryMenu = new JComboBox<>(categories);

        formPanel.add(categoryLabel);
        formPanel.add(categoryMenu);

        // ---- Submit --------

        JButton submitButton = new JButton("Submit Expense");

        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        submitButton.addActionListener(e -> addExpense());

        // --- Top Expenses Table ---

        String[] columnsForTable = { "Name", "Amount", "Category" };
        tableModel = new DefaultTableModel(columnsForTable, 3); // 3 is number of rows
        topExpenses = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(topExpenses);
        topExpenses.setGridColor(Color.BLACK);
        add(scrollPane, BorderLayout.CENTER);


        add(formPanel, BorderLayout.NORTH);
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
            Expense expense = new Expense(name, amount, category);
            expenseManager.addExpense(expense);

            JOptionPane.showMessageDialog(this, "Expense Added");

            nameField.setText("");
            amountField.setText("");
            categoryMenu.setSelectedIndex(0);

            refreshTables();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Must be a valid number");
        }
    }

    private void refreshTables() {
        tableModel.setRowCount(0);

        List<Expense> sortedExpenses = expenseManager.getSortedExpenses();

        for (Expense expense : sortedExpenses) {
            tableModel.addRow(new Object[] {
                    expense.getName(),
                    expense.getAmount(),
                    expense.getCategory(),
            });
        }
    }
}
