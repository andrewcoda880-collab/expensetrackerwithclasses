import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class InvestmentGraphPanel extends JPanel {
    private List<Double> projectionValues = new ArrayList<>();

    public InvestmentGraphPanel() {
        setBackground(Color.WHITE);
    }

    public void setProjectionValues(List<Double> projectionValues) {
        this.projectionValues = new ArrayList<>(projectionValues);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int left = 45;
        int right = 20;
        int top = 20;
        int bottom = 35;

        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.drawLine(left, height - bottom, width - right, height - bottom);
        graphics2D.drawLine(left, top, left, height - bottom);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 11));
        graphics2D.drawString("Years", width / 2 - 15, height - 10);
        graphics2D.drawString("Value", 6, top + 10);

        if (projectionValues == null || projectionValues.size() < 2) {
            graphics2D.drawString("Enter values and project growth to view graph", 55, height / 2);
            graphics2D.dispose();
            return;
        }

        double maxValue = 0;
        for (double value : projectionValues) {
            maxValue = Math.max(maxValue, value);
        }
        if (maxValue <= 0) {
            graphics2D.dispose();
            return;
        }

        int graphWidth = width - left - right;
        int graphHeight = height - top - bottom;

        graphics2D.setColor(new Color(0, 102, 204));
        graphics2D.setStroke(new BasicStroke(2f));

        int previousX = left;
        int previousY = height - bottom - (int) ((projectionValues.get(0) / maxValue) * graphHeight);

        for (int i = 0; i < projectionValues.size(); i++) {
            int x = left + (int) ((i / (double) (projectionValues.size() - 1)) * graphWidth);
            int y = height - bottom - (int) ((projectionValues.get(i) / maxValue) * graphHeight);

            graphics2D.fillOval(x - 3, y - 3, 6, 6);
            if (i > 0) {
                graphics2D.drawLine(previousX, previousY, x, y);
            }

            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawString(String.valueOf(i), x - 3, height - 18);
            if (i == projectionValues.size() - 1) {
                graphics2D.drawString(String.format("$%.0f", projectionValues.get(i)), x - 25, y - 8);
            }
            graphics2D.setColor(new Color(0, 102, 204));

            previousX = x;
            previousY = y;
        }

        graphics2D.setColor(Color.GRAY);
        graphics2D.drawString("$0", 12, height - bottom + 5);
        graphics2D.drawString(String.format("$%.0f", maxValue), 8, top + 5);
        graphics2D.dispose();
    }
}