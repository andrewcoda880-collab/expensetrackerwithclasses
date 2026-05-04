import javax.swing.*;

public class Warning {
    //We will see if entertainment is more than bills or a certain amount, as of now 25% of bills
    public static void checkEntertainmentVsBills(double entertainmentTotal, double billsTotal, double totalExpenses,double foodTotal, double transportTotal, double otherTotal) {
        if (totalExpenses > 1000) {
            if (billsTotal == 0){
                String warningMessage = "You havent put anything in your bills, is everything you inputted correct?";
                JOptionPane.showMessageDialog(null,warningMessage,
                    "Critical Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Check if entertainment is 10% or more of Total Expenses
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
                double foodpercentage = (foodTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Food expenses ($%.2f) are %.1f%% of your Total ($%.2f)!\n" +
                    "Is all this food nessisary, groceries? Takeout? Consider thinking of spending less on food!",
                    foodTotal, foodpercentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if  (transportTotal >= totalExpenses * 0.15){
                double transportpercentage = (transportTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Transport expenses ($%.2f) are %.1f%% of your Total ($%.2f)!\n" +
                    "How much are you going out? is all this going out nessisary? If not just stay at home save some money on gas!",
                    transportTotal, transportpercentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
                    return;
            }
            else if  (otherTotal >= totalExpenses * 0.15){
                double otherpercentage = (otherTotal / totalExpenses) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: The other category ($%.2f) are %.1f%% of your Total ($%.2f)!\n" +
                    "Are these things essential to you? Think more about how useful these expenses are and if they are nessisary",
                    otherTotal, otherpercentage, totalExpenses);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Budget Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
        return;
    }
}
}