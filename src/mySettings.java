import java.awt.*;
import javax.swing.*;

public class mySettings extends JPanel {
    private UserSettings userSettings;

    public mySettings(UserSettings userSettings) {
        this.userSettings = userSettings;
        setBackground(Constants.APP_COLOR);
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("My Settings");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        
        JPanel settingsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        settingsPanel.setBackground(Constants.APP_COLOR);
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        settingsPanel.add(new JLabel("Notification Frequency:"));
        JComboBox<String> notifCombo = new JComboBox<>(new String[]{"Weekly", "Monthly", "Bi-Weekly", "Off"});
        notifCombo.setSelectedItem(userSettings.getTimeNotifications());
        settingsPanel.add(notifCombo);
        
        settingsPanel.add(new JLabel("Chart Type:"));
        JComboBox<String> chartCombo = new JComboBox<>(new String[]{"Pie", "Bar", "Both"});
        chartCombo.setSelectedItem(userSettings.getChartPreference());
        settingsPanel.add(chartCombo);
        
        JButton saveButton = new JButton("Save Settings");
        saveButton.addActionListener(e -> {
            userSettings.savePreferences(
                (String) notifCombo.getSelectedItem(),
                (String) chartCombo.getSelectedItem(),
                userSettings.isLightMode()
            );
            JOptionPane.showMessageDialog(this, "Settings saved!");
        });
        settingsPanel.add(saveButton);
        
        add(settingsPanel, BorderLayout.CENTER);
    }
}