import java.awt.*;
import javax.swing.*;

public class LoginTab extends JPanel {
    
    public LoginTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.CENTER);
    }
}
