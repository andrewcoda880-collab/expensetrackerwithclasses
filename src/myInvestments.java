import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class myInvestments extends JPanel {
    private final ExpenseManager expenseManager;
    private final Investment investmentCalculator;

    private JTextField principalField;
    private JTextField annualRateField;
    private JTextField yearsField;
    private JTextField reserveField;

    private JLabel monthlyIncomeValueLabel;
    private JLabel totalExpensesValueLabel;
    private JLabel availableToInvestValueLabel;
    private JLabel futureValueLabel;
    private JLabel growthEarnedLabel;
    private JLabel noteLabel;

    private DefaultTableModel projectionTableModel;
    private InvestmentGraphPanel graphPanel;
    private double lastAvailableToInvest;

    public myInvestments(ExpenseManager expenseManager) {
        this.expenseManager = expenseManager;
        this.investmentCalculator = new Investment();
        this.lastAvailableToInvest = 0;

        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel titleLabel = new JLabel("My Investments", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Constants.APP_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contentPanel.add(createSummaryPanel());
        contentPanel.add(createInputPanel());
        contentPanel.add(createResultPanel());
        contentPanel.add(createProjectionTablePanel());
        contentPanel.add(createGraphPanel());

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Constants.APP_COLOR);
        add(scrollPane, BorderLayout.CENTER);

        refreshSummary();
    }

    private JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        summaryPanel.setBackground(Color.WHITE);
        summaryPanel.setBorder(BorderFactory.createTitledBorder("Current Summary"));
        summaryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        monthlyIncomeValueLabel = new JLabel();
        totalExpensesValueLabel = new JLabel();
        availableToInvestValueLabel = new JLabel();
        noteLabel = new JLabel("Use reserve amount to keep cash aside before investing.");

        summaryPanel.add(monthlyIncomeValueLabel);
        summaryPanel.add(totalExpensesValueLabel);
        summaryPanel.add(availableToInvestValueLabel);
        summaryPanel.add(noteLabel);
        return summaryPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Investment Calculator"));
        inputPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        principalField = new JTextField();
        annualRateField = new JTextField();
        yearsField = new JTextField();
        reserveField = new JTextField("0");

        inputPanel.add(new JLabel("Starting Amount:"));
        inputPanel.add(principalField);
        inputPanel.add(new JLabel("Annual Growth Rate (%):"));
        inputPanel.add(annualRateField);
        inputPanel.add(new JLabel("Years:"));
        inputPanel.add(yearsField);
        inputPanel.add(new JLabel("Budget Reserve:"));
        inputPanel.add(reserveField);

        JButton calculateAvailableButton = new JButton("Calculate Available Amount");
        calculateAvailableButton.addActionListener(e -> calculateAvailableAmount());
        JButton projectButton = new JButton("Project Growth");
        projectButton.addActionListener(e -> projectGrowth());

        inputPanel.add(calculateAvailableButton);
        inputPanel.add(projectButton);
        return inputPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createTitledBorder("Projection Results"));
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        futureValueLabel = new JLabel("Projected Value: $0.00");
        growthEarnedLabel = new JLabel("Growth Earned: $0.00");

        JButton useAvailableButton = new JButton("Use Available Amount");
        useAvailableButton.addActionListener(e -> principalField.setText(String.format("%.2f", Math.max(0, lastAvailableToInvest))));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearInvestmentInputs());

        resultPanel.add(futureValueLabel);
        resultPanel.add(growthEarnedLabel);
        resultPanel.add(useAvailableButton);
        resultPanel.add(clearButton);
        return resultPanel;
    }

    private JPanel createProjectionTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Constants.APP_COLOR);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Year-by-Year Projection"));
        tablePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));

        projectionTableModel = new DefaultTableModel(new Object[] {"Year", "Projected Value"}, 0);
        JTable projectionTable = new JTable(projectionTableModel);
        JScrollPane tableScrollPane = new JScrollPane(projectionTable);
        tableScrollPane.setPreferredSize(new Dimension(450, 120));
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        return tablePanel;
    }

    private JPanel createGraphPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Constants.APP_COLOR);
        panel.setBorder(BorderFactory.createTitledBorder("Investment Growth Graph"));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        graphPanel = new InvestmentGraphPanel();
        graphPanel.setPreferredSize(new Dimension(450, 220));
        panel.add(graphPanel, BorderLayout.CENTER);
        return panel;
    }

    private void calculateAvailableAmount() {
        try {
            double reserveAmount = parseDoubleField(reserveField, "Budget reserve");
            double monthlyIncome = IncomeSummary.calculateMonthlyIncome();
            double totalExpenses = expenseManager.getSumOfAllExpenses();

            lastAvailableToInvest = investmentCalculator.calculateAvailableToInvest(
                    monthlyIncome,
                    totalExpenses,
                    reserveAmount);

            refreshSummary();
            if (lastAvailableToInvest < 0) {
                noteLabel.setText("Tracked expenses and reserve are above monthly income.");
            } else {
                noteLabel.setText("Available amount updated. You can use it as your starting amount.");
            }
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    private void projectGrowth() {
        try {
            double principal = parseDoubleField(principalField, "Starting amount");
            double annualRatePercent = parseDoubleField(annualRateField, "Annual growth rate");
            int years = parseIntegerField(yearsField, "Years");
            double annualRate = annualRatePercent / 100.0;

            double futureValue = investmentCalculator.calculateProjectedGrowth(principal, annualRate, years);
            double growthEarned = investmentCalculator.calculateGrowthEarned(principal, annualRate, years);
            List<Double> projection = investmentCalculator.createYearlyProjection(principal, annualRate, years);

            futureValueLabel.setText(String.format("Projected Value: $%.2f", futureValue));
            growthEarnedLabel.setText(String.format("Growth Earned: $%.2f", growthEarned));
            fillProjectionTable(projection);
            graphPanel.setProjectionValues(projection);
            noteLabel.setText("Projection uses yearly compounding and no extra contributions.");
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    private void fillProjectionTable(List<Double> projection) {
        projectionTableModel.setRowCount(0);
        for (int year = 0; year < projection.size(); year++) {
            projectionTableModel.addRow(new Object[] {year, String.format("$%.2f", projection.get(year))});
        }
    }

    private void refreshSummary() {
        double monthlyIncome = IncomeSummary.calculateMonthlyIncome();
        double totalExpenses = expenseManager.getSumOfAllExpenses();
        monthlyIncomeValueLabel.setText(String.format("Estimated Monthly Income: $%.2f", monthlyIncome));
        totalExpensesValueLabel.setText(String.format("Tracked Expenses: $%.2f", totalExpenses));
        availableToInvestValueLabel.setText(String.format("Available To Invest: $%.2f", lastAvailableToInvest));
    }

    private double parseDoubleField(JTextField field, String fieldName) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        try {
            double value = Double.parseDouble(text);
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " cannot be negative");
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a valid number");
        }
    }

    private int parseIntegerField(JTextField field, String fieldName) {
        String text = field.getText().trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        try {
            int value = Integer.parseInt(text);
            if (value < 0) {
                throw new IllegalArgumentException(fieldName + " cannot be negative");
            }
            return value;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(fieldName + " must be a whole number");
        }
    }

    private void clearInvestmentInputs() {
        principalField.setText("");
        annualRateField.setText("");
        yearsField.setText("");
        reserveField.setText("0");
        futureValueLabel.setText("Projected Value: $0.00");
        growthEarnedLabel.setText("Growth Earned: $0.00");
        projectionTableModel.setRowCount(0);
        graphPanel.setProjectionValues(java.util.Collections.emptyList());
        noteLabel.setText("Use reserve amount to keep cash aside before investing.");
    }
}