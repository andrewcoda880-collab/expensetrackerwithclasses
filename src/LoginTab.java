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

        // Register button (NEW)
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 340, 120, 35);
        add(registerButton);

        // Events
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());
    }

        // Event
        loginButton.addActionListener(e -> handleLogin());
    }

    // ===================== LOGIC METHODS =====================

    private void handleLogin() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        if (authenticate(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login");
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter username and password");
            return;
        }

        if (UserStore.users.containsKey(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists");
            return;
        }

        UserStore.users.put(username, password);

        UserStore.saveUsers();
        JOptionPane.showMessageDialog(this, "User registered! You can now log in.");
    }

    private boolean authenticate(String username, char[] password) {
        String storedPassword = UserStore.users.get(username);
        return storedPassword != null && storedPassword.equals(String.valueOf(password));
    private boolean authenticate(String username, char[] password) {
        return username.equals("admin") && String.valueOf(password).equals("1234");
    }
}