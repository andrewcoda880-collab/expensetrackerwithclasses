import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class IncomeSummary extends JPanel {

    private final static List<Income> incomes = new ArrayList<>();  // List to hold income entries
    private static JLabel totalIncomeLabel;
    private static JLabel weeklyAmount;
    private static JLabel monthlyAmount;
    private static JLabel yearlyAmount;
    private  JTextField sourceOfIncomeField;
    public DefaultTableModel incomeSummaryTableModel;
    private JTable topIncome;
    

    public IncomeSummary(CardLayout layout, JPanel container) {
        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JButton AddIncomeButton = new JButton("Add Source of Income");
        AddIncomeButton.setBounds(300, 450, 200, 60);
        add(AddIncomeButton);

        AddIncomeButton.addActionListener(e -> layout.show(container, "MYINCOME"));
        

        JLabel summaryLabel = new JLabel("Income Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryLabel.setBounds(150, 20, 200, 30);
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        summaryLabel.setVerticalAlignment(JLabel.NORTH);
        add(summaryLabel);

        double totalIncome = getSumOfAllIncome();
        totalIncomeLabel = new JLabel("Total Income: $" + String.format("%.2f", totalIncome));
        totalIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        totalIncomeLabel.setBounds(0, 400, 350, 30);
        add(totalIncomeLabel);

        weeklyAmount = new JLabel("Estimated Weekly Income: $0.00");
        weeklyAmount.setFont(new Font("Arial", Font.BOLD, 18));
        weeklyAmount.setBounds(0, 430, 350, 30);
        add(weeklyAmount);

        monthlyAmount = new JLabel("Estimated Monthly Income: $0.00");
        monthlyAmount.setFont(new Font("Arial", Font.BOLD, 18));
        monthlyAmount.setBounds(0, 460, 350, 30);
        add(monthlyAmount);

        yearlyAmount = new JLabel("Estimated Yearly Income: $0.00");
        yearlyAmount.setFont(new Font("Arial", Font.BOLD, 18));
        yearlyAmount.setBounds(0, 490, 350, 30);
        add(yearlyAmount);

        //Income Summary Calculation Table
        String[] columnsForSummaryTable = { "Source of Income", "Weekly", "Monthly", "Yearly"};
        incomeSummaryTableModel = new DefaultTableModel(columnsForSummaryTable, 0);
        topIncome = new JTable(incomeSummaryTableModel);
        JScrollPane incomeScrollPane = new JScrollPane(topIncome);
        incomeScrollPane.setBounds(0, 100, 500, 300);
        add(incomeScrollPane);

        UpdateTotalIncome();

  

    }

        public static void addIncome(Income income){ 
        incomes.add(income);
    }
    
        public static List<Income> getSortedIncomes(){ // sorts our incomes from high to low
        List<Income> sortedIncomes = new ArrayList<>(incomes);
        sortedIncomes.sort((a,b) -> Double.compare(b.getTotalIncome(), a.getTotalIncome()));
        return sortedIncomes;
    }

        public List<Income> getIncomes() { // returns regular list of incomes ()
        return incomes;
    }
        
        public static double getSumOfAllIncome() {
            double totalIncome = 0;
            for (Income i : incomes){
                totalIncome += i.getTotalIncome();
            }
            return totalIncome;
       }   
            

            public static double calculateWeeklyIncome(){

            double weeklyIncome = 0;
            for (Income i :incomes){
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();


            if (frequency.contains("Week")) {
                weeklyIncome += totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Month")){
               weeklyIncome += totalIncome / (frequencyAmount * 4.345);
            }
            else if (frequency.contains("Year")){
               weeklyIncome += totalIncome / (frequencyAmount * 52);
            }
           }
           return weeklyIncome;
        }

            public static double calculateMonthyIncome(){
            double monthlyIncome = 0;
            for (Income i :incomes){
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();


            if (frequency.contains("Week")) {
                monthlyIncome += totalIncome / (frequencyAmount) * 4.345;
            }
            else if (frequency.contains("Month")){
               monthlyIncome += totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Year")){
               monthlyIncome += totalIncome / (frequencyAmount * 12);
            }
           }
           return monthlyIncome;
        }

        public static double calculateYearlyIncome(){
            double yearlyIncome = 0;
            for (Income i :incomes){
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();


            if (frequency.contains("Week")) {
                yearlyIncome += (totalIncome / frequencyAmount)* 52;
            }
            else if (frequency.contains("Month")){
               yearlyIncome += (totalIncome / frequencyAmount) * 12;
            }
            else if (frequency.contains("Year")){
               yearlyIncome += totalIncome / (frequencyAmount);
            }
           }
           return yearlyIncome;
        }

        public static double calculateWeeklyIncomeForIncomeTable(Income income){
            //Income income comes from the class income
            
            double totalIncome = income.getTotalIncome();
            String frequency = income.getInputFrequency();
            double frequencyAmount = income.getFrequencyAmount();

                if (frequency.contains("Week")) {
                return totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Month")){
               return totalIncome / (frequencyAmount * 4.345);
            }
            else if (frequency.contains("Year")){
               return totalIncome / (frequencyAmount * 52);
            }      
           return 0;
        }

            public static double calculateMonthlyIncomeForIncomeTable(Income income){
            //Income income comes from the class income
            
            double totalIncome = income.getTotalIncome();
            String frequency = income.getInputFrequency();
            double frequencyAmount = income.getFrequencyAmount();


                if (frequency.contains("Week")) {
                return totalIncome / (frequencyAmount) * 4.345;
            }
            else if (frequency.contains("Month")){
               return totalIncome / frequencyAmount;
            }
            else if (frequency.contains("Year")){
               return totalIncome / (frequencyAmount * 12);
            }
            return 0;
           
        }

            public static double calculateYearlyIncomeForIncomeTable(Income income){
            //Income income comes from the class income
            
            double totalIncome = income.getTotalIncome();
            String frequency = income.getInputFrequency();
            double frequencyAmount = income.getFrequencyAmount();

        if (frequency.contains("Week")) {
                return (totalIncome / frequencyAmount)* 52;
            }
            else if (frequency.contains("Month")){
               return (totalIncome / frequencyAmount) * 12;
            }
            else if (frequency.contains("Year")){
               return totalIncome / (frequencyAmount);
            }
            
           return 0;
        }
    


       public void refreshIncomeTable() {
        incomeSummaryTableModel.setRowCount(0);
        List<Income> sortedIncomes = IncomeSummary.getSortedIncomes();
        int recentLimit = Math.min(3, incomes.size());
        for (int i = 0; i < recentLimit; i++) {
            Income income = sortedIncomes.get(i);
            
            incomeSummaryTableModel.addRow(new Object[] {
              income.getSource(),
            String.format("$%.2f", calculateWeeklyIncomeForIncomeTable(income)),
            String.format("$%.2f", calculateMonthlyIncomeForIncomeTable(income)),
            String.format("$%.2f", calculateYearlyIncomeForIncomeTable(income))
            });

            }
       }


        public static void UpdateTotalIncome(){
         //printing total income text
        double totalIncome = getSumOfAllIncome();
        totalIncomeLabel.setText("Total Income: $" + String.format("%.2f", totalIncome));

        double weeklyIncomeAmount = calculateWeeklyIncome();
        weeklyAmount.setText("Estimated Weekly Income: $" + String.format("%.2f", weeklyIncomeAmount));

        double monthlyIncomeAmount = calculateMonthyIncome();
        monthlyAmount.setText("Estimated Monthly Income: $" + String.format("%.2f", monthlyIncomeAmount));

        double yearlyIncomeAmount = calculateYearlyIncome();
        yearlyAmount.setText("Estimated Yearly Income: $" + String.format("%.2f", yearlyIncomeAmount));
      }
    }
