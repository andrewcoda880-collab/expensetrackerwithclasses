
import java.awt.*;
import javax.swing.*;

public class LoginTab extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginTab() {

        setLayout(null);
        setBackground(Constants.APP_COLOR);

        // Title
        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(170, 60, 200, 40);
        add(title);

        // Username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 140, 100, 25);
        add(userLabel);

        // Username field
        usernameField = new JTextField();
        usernameField.setBounds(120, 165, 200, 30);
        add(usernameField);

        // Password label
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 210, 100, 25);
        add(passLabel);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(120, 235, 200, 30);
        add(passwordField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 290, 120, 35);
        add(loginButton);

        // Button action (simple test login)
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
            }
        });
    }
}