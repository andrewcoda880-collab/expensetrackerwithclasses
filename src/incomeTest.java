

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JPanel;




public class incomeTest {
    

    
    private myIncome MyIncome;

    
    @Before
    public void setUp() {
    MyIncome = new myIncome(new CardLayout(), new JPanel());

    }

    @Test
    public void testAddIncome() {
        Income income = new Income("Part Time Job", 100.0, "1 Month", 1.0);
        IncomeSummary.addIncome(income);


        assertEquals(1, IncomeSummary.getSortedIncomes().size());
        assertEquals("Part Time Job", IncomeSummary.getSortedIncomes().get(0).getSource());
        assertEquals(100.0, IncomeSummary.getSortedIncomes().get(0).getTotalIncome(), 0.001);
    }
}
    /* 
    @Test
    public void testAddExpenseIncreasesList2() {
        expenseManager.addExpense(new Expense("Coffee", 4.50, "Food"));
        expenseManager.addExpense(new Expense("Bus", 2.00, "Transport"));
        expenseManager.addExpense(new Expense("Rent", 800.00, "Bills"));
        expenseManager.addExpense(new Expense("Car Payment", 350.00, "Bills"));
        expenseManager.addExpense(new Expense("Netflix", 9.99, "Entertainment"));
        assertEquals(5, expenseManager.getExpenses().size());
    }

    @Test
    public void testSumOfAllExpenses() {
        expenseManager.addExpense(new Expense("Coffee", 4.50, "Food"));
        assertEquals(4.50, expenseManager.getSumOfAllExpenses(), 0.001);
    }

    @Test
    public void testSumOfAllExpenses2() {
        expenseManager.addExpense(new Expense("Coffee", 4.50, "Food"));
        expenseManager.addExpense(new Expense("Bus", 2.00, "Transport"));
        expenseManager.addExpense(new Expense("Rent", 800.00, "Bills"));
        expenseManager.addExpense(new Expense("Car Payment", 350.00, "Bills"));
        expenseManager.addExpense(new Expense("Netflix", 9.99, "Entertainment"));
        assertEquals(1166.49, expenseManager.getSumOfAllExpenses(), 0.001);
    }

    @Test
    public void testGetSortedExpenses() {
        expenseManager.addExpense(new Expense("Coffee", 4.50, "Food"));
        expenseManager.addExpense(new Expense("Rent", 800.00, "Bills"));
        expenseManager.addExpense(new Expense("Bus", 2.00, "Transport"));

        List<Expense> sorted = expenseManager.getSortedExpenses();
        assertEquals("Rent", sorted.get(0).getName());
        assertEquals("Coffee", sorted.get(1).getName());
        assertEquals("Bus", sorted.get(2).getName());
    }

    @Test
    public void testSumIsZeroWhenEmpty() {
        assertEquals(0.0, expenseManager.getSumOfAllExpenses(), 0.001);
    }

    @Test
    public void getRecentExpenses() {
        expenseManager.addExpense(new Expense("Coffee", 4.50, "Food"));
        expenseManager.addExpense(new Expense("Rent", 800.00, "Bills"));
        expenseManager.addExpense(new Expense("Bus", 2.00, "Transport"));

        List<Expense> recent = expenseManager.getExpenses();
        assertEquals("Bus", recent.get(2).getName());
        assertEquals("Rent", recent.get(1).getName());
        assertEquals("Coffee", recent.get(0).getName());
    }

    // The following fail because the safe-proofing is built into ExpensesTab (where the expenses are ultimately entered)
    // not in the expenseManager. Not sure if this should be changed. 
    @Test
    public void testSizeWhenExpenseAmountEnteredIsInvalid() {
        expenseManager.addExpense(new Expense("Coffee", -1.00, "Food"));
         assertEquals(0, expenseManager.getExpenses().size());
    }

    @Test
    public void testSizeWhenExpenseNameEnteredIsInvalid() {
        expenseManager.addExpense(new Expense("", 1.00, "Food"));
         assertEquals(0, expenseManager.getExpenses().size());
    }

    @Test
    public void testSizeWhenExpenseCategoryEnteredIsInvalid() {
        expenseManager.addExpense(new Expense("Coffee", 1.00, ""));
         assertEquals(0, expenseManager.getExpenses().size());
    }

    */
}