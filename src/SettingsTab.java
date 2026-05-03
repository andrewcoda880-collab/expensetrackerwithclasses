import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SettingsTab extends JPanel {
        private JComboBox<String> notifsMenu;
        private JComboBox<String> chartMenu;
        private UserSettings userSettings;

    public SettingsTab(UserSettings userSettings){
        this.userSettings = userSettings;
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Settings Page");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);
        
        JPanel notifPanel = new JPanel();
        TitledBorder notifBorder;
        notifBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Notification Settings");
        notifPanel.setLayout(new BoxLayout(notifPanel, BoxLayout.Y_AXIS));
        notifPanel.setPreferredSize(new Dimension(500, 250));
        notifPanel.setBackground(Color.LIGHT_GRAY);
        notifPanel.setBorder(notifBorder);
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(2, 2, 4, 4));
        settingsPanel.setBackground(Constants.APP_COLOR);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Constants.APP_COLOR);

        JLabel titleNotificationSettings = new JLabel("Enable Notifications");
        titleNotificationSettings.setFont(new Font("Arial", Font.BOLD, 14));
        notifsMenu = new JComboBox<>(new String[]{"Weekly", "Monthly", "Bi-Weekly", "Off"});
        titleNotificationSettings.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 6));

        JLabel chartType = new JLabel("Chart Types");
        chartType.setFont(new Font("Arial", Font.BOLD, 14));
        chartMenu = new JComboBox<>(new String[]{"Pie", "Bar", "Line"});

        notifsMenu.setSelectedItem(userSettings.getTimeNotifications());
        chartMenu.setSelectedItem(userSettings.getChartPreference());

        settingsPanel.add(titleNotificationSettings);
        settingsPanel.add(notifsMenu);
        settingsPanel.add(chartType);
        settingsPanel.add(chartMenu);
        notifPanel.add(settingsPanel);
        add(notifPanel, BorderLayout.CENTER);


        JLabel miscSettings = new JLabel("Light Mode:");
        miscSettings.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 9));
        textPanel.setBackground(Color.LIGHT_GRAY);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5)); 
        sliderPanel.setBackground(Color.LIGHT_GRAY);
        
        JPanel themePanel = new JPanel();
        TitledBorder appBorder;
        appBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "App Settings");
        themePanel.setLayout(new BorderLayout(10, 0));
        themePanel.setPreferredSize(new Dimension(500, 225));
        themePanel.setBackground(Color.LIGHT_GRAY);
        themePanel.setBorder(appBorder);
        JSlider themeSlider = new JSlider(0, 1, userSettings.isLightMode() ? 1 : 0);
        themeSlider.setPreferredSize(new Dimension(50, 25));

        JButton saveSettings = new JButton("Save Settings");
        saveSettings.setPreferredSize(new Dimension(105, 35));
        saveSettings.setForeground(new Color(50, 50, 185));
        buttonPanel.add(saveSettings);

        saveSettings.addActionListener(e -> {
            String selectedNotif = (String) notifsMenu.getSelectedItem();
            String selectedChart = (String) chartMenu.getSelectedItem();
            boolean lightMode = (themeSlider.getValue() == 1);

            userSettings.savePreferences(selectedNotif, selectedChart, lightMode);
            userSettings.changePreferences();
            JOptionPane.showMessageDialog(this, "Settings saved!:\nNotifications: " + selectedNotif + "\nChart Type: " + selectedChart + "\nTheme Mode: " + (lightMode ? "Light" : "Dark"));
        });

        textPanel.add(miscSettings);
        sliderPanel.add(themeSlider);
        themePanel.add(textPanel, BorderLayout.WEST);
        themePanel.add(sliderPanel, BorderLayout.EAST);
        themePanel.add(buttonPanel, BorderLayout.SOUTH);
        add(themePanel, BorderLayout.SOUTH);



        
         
    }
}