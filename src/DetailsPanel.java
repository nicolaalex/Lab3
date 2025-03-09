/*
// DetailsPanel that shows details of a selected artist

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class DetailsPanel extends JPanel {
    private JLabel detailsLabel;

    public DetailsPanel(JTable table) {
        setLayout(new BorderLayout());
        detailsLabel = new JLabel("Select an artist to see details");
        add(detailsLabel, BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    detailsLabel.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
                }
            }
        });
    }
}
*/