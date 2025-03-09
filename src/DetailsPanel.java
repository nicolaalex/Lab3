import javax.swing.*;
import java.awt.*;

class DetailsPanel extends JPanel {
    private JLabel detailsLabel;

    public DetailsPanel(JTable table) {
        setLayout(new BorderLayout());
        detailsLabel = new JLabel("Select an artist to see details");
        add(detailsLabel, BorderLayout.CENTER);
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                detailsLabel.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
    }
}