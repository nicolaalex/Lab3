import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

class TablePanel extends JPanel {
    private JTable table;
    private List<Artist> artists;

    public TablePanel(List<Artist> artists) {
        this.artists = artists;
        setLayout(new BorderLayout());
        String[] columnNames = {"Name", "Birth Year", "Death Year", "Nationality", "Works"};
        Object[][] data = artists.stream()
                .map(a -> new Object[]{a.getName(), a.getBirthYear(), a.getDeathYear(), a.getNationality(), a.getWorks()})
                .toArray(Object[][]::new);
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public JTable getTable() { return table; }
}