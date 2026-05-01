import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SettingsTab extends JPanel {
    private DefaultTableModel notifsMenu;
    private DefaultTableModel miscMenu;
    private JTable notifSettings;
    private JTable miscSettingsTable;

    public SettingsTab() {
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBackground(Constants.APP_COLOR);

        // Notification Settings
        JLabel titleNotificationSettings = new JLabel("Notification Settings");
        titleNotificationSettings.setFont(new Font("Arial", Font.BOLD, 16));
        titleNotificationSettings.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        notifsMenu = new DefaultTableModel(new Object[]{"Setting", "Value"}, 0);
        notifSettings = new JTable(notifsMenu);
        notifSettings.setGridColor(new Color(50, 50, 185));
        notifSettings.setBackground(Constants.APP_COLOR);
        
        notifsMenu.addRow(new Object[]{"Enable notifications", "Weekly"});
        JComboBox<String> tableComboBox = new JComboBox<>(new String[]{"Weekly", "Monthly", "Weekly/Monthly", "Off"});
        notifSettings.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(tableComboBox));
        
        JScrollPane notifSettingsScrollPane = new JScrollPane(notifSettings);
        notifSettingsScrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 1));
        
        settingsPanel.add(titleNotificationSettings);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(notifSettingsScrollPane);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // App Settings
        JLabel miscSettings = new JLabel("App Settings");
        miscSettings.setFont(new Font("Arial", Font.BOLD, 16));
        miscSettings.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        miscMenu = new DefaultTableModel(new Object[]{"Setting", "Value"}, 0);
        miscSettingsTable = new JTable(miscMenu);
        miscSettingsTable.setGridColor(new Color(50, 50, 185));
        miscSettingsTable.setBackground(Constants.APP_COLOR);
        
        miscMenu.addRow(new Object[]{"Theme", "Light"});
        miscMenu.addRow(new Object[]{"Currency", "USD"});
        
        JScrollPane miscSettingsScrollPane = new JScrollPane(miscSettingsTable);
        miscSettingsScrollPane.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 1));
        
        settingsPanel.add(miscSettings);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        settingsPanel.add(miscSettingsScrollPane);

        JScrollPane mainScrollPane = new JScrollPane(settingsPanel);
        mainScrollPane.setBorder(null);
        mainScrollPane.getViewport().setBackground(Constants.APP_COLOR);
        add(mainScrollPane, BorderLayout.CENTER);
    }
}