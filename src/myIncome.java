import java.awt.CardLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class myIncome extends JPanel {
    private final JLabel sourceOfIncomeLabel;
    private final JTextField sourceOfIncomeField;
    private JLabel totalIncomeLabel;
    private JTextField totalIncomeField;
    private JLabel frequencyAmountLabel;
    private JTextField frequencyAmountField;
    private JLabel frequencyStringsLabel;
    public JComboBox<String> frequencyMenu;
    private JLabel totalIncomeSectionLabel;
    private DefaultTableModel incomeTableModel;
    private JTable topIncome;

    public myIncome(CardLayout layout, JPanel container) {
        setBackground(Constants.APP_COLOR);
        setLayout(null);

        JLabel label = new JLabel("My Income");
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setBounds(0, 20, 250, 40);
        add(label);

        sourceOfIncomeLabel = new JLabel("Source of Income:");
        sourceOfIncomeLabel.setBounds(0, 70, 200, 40);
        sourceOfIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(sourceOfIncomeLabel);

        sourceOfIncomeField = new JTextField();
        sourceOfIncomeField.setBounds(175, 70, 200, 40);
        sourceOfIncomeField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(sourceOfIncomeField);

        totalIncomeLabel = new JLabel("Total Income:");
        totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalIncomeLabel.setBounds(0, 120, 200, 40);
        add(totalIncomeLabel);

        totalIncomeField = new JTextField();
        totalIncomeField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalIncomeField.setBounds(175, 120, 200, 40);
        add(totalIncomeField);

        frequencyAmountLabel = new JLabel("Frequency Amount:");
        frequencyAmountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyAmountLabel.setBounds(0, 170, 200, 40);
        add(frequencyAmountLabel);

        frequencyAmountField = new JTextField();
        frequencyAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyAmountField.setBounds(175, 170, 200, 40);
        add(frequencyAmountField);

        frequencyStringsLabel = new JLabel("Yearly/Monthly/Weekly:");
        frequencyStringsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyStringsLabel.setBounds(0, 220, 200, 40);
        add(frequencyStringsLabel);

        String[] frequencyStrings = {"", "Weekly", "Monthly", "Yearly"};
        frequencyMenu = new JComboBox<>(frequencyStrings);
        frequencyMenu.setBounds(175, 220, 200, 40);
        add(frequencyMenu);

        JButton submitButton = new JButton("Submit Income");
        submitButton.setBounds(175, 270, 200, 40);
        add(submitButton);

        totalIncomeSectionLabel = new JLabel("Income Calculations:");
        totalIncomeSectionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalIncomeSectionLabel.setBounds(0, 300, 200, 40);
        add(totalIncomeSectionLabel);

        String[] columnsForTable = {"Source of Income", "Total Income", "Frequency"};
        incomeTableModel = new DefaultTableModel(columnsForTable, 0);
        topIncome = new JTable(incomeTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 335, 500, 150);
        add(incomeScrollPane);
        submitButton.addActionListener(e -> addIncome());

        JButton incomeSummaryButton = new JButton("View Income Summary");
        incomeSummaryButton.setBounds(0, 490, 200, 40);
        add(incomeSummaryButton);

        container.add(new IncomeSummary(layout, container), "INCOMESUMMARY");
        incomeSummaryButton.addActionListener(e -> layout.show(container, "INCOMESUMMARY"));
        incomeSummaryButton.addActionListener(e -> refreshIncomeTable());
        incomeSummaryButton.addActionListener(e -> IncomeSummary.UpdateTotalIncome());
    }

    public void addIncome() {
        String source = sourceOfIncomeField.getText().trim();
        String totalText = totalIncomeField.getText().trim();
        String frequencyAmountText = frequencyAmountField.getText().trim();
        String frequencySelection = (String) frequencyMenu.getSelectedItem();

        if (source.isEmpty() || totalText.isEmpty() || frequencyAmountText.isEmpty() ||
                frequencySelection == null || frequencySelection.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields");
            return;
        }

        try {
            double totalIncome = Double.parseDouble(totalText);
            double frequencyAmount = Double.parseDouble(frequencyAmountText);

            if (totalIncome < 0) {
                JOptionPane.showMessageDialog(this, "Income must be a non-negative number");
                return;
            }
            if (frequencyAmount <= 0) {
                JOptionPane.showMessageDialog(this, "Frequency amount must be greater than 0");
                return;
            }

            String inputFrequency = formatFrequency(frequencySelection, frequencyAmountText);
            Income income = new Income(source, totalIncome, inputFrequency, frequencyAmount);
            IncomeSummary.addIncome(income);

            JOptionPane.showMessageDialog(this, "Source of Income Added");
            incomeTableModel.addRow(new Object[] {
                source,
                String.format("$%.2f", totalIncome),
                inputFrequency,
            });
            clearInputs();
            IncomeSummary.UpdateTotalIncome();
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Must be a valid number");
        }
    }

    private String formatFrequency(String frequencySelection, String frequencyAmountText) {
        boolean isSingular = "1".equals(frequencyAmountText.trim()) || "1.0".equals(frequencyAmountText.trim());
        if ("Weekly".equals(frequencySelection)) {
            return frequencyAmountText + (isSingular ? " Week" : " Weeks");
        }
        if ("Monthly".equals(frequencySelection)) {
            return frequencyAmountText + (isSingular ? " Month" : " Months");
        }
        if ("Yearly".equals(frequencySelection)) {
            return frequencyAmountText + (isSingular ? " Year" : " Years");
        }
        return frequencyAmountText + " No Frequency Selected";
    }

    private void clearInputs() {
        sourceOfIncomeField.setText("");
        totalIncomeField.setText("");
        frequencyAmountField.setText("");
        frequencyMenu.setSelectedIndex(0);
    }

    public void refreshIncomeTable() {
        incomeTableModel.setRowCount(0);
        List<Income> incomes = IncomeSummary.getSortedIncomes();
        int recentLimit = Math.min(3, incomes.size());
        for (int i = 0; i < recentLimit; i++) {
            incomeTableModel.addRow(new Object[] {
                incomes.get(i).getSource(),
                String.format("%.2f", incomes.get(i).getTotalIncome()),
                incomes.get(i).getInputFrequency(),
            });
        }
    }
}