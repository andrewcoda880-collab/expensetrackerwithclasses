import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myBudget extends JPanel {

    //This class represents the user's budget. It will be accessed through the "My Budget" tab on the Home screen.
    // It will display the user's current budget and allow them to set a new budget.


    //constructor
    public myBudget() {

    }

    public  void refreshBudget(){

        removeAll();
        
        setBackground(Constants.APP_COLOR);
        JLabel label = new JLabel("Budget");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(200, 0, 500, 50);
        add(label);
        setLayout(null);


        //variables for income
        double weeklyIncome = IncomeSummary.calculateWeeklyIncome();
        double monthlyIncome = IncomeSummary.calculateMonthlyIncome();
        double yearlyIncome = IncomeSummary.calculateYearlyIncome();
        

        //variables for bills
        double weeklyTotalBills = BillsSummary.calculateWeeklyCostOfBill();
        double monthlyTotalBills = BillsSummary.calculateMonthlyCostOfBill();
        double yearlyTotalBills = BillsSummary.calculateYearlyCostOfBill();


        //variables for subscriptions
        double weeklyTotalSubscriptions = SubscriptionSummary.calculateWeeklyCostOfSubscription();
        double monthlyTotalSubscriptions =SubscriptionSummary.calculateMonthlyCostOfSubscription();
        double yearlyTotalSubscriptions =SubscriptionSummary.calculateYearlyCostOfSubscription();


        //Variables for the total of the other expenses.

        //Calculate Estimated LeftOver Money After Bills (Weekly)
        double weeklyLeftoverAfterBills = weeklyIncome - weeklyTotalBills;

        //Calculate Estimated LeftOver Money After Bills (Monthly)
        double monthlyLeftoverAfterBills = monthlyIncome - monthlyTotalBills;

        //Calculate Estimated LeftOver Money After Bills (Yearly)
        double yearlyLeftoverAfterBills = yearlyIncome - yearlyTotalBills;

        //variables for other expenses
        double otherExpenses = ExpenseManager.getSumOfAllExpenses();

        //Calculate Estimated leftover money after paying for everything (Weekly)
        double weeklyLeftoverMoneyAfterEverything = weeklyIncome - weeklyTotalBills - weeklyTotalSubscriptions - otherExpenses;

        //Calculate Estimated leftover money after paying for everything (Monthly)
        double monthlyLeftoverMoneyAfterEverything = monthlyIncome - monthlyTotalBills - monthlyTotalSubscriptions - otherExpenses;

        //Calculate Estimated leftover money after paying for everything (Yearly)
        double yearlyLeftoverMoneyAfterEverything = yearlyIncome - yearlyTotalBills - yearlyTotalSubscriptions - otherExpenses;

        
        JLabel weeklyAfterBills = new JLabel("The weekly estimated leftover money after paying bills is: " + "$"+ String.format("%.2f",weeklyLeftoverAfterBills));
        weeklyAfterBills.setFont(new Font("Arial", Font.BOLD, 15));
        weeklyAfterBills.setBounds(0, 50, 500, 50);
        add(weeklyAfterBills);

        JLabel monthlyAfterBills = new JLabel("The estimated monthly leftover money after paying bills is: " + "$" + String.format("%.2f", monthlyLeftoverAfterBills));
        monthlyAfterBills.setFont(new Font("Arial", Font.BOLD, 15));
        monthlyAfterBills.setBounds(0, 100, 500, 50);
        add(monthlyAfterBills);

        JLabel yearlyAfterBills = new JLabel("The estimated yearly leftover money after paying bills is: " + "$" + String.format("%.2f",yearlyLeftoverAfterBills));
        yearlyAfterBills.setFont(new Font("Arial", Font.BOLD, 15));
        yearlyAfterBills.setBounds(0, 150, 500, 50);
        add(yearlyAfterBills);



        //Calculate Estimated LeftOver Money After Bills And Subscriptions (Weekly)
        double weeklyLeftoverAfterBillsAndSubscriptions = weeklyLeftoverAfterBills - weeklyTotalSubscriptions;
        JLabel weeklyAfterBillsAndSubscriptions = new JLabel("The weekly estimated leftover money after paying bills and subscriptions is: " + "$" + String.format("%.2f",weeklyLeftoverAfterBillsAndSubscriptions));
        weeklyAfterBillsAndSubscriptions.setFont(new Font("Arial", Font.BOLD, 12));
        weeklyAfterBillsAndSubscriptions.setBounds(0, 200, 500, 50);

        add(weeklyAfterBillsAndSubscriptions);
        //Calculate Estimated LeftOver Money After Bills And Subscriptions (Monthly)
        double monthlyLeftoverAfterBillsAndSubscriptions = monthlyLeftoverAfterBills - monthlyTotalSubscriptions;
        JLabel monthlyAfterBillsAndSubscriptions = new JLabel("The monthly estimated leftover money after paying bills and subscriptions is: " + "$" + String.format("%.2f", monthlyLeftoverAfterBillsAndSubscriptions));
        monthlyAfterBillsAndSubscriptions.setFont(new Font("Arial", Font.BOLD, 12));
        monthlyAfterBillsAndSubscriptions.setBounds(0, 250, 500, 50);
        
        add(monthlyAfterBillsAndSubscriptions);
        //Calculate Estimated LeftOver Money After Bills And Subscriptions (Yearly)
        double yearlyLeftoverAfterBillsAndSubscriptions = yearlyLeftoverAfterBills - yearlyTotalSubscriptions;
        JLabel yearlyAfterBillsAndSubscriptions = new JLabel("The yearly estimated leftover money after paying bills and subscriptions is: " + "$" + String.format("%.2f", yearlyLeftoverAfterBillsAndSubscriptions));
        yearlyAfterBillsAndSubscriptions.setFont(new Font("Arial", Font.BOLD, 12));
        yearlyAfterBillsAndSubscriptions.setBounds(0, 300, 500, 50);
        add(yearlyAfterBillsAndSubscriptions);

        //Estimated Leftover money after everything
        JLabel weeklyAfterEverything = new JLabel("The weekly estimated leftover money after paying bills, subscriptions and other expenses is: " + "$" + String.format("%.2f", weeklyLeftoverMoneyAfterEverything));
        weeklyAfterEverything.setFont(new Font("Arial", Font.BOLD, 10));
        weeklyAfterEverything.setBounds(0, 350, 550, 50);
        add(weeklyAfterEverything);

        //Estimated Leftover money after everything
        JLabel monthlyAfterEverything = new JLabel("The monthly estimated leftover money after paying bills, subscriptions and other expenses is: " + "$" + String.format("%.2f", monthlyLeftoverMoneyAfterEverything));
        monthlyAfterEverything.setFont(new Font("Arial", Font.BOLD, 10));
        monthlyAfterEverything.setBounds(0, 400, 550, 50);
        add(monthlyAfterEverything);

        //Estimated Leftover money after everything
        JLabel yearlyAfterEverything = new JLabel("The yearly estimated leftover money after paying bills, subscriptions and other expenses is: " + "$" + String.format("%.2f", yearlyLeftoverMoneyAfterEverything));
        yearlyAfterEverything.setFont(new Font("Arial", Font.BOLD, 10));
        yearlyAfterEverything.setBounds(0, 450, 550, 50);
        add(yearlyAfterEverything);




    }
}