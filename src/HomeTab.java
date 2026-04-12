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

        
        JPanel homepagePanel = new JPanel();
        JLabel homepageLabel = new JLabel();

        homepagePanel.add(homepageLabel);
        homepagePanel.setLayout(new GridBagLayout());
        GridBagConstraints homepagePanelLayout = new GridBagConstraints();
        JButton settingsButton = new JButton("Settings");

        homepagePanelLayout.gridx = 1;
        homepagePanelLayout.gridy = 6;

        settingsButton.setBounds(200,500,100,50);
        settingsButton.setSize(1000,500);
        homepagePanel.add(settingsButton);
        
        add(homepagePanel);

        
    }
}
