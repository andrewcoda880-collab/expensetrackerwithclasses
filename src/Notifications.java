import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class Notifications {

    private UserSettings userSettings;
    private ExpenseManager expenseManager;

    public Notifications(UserSettings userSettings, ExpenseManager expenseManager) {
        this.userSettings = userSettings;
        this.expenseManager = expenseManager;
    }

    /**
     * Checks if notifications are enabled and displays a notification if true
     * The notification shows total expenses for the selected period
     */
    public void checkAndShowNotification() {
        String notificationFrequency = userSettings.getTimeNotifications();
        if (notificationFrequency.equals("Off")) {
            return;
        }

        double totalExpenses = 0;
        String periodLabel = "";

        switch (notificationFrequency) {
            case "Weekly":
                totalExpenses = calculateWeeklyExpenses();
                periodLabel = "This Week";
                break;
            case "Monthly":
                totalExpenses = calculateMonthlyExpenses();
                periodLabel = "This Month";
                break;
            case "Bi-Weekly":
                totalExpenses = calculateBiWeeklyExpenses();
                periodLabel = "These 2 Weeks";
                break;
        }

        displayNotification(totalExpenses, periodLabel);
    }

    /**
     * Calculates the total expenses for the current week
     */
    private double calculateWeeklyExpenses() {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int currentWeek = today.get(weekFields.weekOfWeekBasedYear());
        int currentYear = today.get(weekFields.weekBasedYear());

        return expenseManager.getExpenses().stream()
                .filter(expense -> {
                    LocalDate expenseDate = expense.getDate();
                    int expenseWeek = expenseDate.get(weekFields.weekOfWeekBasedYear());
                    int expenseYear = expenseDate.get(weekFields.weekBasedYear());
                    return expenseWeek == currentWeek && expenseYear == currentYear;
                }).mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Calculates the total expenses for the current month
     */
    private double calculateMonthlyExpenses() {
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        return expenseManager.getExpenses().stream().filter(expense -> {
                    LocalDate expenseDate = expense.getDate();
                    return expenseDate.getMonthValue() == currentMonth && expenseDate.getYear() == currentYear;
                }).mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Calculates the total expenses for the current bi-weekly period (current week + previous week)
     */
    private double calculateBiWeeklyExpenses() {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksAgo = today.minusWeeks(2);
        
        return expenseManager.getExpenses().stream()
                .filter(expense -> {
                    LocalDate expenseDate = expense.getDate();
                    return !expenseDate.isBefore(twoWeeksAgo) && !expenseDate.isAfter(today);
                }).mapToDouble(Expense::getAmount).sum();
    }

    /**
     * Displays a notification dialog with total expenses for a single period
     */
    private void displayNotification(double totalExpenses, String periodLabel) {
        String message = String.format(
                "Expense Summary - %s\n\n" + "Total Charged: $%.2f",
                periodLabel,
                totalExpenses
        );

        JOptionPane.showMessageDialog(
                null,
                message,
                "Expense Notification",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public double getExpenseTotal(String period) {
        switch (period.toLowerCase()) {
            case "weekly":
                return calculateWeeklyExpenses();
            case "monthly":
                return calculateMonthlyExpenses();
            case "bi-weekly":
                return calculateBiWeeklyExpenses();
            default:
                return 0;
        }
    }
}
