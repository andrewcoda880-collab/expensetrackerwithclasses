import java.awt.*;
import javax.swing.*;

public class IncomeSummary extends JPanel {

    public IncomeSummary() {
        setBackground(Constants.APP_COLOR);
        setLayout(null); // Set layout to null for absolute positioning

        JLabel summaryLabel = new JLabel("Income Summary");
        summaryLabel.setFont(new Font("Arial", Font.BOLD, 24));
        summaryLabel.setBounds(150, 20, 200, 30);
        summaryLabel.setHorizontalAlignment(JLabel.CENTER);
        summaryLabel.setVerticalAlignment(JLabel.NORTH);
        add(summaryLabel);

        // Additional components to display total income, current value, and profit/loss can be added here
    }

    public void totalIncome() {
        // This method will calculate the total income based on the data entered by the user in the myIncome panel.
        // It will then display this information in the IncomeSummary panel.
        // For simplicity, this method is currently empty and can be implemented in the future.
        


    }

    


}