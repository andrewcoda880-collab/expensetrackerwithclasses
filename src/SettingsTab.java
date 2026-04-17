import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SettingsTab extends JPanel {

        private JComboBox<String> settingOptions;
        private JButton themeChange;

    public SettingsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Setting$");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        String titleNotificationSettings = "Notification Settings";
        notifsMenu = new DefaultTableModel();


        //hey!
        //yo
        //commit test
        //heyo
        //starting at 10:58 I (steve) am now gona work on the settingstab
        
        
         
    }
}