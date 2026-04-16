import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class mySubscriptions extends JPanel {

    //this class will display the summary of the user's subscriptions, including the name of the subscription, the cost, and the renewal date. 
    //This will be accessed through the "My Subscriptions" button on the homepage.

    //constructor 
    public mySubscriptions() {
        setBackground(Constants.APP_COLOR);
        JLabel titleLabel = new JLabel("My Subscriptions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
    }
}