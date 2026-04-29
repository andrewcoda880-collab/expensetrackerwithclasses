import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myInvestments extends JPanel {

    // This class represents the summary of the user's investments. This can be accessed from the "My Investments" button on the homepage.
    // It will display a summary of the user's investments, including the total amount invested, the current value of the investments,
    // and the profit/loss.

    //constructor
    public myInvestments() {
        setBackground(Constants.APP_COLOR);
        JLabel label = new JLabel("My Investments");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}