// The ArtistTableModel class defines how the table data is represented and managed in the GUI

import javax.swing.table.AbstractTableModel;
import java.util.*;

public class ArtistTableModel extends AbstractTableModel {
    private List<Artist> artists; // List of artists to be displayed
    private final String[] columnNames = {"Name", "Birth Year", "Death Year", "Nationality", "Works"}; // Table column names

    // Constructor that initializes the artist list
    public ArtistTableModel(List<Artist> artists) {
        this.artists = new ArrayList<>(artists);
    }

    // Updates the artist data in the table
    public void setData(List<Artist> artists) {
        this.artists = new ArrayList<>(artists);
        fireTableDataChanged();
    }

    // Returns the artist at a given row index
    public Artist getArtistAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < artists.size()) {
            return artists.get(rowIndex);
        }
        return null; // Return null if index is out of bounds
    }

    // Sorts the artist list by a specific column and direction (ascending or descending)
    public void sortByColumn(int columnIndex, boolean ascending) {
        Comparator<Artist> comparator = switch (columnIndex) {
            case 0 -> Comparator.comparing(Artist::getName, Comparator.nullsLast(Comparator.naturalOrder())); // Sort by name
            case 1 -> Comparator.comparingInt(Artist::getBirthYear); // Sort by birth year
            case 2 -> Comparator.comparing(Artist::getDeathYear, Comparator.nullsLast(Comparator.naturalOrder())); // Sort by death year, nulls last
            case 3 -> Comparator.comparing(Artist::getNationality, Comparator.nullsLast(Comparator.naturalOrder())); // Sort by nationality
            case 4 -> Comparator.comparingInt(Artist::getWorks); // Sort by number of works
            default -> throw new IllegalArgumentException("Invalid column index"); // Invalid column index
        };

        if (!ascending) {
            comparator = comparator.reversed(); // Reverse the order for descending
        }

        Collections.sort(artists, comparator); // Sort the list of artists
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return artists.size(); // Return the number of artists
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; // Return the number of columns
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; // Return the column name
    }

    // Return artist's name, birth year, death year, nationality, and number of works
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= artists.size()) {
            return null; // Prevent IndexOutOfBoundsException
        }

        Artist artist = artists.get(rowIndex); // Get the artist at the specified row
        return switch (columnIndex) {
            case 0 -> artist.getName();
            case 1 -> artist.getBirthYear();
            case 2 -> artist.getDeathYear() != null ? artist.getDeathYear() : " "; // Return empty string if null
            case 3 -> artist.getNationality() != null ? artist.getNationality() : " "; // Handle potential null values
            case 4 -> artist.getWorks();
            default -> null; // Return null for invalid column index
        };
    }
}
