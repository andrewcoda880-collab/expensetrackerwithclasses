import javax.swing.*;

public class Warning {
    
    //checker class to see if entertainment is more than your bills
    public static void checkEntertainmentVsBills(double checker, double billscheck, double Total) {
        if (Total > 1000){
         if (checker > billscheck) {
             String warningMessage = String.format(
                    "⚠️ Warning: Entertainment expenses ($%.2f) exceed Bills ($%.2f)!\n" +
                 "You should check on what you're spending",
                 checker, billscheck
                );
            
            JOptionPane.showMessageDialog(
                null,
                warningMessage,
                "Budget Alert",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }
    else {
        return;
    }
    }
}