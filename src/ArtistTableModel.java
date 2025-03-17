import javax.swing.table.AbstractTableModel;
import java.util.List;

// TableModel to handle the artist data for JTable

class ArtistTableModel extends AbstractTableModel {
    private List<Artist> artists;
    private String[] columnNames = {"Name", "Country", "Birth Year", "Death Year", "Gender", "Works"};

    public ArtistTableModel(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public int getRowCount() {
        return artists.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artist artist = artists.get(rowIndex);
        switch (columnIndex) {
            case 0: return artist.getName();
            case 1: return artist.getNationality();
            case 2: return artist.getBirthYear();
            case 3: return artist.getDeathYear();
            case 4: return artist.getGender();
            case 5: return artist.getWorks();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Set new artist data when filters are applied
    public void setArtists(List<Artist> artists) {
        this.artists = artists;


