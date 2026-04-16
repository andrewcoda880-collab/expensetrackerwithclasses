import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense) { 
        expenses.add(expense);
    }

    public List<Expense> getSortedExpenses() {
        List<Expense> sortedExpenses = new ArrayList<>(expenses);
        sortedExpenses.sort((a, b) -> Double.compare(b.getAmount(), a.getAmount()));
        return sortedExpenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
    
    // Add this method
    public double getSumOfAllExpenses() {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }
        return sum;
    }
}