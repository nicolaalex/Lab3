// The TablePanel class displays and manages the table of artists in the GUI

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TablePanel extends JPanel {
    private JTable table;
    private ArtistTableModel tableModel;
    private boolean[] sortAscending; // Sorting for each column (true for ascending, false for descending)
    private JTableHeader tableHeader;
    private DetailsPanel detailsPanel; // Reference to the details panel for updating selection

    public TablePanel(List<Artist> artists, DetailsPanel detailsPanel) {
        setLayout(new BorderLayout());
        this.detailsPanel = detailsPanel; // Store the details panel reference

        // Initialize table model with artist data and set up the table
        tableModel = new ArtistTableModel(artists);
        table = new JTable(tableModel);
        tableHeader = table.getTableHeader();

        // Enable column sorting when header is clicked
        tableHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columnIndex = table.columnAtPoint(e.getPoint()); // Identify clicked column
                toggleSort(columnIndex); // Toggle sorting order on column click
            }
        });

        // Initialize sorting order for each column
        sortAscending = new boolean[tableModel.getColumnCount()];
        updateColumnHeaders(-1); // Set no sorting initially

        // Add selection listener to update the details panel when a row is selected
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensure final selection
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Artist selectedArtist = tableModel.getArtistAt(selectedRow);
                    detailsPanel.updateDetails(selectedArtist);
                }
            }
        });

        // Add table to the panel with scroll support
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Returns the JTable instance for external access (ex. from the main class)
    public JTable getTable() {
        return table;
    }

    // Updates the data in the table
    public void updateData(List<Artist> artists) {
        tableModel.setData(artists);
    }

    // Toggles sorting order and updates the table based on the column clicked
    private void toggleSort(int columnIndex) {
        sortAscending[columnIndex] = !sortAscending[columnIndex];

        // Sort the table data based on the selected column and direction
        tableModel.sortByColumn(columnIndex, sortAscending[columnIndex]);
        updateColumnHeaders(columnIndex);
    }

    // Updates the column headers with sorting indicators (▲ or ▼)
    private void updateColumnHeaders(int sortedColumnIndex) {
        TableColumnModel columnModel = table.getColumnModel();

        // Loop through each column to update the header based on the sorting state
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            String columnName = tableModel.getColumnName(i);

            // Add sort direction indicator to the sorted column header
            if (i == sortedColumnIndex) {
                column.setHeaderValue(columnName + (sortAscending[i] ? " ▲" : " ▼"));
            } else {
                column.setHeaderValue(columnName);
            }
        }

        tableHeader.repaint();
    }
}
