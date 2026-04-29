import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SettingsTab extends JPanel {
        private DefaultTableModel notifsMenu;
        private DefaultTableModel miscMenu;
        private JTable notifSettings;
        private JComboBox<String> settingOptions;
        private JButton themeChange;

    public SettingsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Setting$");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBackground(Constants.APP_COLOR);

        JLabel titleNotificationSettings = new JLabel("Notification Settings");
        titleNotificationSettings.setFont(new Font("Arial", Font.BOLD, 16));
        notifsMenu = new DefaultTableModel(new Object[]{"Setting", "Value"}, 0);
        notifSettings = new JTable(notifsMenu);
        JScrollPane notifSettingsScrollPane = new JScrollPane(notifSettings);
        notifSettings.setGridColor(new Color(50, 50, 185));
        notifSettings.setBackground(Constants.APP_COLOR);
        titleNotificationSettings.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 6));

        notifsMenu.addRow(new Object[]{"Enable notifications", "Weekly"});
        JComboBox<String> tableComboBox = new JComboBox<>(new String[]{"Weekly", "Monthly", "Weekly/Monthly", "Off"});
        notifSettings.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(tableComboBox));
        
        settingsPanel.add(titleNotificationSettings);
        settingsPanel.add(notifSettingsScrollPane);
        add(settingsPanel, BorderLayout.CENTER);


        JLabel miscSettings = new JLabel("App Settings");
        miscSettings.setFont(new Font("Arial", Font.BOLD, 16));
        miscMenu = new DefaultTableModel(new Object[]{"Settings"}, 0);
        JTable miscSettingsTable = new JTable(miscMenu);
        JScrollPane miscSettingsScrollPane = new JScrollPane(miscSettingsTable);
        miscSettingsTable.setGridColor(new Color(50, 50, 185));
        miscSettingsTable.setBackground(Constants.APP_COLOR);
        miscSettings.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 3));

        settingsPanel.add(miscSettings);
        settingsPanel.add(miscSettingsScrollPane);


        // Remove the separate label and combo box since they're now in the table
        // settingsPanel.add(enableNotifs);
        // settingsPanel.add(settingOptions);


        //hey!
        //yo
        //commit test
        //heyo
        //starting at 10:58 I (steve) am now gona work on the settingstab
        
        
    }
}