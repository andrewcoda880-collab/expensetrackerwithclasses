import javax.swing.*;

public class Warning {
    //We will see if entertainment is more than bills or a certain amount, as of now 25% of bills
    public static void checkEntertainmentVsBills(double entertainmentTotal, double billsTotal, double totalExpenses,double foodTotal, double otherTotal, double transportTotal) {
        if (totalExpenses > 1000) {
            if (billsTotal == 0){
                String warningMessage = "You havent put anything in your bills, is everything you inputted correct?";
                JOptionPane.showMessageDialog(null,warningMessage,
                    "Critical Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Check if entertainment is 25% or more of Total Expenses
            else if (entertainmentTotal >= totalExpenses * 0.10) {
                double percentage = (entertainmentTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Entertainment expenses ($%.2f) are %.1f%% of your total Expenses! ($%.2f)!\n" +
                    "Consider spending less money on entertainment MAN!",
                    entertainmentTotal, percentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if (foodTotal >= totalExpenses * 0.15) {
                double percentage = (entertainmentTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Food expenses ($%.2f) are %.1f%% of your Total ($%.2f)!\n" +
                    "Is all this food nessisary, groceries? Takeout? Consider thinking of spending less on food!",
                    foodTotal, percentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if  (transportTotal >= totalExpenses * 0.15){
                double percentage = (transportTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Transport expenses ($%.2f) are %.1f%% of your Total ($%.2f)!\n" +
                    "How much are you going out? is all this going out nessisary? If not just stay at home save some money on gas!",
                    transportTotal, percentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
        return;
    }
}