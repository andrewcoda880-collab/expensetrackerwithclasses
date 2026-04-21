import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class myIncome extends JPanel {

    // This class represents the summary of the user's income. This can be accessed from the "My Income" button on the homepage.
    // It will display a summary of the user's income, including the total amount earned, the current value of the income,
    // and the profit/loss.
    private final JLabel sourceOfIncomeLabel;
    private final JTextField sourceOfIncomeField;
    private  JLabel totalIncomeLabel;
    private JTextField totalIncomeField;
    private  JLabel frequencyAmountLabel;
    private JTextField frequencyAmountField;
    private JLabel frequencyStringsLabel;
    private JTextField frequencyField;
    private JComboBox<String> frequencyMenu;
    private JLabel TotalIncomeLabel;
    private DefaultTableModel incomeTableModel;
    private JTable topIncome;

    //constructor
    public myIncome() {

        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JLabel label = new JLabel("My Income");
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setBounds(0, 20, 250, 40);
        add(label);

        //Lets the user input the name of their job/occupation/source of income
        sourceOfIncomeLabel = new JLabel("Source of Income:");
        sourceOfIncomeLabel.setBounds(0, 70, 200, 40);
        sourceOfIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(sourceOfIncomeLabel);

        sourceOfIncomeField = new JTextField();
        sourceOfIncomeField.setBounds(175, 70, 200, 40);
        sourceOfIncomeField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(sourceOfIncomeField);

        //Lets the user input the total amount of income they earn

        totalIncomeLabel = new JLabel("Total Income:");
        totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalIncomeLabel.setBounds(0, 120, 200, 40);
        add(totalIncomeLabel);

        totalIncomeField = new JTextField();
        totalIncomeField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalIncomeField.setBounds(175, 120, 200, 40);
        add(totalIncomeField);


        // Lets the user input the frequency of their income
        frequencyAmountLabel = new JLabel("Frequency Amount:");
        frequencyAmountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyAmountLabel.setBounds(0, 170, 200, 40);
        add(frequencyAmountLabel);

        frequencyAmountField = new JTextField();
        frequencyAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyAmountField.setBounds(175, 170, 200, 40);
        add(frequencyAmountField);


        frequencyStringsLabel = new JLabel("Yearly/Monthly/Weekly: ");
        frequencyStringsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frequencyStringsLabel.setBounds(0, 220, 200, 40);
        add(frequencyStringsLabel);

        // Lets the user select the frequency of their income from a dropdown menu
        String[] frequencyStrings = { "", "Weekly", "Monthly", "Yearly" };
        frequencyMenu = new JComboBox<>(frequencyStrings);
        frequencyMenu.setBounds(175, 220, 200, 40);
        this.add(frequencyMenu);

        JButton submitButton = new JButton("Submit Income");
        submitButton.setBounds(175, 270, 200, 40);
        add(submitButton);

        TotalIncomeLabel = new JLabel("Income Calculations:");
        TotalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        TotalIncomeLabel.setBounds(0, 300, 200, 40);
        add(TotalIncomeLabel);

        //Income Calculation Table
        String[] columnsForTable = { "Source of Income", "Total Income", "Frequency"};
        incomeTableModel = new DefaultTableModel(columnsForTable, 0);
        topIncome = new JTable(incomeTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 335, 500, 150);
        add(incomeScrollPane);

        JButton IncomeSummaryButton = new JButton("View Income Summary");
        IncomeSummaryButton.setBounds(0, 490, 200, 40);
        add(IncomeSummaryButton);

        submitButton.addActionListener(e -> addIncome());
    }

        private void addIncome() {
            String source = sourceOfIncomeField.getText();
            String total = totalIncomeField.getText();
            String inputFrequency = null;

            // Determine the frequency of the income based on the user's selection in the dropdown menu
            if ("Weekly".equals(frequencyMenu.getSelectedItem())) {
                inputFrequency = frequencyAmountField.getText() + " Weeks";
            } else if ("Monthly".equals(frequencyMenu.getSelectedItem())){
                inputFrequency = frequencyAmountField.getText() + " Months";
            }
            else if ("Yearly".equals(frequencyMenu.getSelectedItem())) {
                inputFrequency = frequencyAmountField.getText() + " Years";
                } else {
                inputFrequency = frequencyAmountField.getText() + " No Frequency Selected";
                }

            if (!source.isEmpty() && !total.isEmpty() && inputFrequency != null && !inputFrequency.isEmpty()) {
                incomeTableModel.addRow(new Object[]{source, total, inputFrequency});
                sourceOfIncomeField.setText("");
                totalIncomeField.setText("");
                frequencyMenu.setSelectedIndex(0);
            }
        }

        public void viewIncomeSummary() {
            // This method will calculate the total income based on the entries in the income table and display it to the user.
            // It will also provide a breakdown of the income by source and frequency.
        }


}
