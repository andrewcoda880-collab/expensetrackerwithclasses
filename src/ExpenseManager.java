
import java.util.ArrayList;
import java.util.List;


public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>(); // list of our expenses

    public void addExpense(Expense expense){ 
        expenses.add(expense);
    }

    public List<Expense> getSortedExpenses(){ // sorts our expenses from high to low
        List<Expense> sortedExpenses = new ArrayList<>(expenses);
        sortedExpenses.sort((a,b) -> Double.compare(b.getAmount(), a.getAmount()));
        return sortedExpenses;
  
    }

    public List<Expense> getExpenses() { // returns regular list of expenses ()
        return expenses;
    }

    public double getSumOfAllExpenses() {
        double total = 0;
        for (Expense e : expenses){
            total += e.getAmount();
        }
        return total;
    }
}
