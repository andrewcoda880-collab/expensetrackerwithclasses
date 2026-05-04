import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SubscriptionSummary extends JPanel {

    private final static List<Subscription> subscriptions = new ArrayList<>();  // List to hold income entries
    private static JLabel totalSubscriptionLabel;
    private static JLabel weeklyAmount;
    private static JLabel monthlyAmount;
    private static JLabel yearlyAmount;
    private  JTextField SubscriptionField;
    public DefaultTableModel subscriptionSummaryTableModel;
    private JTable topIncome;
    

    public SubscriptionSummary(CardLayout layout, JPanel container) {
        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JButton AddSubscriptionButton = new JButton("Add Subscription");
        AddSubscriptionButton.setBounds(350,425 , 150, 100);
        add(AddSubscriptionButton);

        AddSubscriptionButton.addActionListener(e -> layout.show(container, "MYSUBSCRIPTIONS"));
        

        JLabel summaryLabel = new JLabel("Subscription Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryLabel.setBounds(10, 20, 500, 30);
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        summaryLabel.setVerticalAlignment(JLabel.NORTH);
        add(summaryLabel);

        double totalPrice = getSumOfAllIncome();
        totalSubscriptionLabel = new JLabel("Total Price: $" + String.format("%.2f", totalPrice));
        totalSubscriptionLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        totalSubscriptionLabel.setBounds(0, 400, 400, 30);
        add(totalSubscriptionLabel);

        weeklyAmount = new JLabel("Estimated Weekly Price of Subscription: $0.00");
        weeklyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        weeklyAmount.setBounds(0, 430, 400, 30);
        add(weeklyAmount);

        monthlyAmount = new JLabel("Estimated Monthly Price of Subscription: $0.00");
        monthlyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        monthlyAmount.setBounds(0, 460, 425, 30);
        add(monthlyAmount);

        yearlyAmount = new JLabel("Estimated Yearly Price of Subscription: $0.00");
        yearlyAmount.setFont(new Font("Arial", Font.BOLD, 15));
        yearlyAmount.setBounds(0, 490, 400, 30);
        add(yearlyAmount);

        //Income Summary Calculation Table
        String[] columnsForSummaryTable = { "Subscription", "Weekly", "Monthly", "Yearly"};
        subscriptionSummaryTableModel = new DefaultTableModel(columnsForSummaryTable, 0);
        topIncome = new JTable(subscriptionSummaryTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 100, 500, 300);
        add(incomeScrollPane);

        UpdateTotalPrice();

    }

        public static void addSubscription(Subscription subscription){ 
        subscriptions.add(subscription);
    }
    
        public static List<Subscription> getSortedSubscriptions(){ // sorts our incomes from high to low
        List<Subscription> sortedSubscriptions = new ArrayList<>(subscriptions);
        sortedSubscriptions.sort((a,b) -> Double.compare(b.getTotalPrice(), a.getTotalPrice()));
        return sortedSubscriptions;
    }

        public List<Subscription> getSubscription() { // returns regular list of incomes ()
        return subscriptions;
    }
        
        public static double getSumOfAllIncome() {
            double totalPrice = 0;
            for (Subscription i : subscriptions){
                totalPrice += i.getTotalPrice();
            }
            return totalPrice;
       }
            

            public static double calculateWeeklyCostOfSubscription(){

            double weeklyCost = 0;
            for (Subscription i :subscriptions){
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

            public static double calculateMonthlyCostofSubscription(){
            double monthlyCost = 0;
            for (Subscription i : subscriptions){
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

        public static double calculateYearlyCostOfSubscription(){
            double yearlyCost = 0;
            for (Subscription i :subscriptions){
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

        public static double calculateWeeklyCostForSubscriptionsTable(Subscription sub){
            //Income income comes from the class income
            
            double totalCost = sub.getTotalPrice();
            String frequency = sub.getInputFrequency();
            double frequencyAmount = sub.getFrequencyAmount();

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

            public static double calculateMonthlyCostForSubscriptionsTable(Subscription sub){
            //Income income comes from the class income
            
            double totalCost = sub.getTotalPrice();
            String frequency = sub.getInputFrequency();
            double frequencyAmount = sub.getFrequencyAmount();


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

            public static double calculateYearlyCostForSubscriptionsTable(Subscription sub){
            //Income income comes from the class income
            
            double totalCost = sub.getTotalPrice();
            String frequency = sub.getInputFrequency();
            double frequencyAmount = sub.getFrequencyAmount();

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
    


       public void refreshSubscriptionsTable() {
        subscriptionSummaryTableModel.setRowCount(0);
        List<Subscription> sortedSubscription = SubscriptionSummary.getSortedSubscriptions();
        int recentLimit = Math.min(3, subscriptions.size());
        for (int i = 0; i < recentLimit; i++) {
            Subscription sub = sortedSubscription.get(i);
            
            subscriptionSummaryTableModel.addRow(new Object[] {
            sub.getSubscription(),
            String.format("$%.2f", calculateWeeklyCostForSubscriptionsTable(sub)),
            String.format("$%.2f", calculateMonthlyCostForSubscriptionsTable(sub)),
            String.format("$%.2f", calculateYearlyCostForSubscriptionsTable(sub))
            });

            }
       }


        public static void UpdateTotalPrice(){
        
        double totalPrice = getSumOfAllIncome();
        totalSubscriptionLabel.setText("Total Price of Subscription: $" + String.format("%.2f", totalPrice));

        double weeklySubscriptionAmount = calculateWeeklyCostOfSubscription();
        weeklyAmount.setText("Estimated Weekly Price of Subscription: $" + String.format("%.2f", weeklySubscriptionAmount));

        double monthlySubscriptionAmount = calculateMonthlyCostofSubscription();
        monthlyAmount.setText("Estimated Monthly Price of Subscription: $" + String.format("%.2f", monthlySubscriptionAmount));

        double yearlySubscriptionAmount = calculateYearlyCostOfSubscription();
        yearlyAmount.setText("Estimated Yearly Price of Subscription: $" + String.format("%.2f", yearlySubscriptionAmount));
      }
    }
