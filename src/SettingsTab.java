import java.awt.*;
import javax.swing.*;

public class SettingsTab extends JPanel {
    
    public SettingsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.CENTER);

        //hey!
        //yo
        
         
    }
}