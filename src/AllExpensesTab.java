import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AllExpensesTab extends JPanel {
    private JPanel topPanel;
    private JLabel allExpensesLabel;
    private DefaultTableModel allExpensesTableModel;
    private JTable allExpensesTable;
    //private ExpenseManager expenseManager;
    // I'm just commenting this out since there is an error here!!!

    public AllExpensesTab(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        
        this.setBackground(Constants.APP_COLOR);
        setLayout(new BorderLayout());

        add(createTopPanel());
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Constants.APP_COLOR);

        // title
        allExpensesLabel = new JLabel("All Expenses");
        allExpensesLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel allExpensesLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 20));
        allExpensesLabelPanel.setBackground(Constants.APP_COLOR);
        allExpensesLabelPanel.add(allExpensesLabel);
        topPanel.add(allExpensesLabelPanel);

        // table
        String[] columnsForTable = { "Name", "Amount", "Category" };
        allExpensesTableModel = new DefaultTableModel(columnsForTable, 0);
        allExpensesTable = new JTable(allExpensesTableModel);
        allExpensesTable.setAutoCreateRowSorter(true);
        JScrollPane allExpensesScrollPane = new JScrollPane(allExpensesTable);
        allExpensesTable.setGridColor(Color.BLACK);
        allExpensesScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Constants.APP_COLOR, 8),
            BorderFactory.createLineBorder(Color.BLACK, 1)
        ));
        topPanel.add(allExpensesScrollPane);

        return topPanel;
    }

    
    public void refreshTable() {
        if (allExpensesTableModel != null && expenseManager != null) {
            allExpensesTableModel.setRowCount(0);
            List<Expense> allExpenses = expenseManager.getExpenses();
            for (Expense expense : allExpenses) {
                allExpensesTableModel.addRow(new Object[] {
                        expense.getName(),
                        String.format("$%.2f", expense.getAmount()),
                        expense.getCategory(),
                });
            }
        }
    }
}
