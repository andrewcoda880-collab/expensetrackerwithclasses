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
<<<<<<< HEAD

    public double getSumOfAllExpenses() {
        double total = 0;
        for (Expense e : expenses){
            total += e.getAmount();
        }
        return total;
=======
    
    // Add this method
    public double getSumOfAllExpenses() {
        double sum = 0;
        for (Expense expense : expenses) {
            sum += expense.getAmount();
        }
        return sum;
>>>>>>> 9bf4d94062f0a308f0efc0adfd3755c1d11936bf
    }
}
