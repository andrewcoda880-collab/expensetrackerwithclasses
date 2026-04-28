import java.awt.*;
import java.util.EventObject;
import java.util.concurrent.Flow;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class SettingsTab extends JPanel {
        private JComboBox<String> notifsMenu;
        private JComboBox<String> chartMenu;
        private DefaultTableModel miscMenu;
        private JTable notifSettings;
        private JButton themeChange;

    public SettingsTab(){
        setLayout(new BorderLayout());
        setBackground(Constants.APP_COLOR);

        JLabel title = new JLabel("Setting$");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        add(title, BorderLayout.NORTH);

        JPanel notifPanel = new JPanel();
        TitledBorder notifBorder;
        notifBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Notification Settings");
        notifPanel.setLayout(new BorderLayout(10, 5));
        notifPanel.setBackground(Color.LIGHT_GRAY);
        notifPanel.setBorder(notifBorder);
        
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(2, 2, 4, 4));
        settingsPanel.setBackground(Constants.APP_COLOR);

        JLabel titleNotificationSettings = new JLabel("Enable Notifications");
        titleNotificationSettings.setFont(new Font("Arial", Font.BOLD, 14));
        notifsMenu = new JComboBox<>(new String[]{"Weekly", "Monthly", "Weekly/Monthly", "Off"});
        /*notifSettings = new JTable(notifsMenu);
        JScrollPane notifSettingsScrollPane = new JScrollPane(notifSettings);
        notifSettings.setGridColor(new Color(50, 50, 185));
        notifSettings.setBackground(Constants.APP_COLOR);*/
        titleNotificationSettings.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 185), 6));

        /*notifsMenu.addRow(new Object[]{"Enable notifications", "Weekly"});
        notifsMenu.addRow(new Object[]{"Chart Types", "Pie"});*/
        JLabel chartType = new JLabel("Chart Types");
        chartType.setFont(new Font("Arial", Font.BOLD, 14));
        chartMenu = new JComboBox<>(new String[]{"Pie", "Bar", "Line"});

        /*notifSettings.getColumnModel().getColumn(1).setCellEditor(new TableCellEditor() {
            private JComboBox<String> currentComboBox;
            
            @Override
            public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (row == 0) {
                    currentComboBox = new JComboBox<>(new String[]{"Weekly", "Monthly", "Weekly/Monthly", "Off"});
                } else if (row == 1) {
                    currentComboBox = new JComboBox<>(new String[]{"Pie", "Bar", "Line"});
                } else {
                    currentComboBox = new JComboBox<>();
                }
                currentComboBox.setSelectedItem(value);
                return currentComboBox;
            }

            @Override
            public Object getCellEditorValue() {
                return currentComboBox.getSelectedItem();
            }

            @Override
            public boolean isCellEditable(EventObject anEvent) { return true; }
            @Override
            public boolean shouldSelectCell(EventObject anEvent) { return true; }
            @Override
            public boolean stopCellEditing() { return true; }
            @Override
            public void cancelCellEditing() {}
            @Override
            public void addCellEditorListener(CellEditorListener l) {}
            @Override
            public void removeCellEditorListener(CellEditorListener l) {}

            /*@Override
            public boolean isCellEditable(EventObject anEvent) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'isCellEditable'");
            }

            @Override
            public boolean shouldSelectCell(EventObject anEvent) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'shouldSelectCell'");
            }

            @Override
            public void addCellEditorListener(CellEditorListener l) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addCellEditorListener'");
            }

            @Override
            public void removeCellEditorListener(CellEditorListener l) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'removeCellEditorListener'");
            }
        });*/
        //notifPanel.add(notifBorder);
        settingsPanel.add(titleNotificationSettings);
        settingsPanel.add(notifsMenu);
        settingsPanel.add(chartType);
        settingsPanel.add(chartMenu);
        notifPanel.add(settingsPanel);
        add(notifPanel, BorderLayout.CENTER);


        JLabel miscSettings = new JLabel("Light Mode:");
        miscSettings.setFont(new Font("Arial", Font.BOLD, 16));
        miscMenu = new DefaultTableModel(new Object[]{"Settings"}, 0);
        JTable miscSettingsTable = new JTable(miscMenu);
        JScrollPane miscSettingsScrollPane = new JScrollPane(miscSettingsTable);
        miscSettingsTable.setGridColor(new Color(50, 50, 185));
        miscSettingsTable.setBackground(Constants.APP_COLOR);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8));
        textPanel.setBackground(Color.LIGHT_GRAY);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5)); 
        sliderPanel.setBackground(Color.LIGHT_GRAY);
        
        JPanel themePanel = new JPanel();
        TitledBorder appBorder;
        appBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "App Settings");
        themePanel.setLayout(new BorderLayout(10, 0));
        themePanel.setBackground(Color.LIGHT_GRAY);
        themePanel.setBorder(appBorder);
        JSlider themeSlider = new JSlider(0, 1, 0);
        themeSlider.setPreferredSize(new Dimension(50, 25));

        textPanel.add(miscSettings);
        sliderPanel.add(themeSlider);
        themePanel.add(textPanel, BorderLayout.WEST);
        themePanel.add(sliderPanel, BorderLayout.EAST);
        add(themePanel, BorderLayout.SOUTH);



        //hey!
        //yo
        //commit test
        //heyo
        //starting at 10:58 I (steve) am now gona work on the settingstab
        
        
         
    }
}