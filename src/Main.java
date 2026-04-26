import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) throws Exception {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            new App().start();
        });
    }
}
