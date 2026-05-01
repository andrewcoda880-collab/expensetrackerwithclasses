import java.awt.*;
import javax.swing.*;

public class ForgotPasswordTab extends JPanel {

    private JTextField usernameField;
    private JTextField answerField;
    private JLabel questionLabel;

    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ForgotPasswordTab(CardLayout cardLayout, JPanel cardPanel) {

        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(null);
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Password Recovery");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(120, 60, 250, 30);
        add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(120, 120, 100, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 145, 200, 30);
        add(usernameField);

        JButton loadQuestion = new JButton("Load Question");
        loadQuestion.setBounds(120, 185, 200, 30);
        add(loadQuestion);

        questionLabel = new JLabel("");
        questionLabel.setBounds(120, 225, 300, 25);
        add(questionLabel);

        answerField = new JTextField();
        answerField.setBounds(120, 255, 200, 30);
        add(answerField);

        JButton submit = new JButton("Submit");
        submit.setBounds(120, 300, 200, 35);
        add(submit);

        JButton back = new JButton("Back");
        back.setBounds(120, 350, 200, 30);
        add(back);

        // ================= LOAD QUESTION =================
        loadQuestion.addActionListener(e -> {

            String username = usernameField.getText();

            User user = UserStore.users.get(username);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "User not found");
                return;
            }

            questionLabel.setText(user.securityQuestion);
        });

        // ================= SUBMIT ANSWER =================
        submit.addActionListener(e -> {

            String username = usernameField.getText();
            String answer = answerField.getText();

            User user = UserStore.users.get(username);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "User not found");
                return;
            }

            // check answer
            if (user.securityAnswer.equalsIgnoreCase(answer)) {

                String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");

                if (newPassword == null || newPassword.isEmpty()) {
                    return;
                }

                user.password = newPassword;
                UserStore.saveUsers();

                JOptionPane.showMessageDialog(this, "Password reset successful!");

                // go back to login
                cardLayout.show(cardPanel, "LOGIN");

            } else {
                JOptionPane.showMessageDialog(this, "Incorrect answer");
            }
        });

        // ================= BACK =================
        back.addActionListener(e ->
            cardLayout.show(cardPanel, "LOGIN")
        );
    }
}