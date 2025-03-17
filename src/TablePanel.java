// TablePanel to display the artists' data in a JTable

import javax.swing.*;
import java.awt.*;
import java.util.List;

class TablePanel extends JPanel {
    private JTable table;
    private ArtistTableModel tableModel;

    public TablePanel(List<Artist> artists) {
        setLayout(new BorderLayout());
        tableModel = new ArtistTableModel(artists);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Update the table data when filters are applied
    public void updateData(List<Artist> newArtists) {
        tableModel.setArtists(newArtists);
        tableModel.fireTableDataChanged();  // Refresh the table
    }
}
