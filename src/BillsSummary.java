
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BillsSummary extends JPanel {

    private final static List<Bills> bill = new ArrayList<>();  // List to hold income entries
    private static JLabel totalBillLabel;
    private static JLabel weeklyAmount;
    private static JLabel monthlyAmount;
    private static JLabel yearlyAmount;
    private  JTextField BillsField;
    public DefaultTableModel billsSummaryTableModel;
    private JTable topIncome;
    

    public BillsSummary(CardLayout layout, JPanel container) {
        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JButton AddBillButton = new JButton("Add Bill");
        AddBillButton.setBounds(350,425 , 150, 100);
        add(AddBillButton);

        AddBillButton.addActionListener(e -> layout.show(container, "MYBILLS"));
        

        JLabel summaryLabel = new JLabel("Bills Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryLabel.setBounds(10, 20, 500, 30);
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        summaryLabel.setVerticalAlignment(JLabel.NORTH);
        add(summaryLabel);

        double totalPrice = getSumOfAllBills();
        totalBillLabel = new JLabel("Total Price: $" + String.format("%.2f", totalPrice));
        totalBillLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        totalBillLabel.setBounds(0, 400, 400, 30);
        add(totalBillLabel);

        weeklyAmount = new JLabel("Estimated Weekly Price of Bills: $0.00");
        weeklyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        weeklyAmount.setBounds(0, 430, 400, 30);
        add(weeklyAmount);

        monthlyAmount = new JLabel("Estimated Monthly Price of Bills: $0.00");
        monthlyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        monthlyAmount.setBounds(0, 460, 425, 30);
        add(monthlyAmount);

        yearlyAmount = new JLabel("Estimated Yearly Price of Bills: $0.00");
        yearlyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        yearlyAmount.setBounds(0, 490, 400, 30);
        add(yearlyAmount);

        //Income Summary Calculation Table
        String[] columnsForSummaryTable = { "Subscription", "Weekly", "Monthly", "Yearly"};
        billsSummaryTableModel = new DefaultTableModel(columnsForSummaryTable, 0);
        topIncome = new JTable(billsSummaryTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 100, 500, 300);
        add(incomeScrollPane);

        UpdateTotalPrice();


    }

        public static void addBills(Bills newBill){ 
        bill.add(newBill);
    }
    
        public static List<Bills> getSortedBills(){ // sorts our incomes from high to low
        List<Bills> sortedBills = new ArrayList<>(bill);
        sortedBills.sort((a,b) -> Double.compare(b.getTotalPrice(), a.getTotalPrice()));
        return sortedBills;
    }

        public List<Bills> getBills() { // returns regular list of incomes ()
        return bill;
    }
        
        public static double getSumOfAllBills() {
            double totalPrice = 0;
            for (Bills i : bill){
                totalPrice += i.getTotalPrice();
            }
            return totalPrice;
       }
            

            public static double calculateWeeklyCostOfBill(){

            double weeklyCost = 0;
            for (Bills i :bill){
            double totalIncome = i.getTotalPrice();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();

            if (frequency.contains("Week")) {
                weeklyCost += totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Month")){
               weeklyCost += totalIncome / (frequencyAmount * 4.345);
            }
            else if (frequency.contains("Year")){
               weeklyCost += totalIncome / (frequencyAmount * 52);
            }
           }
           return weeklyCost;
        }

            public static double calculateMonthlyCostofBill(){
            double monthlyCost = 0;
            for (Bills i : bill){
            double totalIncome = i.getTotalPrice();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();


            if (frequency.contains("Week")) {
                monthlyCost += totalIncome / (frequencyAmount) * 4.345;
            }
            else if (frequency.contains("Month")){
               monthlyCost += totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Year")){
               monthlyCost += totalIncome / (frequencyAmount * 12);
            }
           }
           return monthlyCost;
        }

        public static double calculateYearlyCostOfBill(){
            double yearlyCost = 0;
            for (Bills i :bill){
            double totalCost = i.getTotalPrice();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();


            if (frequency.contains("Week")) {
                yearlyCost += (totalCost / frequencyAmount)* 52;
            }
            else if (frequency.contains("Month")){
               yearlyCost += (totalCost / frequencyAmount) * 12;
            }
            else if (frequency.contains("Year")){
               yearlyCost += totalCost / (frequencyAmount);
            }
           }
           return yearlyCost;
        }

        public static double calculateWeeklyCostForBillsTable(Bills bill){
            //Income income comes from the class income
            
            double totalCost = bill.getTotalPrice();
            String frequency = bill.getInputFrequency();
            double frequencyAmount = bill.getFrequencyAmount();

                if (frequency.contains("Week")) {
                return totalCost / frequencyAmount;
            }
            else if (frequency.contains("Month")){
               return totalCost / (frequencyAmount * 4.345);
            }
            else if (frequency.contains("Year")){
               return totalCost / (frequencyAmount * 52);
            }      
           return 0;
        }

            public static double calculateMonthlyCostForBillsTable(Bills bill){
            //Income income comes from the class income
            
            double totalCost = bill.getTotalPrice();
            String frequency = bill.getInputFrequency();
            double frequencyAmount = bill.getFrequencyAmount();


                if (frequency.contains("Week")) {
                return totalCost / (frequencyAmount) * 4.345;
            }
            else if (frequency.contains("Month")){
               return totalCost / frequencyAmount;
            }
            else if (frequency.contains("Year")){
               return totalCost / (frequencyAmount * 12);
            }
            return 0;
           
        }

            public static double calculateYearlyCostForBillsTable(Bills bill){
            //Income income comes from the class income
            
            double totalCost = bill.getTotalPrice();
            String frequency = bill.getInputFrequency();
            double frequencyAmount = bill.getFrequencyAmount();

        if (frequency.contains("Week")) {
                return (totalCost / frequencyAmount)* 52;
            }
            else if (frequency.contains("Month")){
               return (totalCost / frequencyAmount) * 12;
            }
            else if (frequency.contains("Year")){
               return totalCost / (frequencyAmount);
            }
            
           return 0;
        }
    


       public void refreshBillsTable() {
        billsSummaryTableModel.setRowCount(0);
        List<Bills> sortedBills = BillsSummary.getSortedBills();
        int recentLimit = Math.min(3, bill.size());
        for (int i = 0; i < recentLimit; i++) {
            Bills bills = sortedBills.get(i);
            
            billsSummaryTableModel.addRow(new Object[] {
            bills.getBills(),
            String.format("$%.2f", calculateWeeklyCostForBillsTable(bills)),
            String.format("$%.2f", calculateMonthlyCostForBillsTable(bills)),
            String.format("$%.2f", calculateYearlyCostForBillsTable(bills))
            });

            }
       }


        public static void UpdateTotalPrice(){
        
        double totalPrice = getSumOfAllBills();
        totalBillLabel.setText("Total Price of Bills: $" + String.format("%.2f", totalPrice));

        double weeklyBillsAmount = calculateWeeklyCostOfBill();
        weeklyAmount.setText("Estimated Weekly Price of Bills: $" + String.format("%.2f", weeklyBillsAmount));

        double monthlySubscriptionAmount = calculateMonthlyCostofBill();
        monthlyAmount.setText("Estimated Monthly Price of Bills: $" + String.format("%.2f", monthlySubscriptionAmount));

        double yearlyBillsAmount = calculateYearlyCostOfBill();
        yearlyAmount.setText("Estimated Yearly Price of Bills: $" + String.format("%.2f", yearlyBillsAmount));
      }

    }
