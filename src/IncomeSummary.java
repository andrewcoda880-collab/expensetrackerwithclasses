import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class IncomeSummary extends JPanel {

    private final static List<Income> incomes = new ArrayList<>();
    private static JLabel totalIncomeLabel;
    private static JLabel weeklyAmount;
    private static JLabel monthlyAmount;
    private static JLabel yearlyAmount;
    private CardLayout layout;
    private JPanel container;

    public IncomeSummary(CardLayout layout, JPanel container) {
        this.layout = layout;
        this.container = container;
        
        setBackground(Constants.APP_COLOR);
        setLayout(null);

        JLabel summaryLabel = new JLabel("Income Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryLabel.setBounds(150, 20, 200, 30);
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        add(summaryLabel);

        totalIncomeLabel = new JLabel("Total Income: $0.00");
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

        yearlyAmount = new JLabel("Estimated Yearly Income: $0.00");  // Fixed label text
        yearlyAmount.setFont(new Font("Arial", Font.BOLD, 18));
        yearlyAmount.setBounds(0, 490, 350, 30);
        add(yearlyAmount);

        UpdateTotalIncome();
    }

    public static void addIncome(Income income) { 
        incomes.add(income);
    }
    
    public static List<Income> getSortedIncomes() {
        List<Income> sortedIncomes = new ArrayList<>(incomes);
        sortedIncomes.sort((a,b) -> Double.compare(b.getTotalIncome(), a.getTotalIncome()));
        return sortedIncomes;
    }

    public List<Income> getIncomes() {
        return incomes;
    }
    
    public static double getSumOfAllIncome() {
        double totalIncome = 0;
        for (Income i : incomes){
            totalIncome += i.getTotalIncome();
        }
        return totalIncome;
    }   

    public static double calculateWeeklyIncome() {
        double weeklyIncome = 0;
        for (Income i : incomes) {
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();

            if (frequency.contains("Week")) {
                weeklyIncome += totalIncome / frequencyAmount;
            } else if (frequency.contains("Month")) {
                weeklyIncome += totalIncome / (frequencyAmount * 4.345);
            } else if (frequency.contains("Year")) {
                weeklyIncome += totalIncome / (frequencyAmount * 52);
            }
        }
        return weeklyIncome;
    }

    public static double calculateMonthlyIncome() {  // Fixed spelling
        double monthlyIncome = 0;
        for (Income i : incomes) {
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();

            if (frequency.contains("Week")) {
                monthlyIncome += (totalIncome / frequencyAmount) * 4.345;
            } else if (frequency.contains("Month")) {
                monthlyIncome += totalIncome / frequencyAmount;
            } else if (frequency.contains("Year")) {
                monthlyIncome += totalIncome / (frequencyAmount * 12);
            }
        }
        return monthlyIncome;
    }

    public static double calculateYearlyIncome() {
        double yearlyIncome = 0;
        for (Income i : incomes) {
            double totalIncome = i.getTotalIncome();
            String frequency = i.getInputFrequency();
            double frequencyAmount = i.getFrequencyAmount();

            if (frequency.contains("Week")) {
                yearlyIncome += (totalIncome / frequencyAmount) * 52;
            } else if (frequency.contains("Month")) {
                yearlyIncome += (totalIncome / frequencyAmount) * 12;
            } else if (frequency.contains("Year")) {
                yearlyIncome += totalIncome / frequencyAmount;
            }
        }
        return yearlyIncome;
    }

    public static void UpdateTotalIncome() {
        double totalIncome = getSumOfAllIncome();
        totalIncomeLabel.setText("Total Income: $" + String.format("%.2f", totalIncome));

        double weeklyIncomeAmount = calculateWeeklyIncome();
        weeklyAmount.setText("Estimated Weekly Income: $" + String.format("%.2f", weeklyIncomeAmount));

        double monthlyIncomeAmount = calculateMonthlyIncome();
        monthlyAmount.setText("Estimated Monthly Income: $" + String.format("%.2f", monthlyIncomeAmount));

        double yearlyIncomeAmount = calculateYearlyIncome();
        yearlyAmount.setText("Estimated Yearly Income: $" + String.format("%.2f", yearlyIncomeAmount));
    }
}