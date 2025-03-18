import java.io.*;
import java.util.*;

public class ConsoleTest {
    public static void main(String[] args) {
        // Read the file containing artist data
        List<Artist> artists = readArtistsFromFile("src/artists.csv");

        // Run the console test with the loaded artists list
        consoleTest(artists);
    }

    // Console Test method as you described
    private static void consoleTest(List<Artist> artists) {
        // Print out the attributes of the first data item
        if (!artists.isEmpty()) {
            System.out.println("First artist: " + artists.get(0));
        }

        // Print out the attributes of the 10th item (if available)
        if (artists.size() >= 10) {
            System.out.println("10th artist: " + artists.get(9));
        } else {
            System.out.println("There are less than 10 artists in the data.");
        }

        // Display the total number of entries in the data
        System.out.println("Total number of artists: " + artists.size());
    }

    // Helper method to read artists from the CSV file
    private static List<Artist> readArtistsFromFile(String filename) {
        List<Artist> artists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Skip the header line
            br.readLine();

            // Read the rest of the lines and create Artist objects
            while ((line = br.readLine()) != null) {
                Artist artist = new Artist(line);
                artists.add(artist);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return artists;
    }
}
