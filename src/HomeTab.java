import java.awt.*;
import javax.swing.*;

public class HomeTab extends JPanel {
    
    public HomeTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Home");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(150, 150, 250, 250);

        
    }
}
