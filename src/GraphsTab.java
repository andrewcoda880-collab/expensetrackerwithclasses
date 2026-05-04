import java.awt.*;
import javax.swing.*;

public class GraphsTab extends JPanel implements ThemeListener {
    
    public GraphsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);
        ThemeManager.register(this);

        JLabel title = new JLabel("Graphs");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.CENTER);
    }

        private void newTheme(Component c) {
        c.setBackground(Constants.APP_COLOR);

    }
    @Override
    public void onThemeChanged() {
        newTheme(this);
        revalidate();
        repaint();
    }
}
