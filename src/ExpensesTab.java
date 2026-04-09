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

        JLabel nameLabel = new JLabel("Expense Name:");
        nameField = new JTextField();

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JLabel categoryLabel = new JLabel("Category");
        String[] categories = { "", "Food", "Transport", "Entertainment", "Bills", "Other" };
        categoryMenu = new JComboBox<>(categories);

        JButton submitButton = new JButton("Submit Expense");

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(amountLabel);
        formPanel.add(amountField);

        formPanel.add(categoryLabel);
        formPanel.add(categoryMenu);

        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        add(formPanel, BorderLayout.NORTH);

        String[] columnsForTable = { "Name", "Amount", "Category" };
        tableModel = new DefaultTableModel(columnsForTable, 3);
        topExpenses = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(topExpenses);
        add(scrollPane, BorderLayout.CENTER);

        submitButton.addActionListener(e -> addExpense());
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
