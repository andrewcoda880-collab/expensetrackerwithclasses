import java.awt.*;
import javax.swing.*;

public class NavigationBar extends JPanel {
    public NavigationBar(CardLayout layout, JPanel container) {
        JButton expenseTabButton = new JButton ("Expenses");
        JButton settingsTabButton = new JButton ("Settings");
        JButton graphsTabButton = new JButton ("Graphs");
        
        expenseTabButton.addActionListener(e -> layout.show(container, "EXPENSES"));
        graphsTabButton.addActionListener(e -> layout.show(container, "GRAPHS"));
        settingsTabButton.addActionListener(e -> layout.show(container, "SETTINGS"));

        add(expenseTabButton);
        add(graphsTabButton);
        add(settingsTabButton);
    }
}
