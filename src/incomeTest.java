

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
    myIncome MyIncome = new myIncome(new CardLayout(), new JPanel());

    }
    //#1
     @Test
    public void testLargeIncome() {
        Income income = new Income("Part Time Job", 12345678.00, "1 Month", 1.0);

        IncomeSummary.addIncome(income);

        assertEquals(1, IncomeSummary.getSortedIncomes().size());
    }
    //#2
    @Test
    public void testSmallIncome() {
        Income income = new Income("Part Time Job", 0.000000005, "1 Month", 1.0);

        IncomeSummary.addIncome(income);

        assertEquals(1, IncomeSummary.getSortedIncomes().size());
    }
    //#3
    @Test
    public void testNegativeIncome() {
        Income income = new Income("Part Time Job", -100, "1 Week", 1.0);

        IncomeSummary.addIncome(income);

        assertEquals(1, IncomeSummary.getSortedIncomes().size());
    }

    //#4
        @Test
        public void testZeroIncome() {
        Income income = new Income("Part Time Job", 0, "1 Week", 1.0);

        IncomeSummary.addIncome(income);
 
        assertEquals(1, IncomeSummary.getSortedIncomes().size());
    }
        //#5
        @Test
        public void testNoInputsForAll() {
        myIncome incomePanel = new myIncome(new CardLayout(), new JPanel());

        incomePanel.setSourceOfIncome(" ");
        incomePanel.setTotalIncome("");
        incomePanel.setFrequencyAmount("");
        incomePanel.setFrequencyMenu("");

        incomePanel.addIncome();

        assertEquals(0, IncomeSummary.getSortedIncomes().size());
}

        //#6
        @Test
        public void testForNoInputForOneComponent(){
        myIncome incomePanel = new myIncome(new CardLayout(), new JPanel());

        incomePanel.setSourceOfIncome("");
        incomePanel.setTotalIncome("100");
        incomePanel.setFrequencyAmount("1");
        incomePanel.setFrequencyMenu("weekly");

        incomePanel.addIncome();

        assertEquals(0, IncomeSummary.getSortedIncomes().size());
    }

        //#7
        @Test
        public void testForNoInputForThreeOrMoreDecimalPlaces(){

        Income income = new Income("job", 100.4563, "2 Months", 1.0);

        IncomeSummary.addIncome(income);

        assertEquals(1, IncomeSummary.getSortedIncomes().size());
        

        
    }

        //#8
        @Test
        public void testForStringForTotalIncome(){
        myIncome incomePanel = new myIncome(new CardLayout(), new JPanel());

        incomePanel.setSourceOfIncome("job");
        incomePanel.setTotalIncome("abc");
        incomePanel.setFrequencyAmount("2");
        incomePanel.setFrequencyMenu("Monthly");

        incomePanel.addIncome();

        assertEquals(0, IncomeSummary.getSortedIncomes().size());
    }


        //#9
        @Test
        public void testForStringForFrequencyAmount(){
        myIncome incomePanel = new myIncome(new CardLayout(), new JPanel());

        incomePanel.setSourceOfIncome("job");
        incomePanel.setTotalIncome("200");
        incomePanel.setFrequencyAmount("xyz");
        incomePanel.setFrequencyMenu("Monthly");

        incomePanel.addIncome();

        assertEquals(0, IncomeSummary.getSortedIncomes().size());
        }
        

    


}

