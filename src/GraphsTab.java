import java.awt.*;
import javax.swing.*;

public class GraphsTab extends JPanel {
    
    public GraphsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Graphs");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.CENTER);
    }
}
