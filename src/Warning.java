import javax.swing.*;

public class Warning {
    //We will see if entertainment is more than bills or a certain amount, as of now 25% of bills
    public static void checkEntertainmentVsBills(double entertainmentTotal, double billsTotal, double totalExpenses) {
        if (totalExpenses > 1000) {
            // Check if entertainment exceeds bills
            if (entertainmentTotal > billsTotal) {
                String warningMessage = String.format(
                    "⚠️ WARNING: Entertainment expenses ($%.2f) EXCEED Bills ($%.2f)!\n" +
                    "You're spending WAY more on entertainment than nessisary, its more than your Bill!\n" + entertainmentTotal, billsTotal);
                
                JOptionPane.showMessageDialog(
                    null,
                    warningMessage,
                    "Critical Budget Alert",
                    JOptionPane.WARNING_MESSAGE
                );
            }
            // Check if entertainment is 25% or more of bills
            else if (entertainmentTotal >= billsTotal * 0.25) {
                double percentage = (entertainmentTotal / billsTotal) * 100;
                String warningMessage = String.format(
                    "⚠️ Budget Alert: Entertainment expenses ($%.2f) are %.1f%% of your Bills ($%.2f)!\n" +
                    "Consider spending less money man!",
                    entertainmentTotal, percentage, billsTotal
                );
                
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