import java.awt.*;
import javax.swing.*;

public class mySettings extends JPanel {
    
    public mySettings() {
        setBackground(Constants.APP_COLOR);
        setLayout(new BorderLayout());
        JLabel label = new JLabel("My Settings", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);
    }
}