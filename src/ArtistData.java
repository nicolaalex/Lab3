// Console Test that prints the number of artists and first and tenth artist.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtistData {
    public static void main(String[] args) {
        List<Artist> artists = new ArrayList<>();
        String filePath = "src/femaleartists.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                // If the line has 6 columns, try to parse it as an artist
                if (data.length == 6) {
                    try {
                        String name = data[0].trim();
                        String nationality = data[1].trim();
                        int birthYear = Integer.parseInt(data[2].trim());
                        int deathYear = Integer.parseInt(data[3].trim());
                        String gender = data[4].trim();
                        int works = Integer.parseInt(data[5].trim());

                        artists.add(new Artist(name, birthYear, deathYear, nationality, works, gender));
                    } catch (NumberFormatException e) {
                        // If there is a number format error, add an "invalid" artist with default values
                        artists.add(new Artist(data[0].trim(), 0, 0, data[1].trim(), 0, data[4].trim()));
                    }
                } else {
                    // If the line has an incorrect number of columns, add an "invalid" artist with default values
                    artists.add(new Artist("Invalid Data", 0, 0, "Unknown", 0, "Unknown"));
                }
            }



            // Display first and 10th artist
            if (!artists.isEmpty()) {
                System.out.println("Artist Name | Birth Year | Death Year | Nationality | Works | Gender " );
                System.out.println("First artist: " + artists.get(0));  // Calls the updated toString method
                if (artists.size() >= 10) {
                    System.out.println("10th artist: " + artists.get(9));  // Calls the updated toString method
                }
            }

            // Display total number of artists (including invalid ones)
            System.out.println("Total number of artists: " + artists.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
