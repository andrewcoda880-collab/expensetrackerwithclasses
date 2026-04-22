import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AllExpensesTab extends JPanel {
    private JPanel topPanel;
    private JLabel allExpensesTabLabel;
    private DefaultTableModel allExpensesTableModel;
    private JTable allExpensesTable;
    private ExpenseManager expenseManager;

    public AllExpensesTab(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        
        this.setBackground(Constants.APP_COLOR);
        setLayout(new BorderLayout());

        add(createTopPanel());
    }

    private JPanel createTopPanel(){
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Constants.APP_COLOR);

        // title

        allExpensesTabLabel = new JLabel("All Expenses");
        allExpensesTabLabel.setFont(new Font("Arial", Font.BOLD, 15));
        topPanel.add(allExpensesTabLabel);

        // table

        String[] columnsForTable = { "Name", "Amount", "Category" };
        allExpensesTableModel = new DefaultTableModel(columnsForTable, 0);
        allExpensesTable = new JTable(allExpensesTableModel);
        JScrollPane allExpensesScrollPane = new JScrollPane(allExpensesTable);
        allExpensesTable.setGridColor(Color.BLACK);
        allExpensesTable.setBackground(Constants.APP_COLOR);
        topPanel.add(allExpensesScrollPane);

        

        return topPanel;
    }

    public void refreshTable() {
        allExpensesTableModel.setRowCount(0);
        List<Expense> allExpenses = expenseManager.getExpenses();
        for (Expense expense : allExpenses) {
            allExpensesTableModel.addRow(new Object[] {
                    expense.getName(),
                    String.format("%.2f", expense.getAmount()), // 2 decimal places
                    expense.getCategory(),
            });
    }
}
}