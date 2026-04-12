import java.awt.*;
import javax.swing.*;

public class NavigationBar extends JPanel {
    public NavigationBar(CardLayout layout, JPanel container) {
        JButton homeTabButton = new JButton("Home");
        JButton expenseTabButton = new JButton ("Expenses");
        JButton settingsTabButton = new JButton ("Settings");
        JButton graphsTabButton = new JButton ("Graphs");
        JButton loginTabButton = new JButton ("Login");
        
        homeTabButton. addActionListener(e -> layout.show(container, "HOME"));
        expenseTabButton.addActionListener(e -> layout.show(container, "EXPENSES"));
        graphsTabButton.addActionListener(e -> layout.show(container, "GRAPHS"));
        settingsTabButton.addActionListener(e -> layout.show(container, "SETTINGS"));
        loginTabButton.addActionListener(e -> layout.show(container, "LOGIN"));

        add(homeTabButton);
        add(expenseTabButton);
        add(graphsTabButton);
        add(settingsTabButton);
        add(loginTabButton);
    }
}
