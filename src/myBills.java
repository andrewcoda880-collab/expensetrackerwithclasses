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

public class myBills extends JPanel {

    // This class represents the summary of the user's income. This can be accessed from the "My Income" button on the homepage.
    // It will display a summary of the user's income, including the total amount earned, the current value of the income,
    // and the profit/loss.
    private final JLabel BillsLabel;
    public  JTextField BillsField;
    private  JLabel totalPriceLabel;
    private JTextField totalPriceField;
    private  JLabel frequencyAmountLabel;
    private JTextField frequencyAmountField;
    private JLabel frequencyStringsLabel;
    private JTextField frequencyField;
    public  JComboBox<String> frequencyMenu;
    private JLabel TotalIncomeLabel;
    private DefaultTableModel incomeTableModel;
    private JTable topIncome;


    //constructor
    public myBills(CardLayout layout, JPanel container) {

        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JLabel label = new JLabel("My Bills");
        label.setFont(new Font("Arial", Font.ITALIC, 20));
        label.setBounds(0, 20, 250, 40);
        add(label);

        //Lets the user input the name of their job/occupation/source of income
        BillsLabel = new JLabel("Name of Bill:");
        BillsLabel.setBounds(0, 70, 200, 40);
        BillsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(BillsLabel);

        BillsField = new JTextField();
        BillsField.setBounds(175, 70, 200, 40);
        BillsField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(BillsField);

        //Lets the user input the total amount of income they earn

        totalPriceLabel = new JLabel("Total Price:");
        totalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalPriceLabel.setBounds(0, 120, 200, 40);
        add(totalPriceLabel);

        totalPriceField = new JTextField();
        totalPriceField.setFont(new Font("Arial", Font.PLAIN, 16));
        totalPriceField.setBounds(175, 120, 200, 40);
        add(totalPriceField);


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

        JButton submitButton = new JButton("Submit Bill");
        submitButton.setBounds(175, 270, 200, 40);
        add(submitButton);

        TotalIncomeLabel = new JLabel("Bill Calculations:");
        TotalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        TotalIncomeLabel.setBounds(0, 300, 200, 40);
        add(TotalIncomeLabel);

        //Income Calculation Table
        String[] columnsForTable = { "Bill", "Total Income", "Frequency"};
        incomeTableModel = new DefaultTableModel(columnsForTable, 0);
        topIncome = new JTable(incomeTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 335, 500, 150);
        add(incomeScrollPane);
        submitButton.addActionListener(e -> addSubscription());

        JButton BillsSummaryButton = new JButton("View Bills Summary");
        BillsSummaryButton.setBounds(0, 490, 200, 40);
        add(BillsSummaryButton);

        BillsSummary summaryPanel = new BillsSummary(layout, container);
        container.add(summaryPanel, "BILLSSUMMARY");

        BillsSummaryButton.addActionListener(e -> {
        summaryPanel.refreshBillsTable();
        BillsSummary.UpdateTotalPrice();
        layout.show(container, "BILLSSUMMARY");
 });
    }

        public void addSubscription() {
            String bill = BillsField.getText().trim();
            String total =  totalPriceField.getText().trim();
            String inputFrequency = null;
            String frequencyAmountString = frequencyAmountField.getText().trim();

            if (bill.isEmpty() || total.isEmpty() ||frequencyAmountString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields");
            return;
            }
            try {
                double frequencyAmountForBill = Double.parseDouble(frequencyAmountString);
                double totalPrice = Double.parseDouble(total);
                if (totalPrice < 0 || frequencyAmountForBill < 0){
                JOptionPane.showMessageDialog(this, "Must be a non-negative number");
                return;
                }

            // Determine the frequency of the income based on the user's selection in the dropdown menu
            if ("Weekly".equals(frequencyMenu.getSelectedItem())) {
                if("1".equals(frequencyAmountField.getText())){
                    inputFrequency = frequencyAmountField.getText() + " Week";
                } else {
                    inputFrequency = frequencyAmountField.getText() + " Weeks";
                }//end weekly if statement
            } else if ("Monthly".equals(frequencyMenu.getSelectedItem())) {
                if("1".equals(frequencyAmountField.getText())){
                    inputFrequency = frequencyAmountField.getText() + " Month";
                } else {
                inputFrequency = frequencyAmountField.getText() + " Months";
                }//end monthly if statement
            }  else if ("Yearly".equals(frequencyMenu.getSelectedItem())) {
                if("1".equals(frequencyAmountField.getText())){
                    inputFrequency = frequencyAmountField.getText() + " Year";
                } else {
                inputFrequency = frequencyAmountField.getText() + " Years";
                }//end yearly if statement
                } else {
                inputFrequency = frequencyAmountField.getText() + " No Frequency Selected";
                }//end if statement for frequency selection
            Bills bills = new Bills(bill, totalPrice, inputFrequency, frequencyAmountForBill);
            BillsSummary.addBills(bills);

            JOptionPane.showMessageDialog(this, "Bill Added");
            incomeTableModel.addRow(new Object[] {
                bill,
                String.format("$%.2f", totalPrice),
                inputFrequency
                });

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Must be a valid number");
                return;
            }
        }

        public void refreshBillTable() {
        incomeTableModel.setRowCount(0);
        List<Bills> bills = BillsSummary.getSortedBills();
        int recentLimit = Math.min(3, bills.size());
        for (int i = 0; i < recentLimit; i++) {
            incomeTableModel.addRow(new Object[] {
                    bills.get(i).getBills(),
                    String.format("%.2f", bills.get(i).getTotalPrice()), // 2 decimal places
                    bills.get(i).getInputFrequency(),
            });
        }

            }

        //Setters
        public void setBills(String bill){
          BillsField.setText(bill);

        }

        public void setTotalPrice(String totalPrice) {
        totalPriceField.setText(totalPrice);
        }

        public void setFrequencyAmount(String amountForBill) {
        frequencyAmountField.setText(amountForBill);
        }

        public void setFrequencyMenu(String BillFrequency) {
        frequencyMenu.setSelectedItem(BillFrequency);
}
     }