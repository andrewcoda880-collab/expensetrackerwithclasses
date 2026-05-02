import java.awt.*;
import javax.swing.*;

public class LoginTab extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public LoginTab(CardLayout cardLayout, JPanel cardPanel) {

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(null);
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setBounds(170, 60, 200, 40);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 140, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 165, 200, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(120, 210, 100, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 235, 200, 30);
        passwordField.setEchoChar('*');
        add(passwordField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(120, 265, 150, 20);
        showPassword.setBackground(Constants.APP_COLOR);
        add(showPassword);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 300, 120, 35);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 350, 120, 35);
        add(registerButton);

        JButton forgotPassword = new JButton("Forgot Password?");
        forgotPassword.setBounds(110, 400, 200, 25);
        forgotPassword.setBorderPainted(false);
        forgotPassword.setContentAreaFilled(false);
        forgotPassword.setFocusPainted(false);
        forgotPassword.setForeground(Color.BLUE);
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(forgotPassword);

        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());

        forgotPassword.addActionListener(e ->
            cardLayout.show(cardPanel, "FORGOT")
        );
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        User user = UserStore.users.get(username);

        if (user != null && user.password.equals(password)) {
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
            JOptionPane.showMessageDialog(this, "User already exists");
            return;
        }

        String[] questions = {
            "What is your pet's name?",
            "What is your mother's maiden name?",
            "What city were you born in?",
            "What was your first school?",
            "What is your favorite food?"
        };

        String selectedQuestion = (String) JOptionPane.showInputDialog(
                this,
                "Select a security question:",
                "Security Question",
                JOptionPane.QUESTION_MESSAGE,
                null,
                questions,
                questions[0]
        );

        String answer = JOptionPane.showInputDialog(this, "Enter your answer:");

        if (selectedQuestion == null || answer == null || answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Security question required");
            return;
        }

        UserStore.users.put(username, new User(password, selectedQuestion, answer));
        UserStore.saveUsers();

        JOptionPane.showMessageDialog(this, "User registered!");
    }
}