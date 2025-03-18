// The Artist class represents an individual artist with details such as name, birth year, death year, nationality, works, and gender

import java.util.Scanner;

public class Artist {
    private String name;
    private int birthYear;
    private Integer deathYear;
    private String nationality;
    private int works;
    private String gender;

    // Constructor to initialize the artist object from CSV data
    public Artist(String name, int birthYear, Integer deathYear, String nationality, int works, String gender) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.works = works;
        this.gender = gender;
    }

    // Constructor that takes a CSV line and parses it
    public Artist(String csvLine) {
        Scanner scanner = new Scanner(csvLine);
        scanner.useDelimiter(",");
        this.name = scanner.next().replaceAll("\"", "");  // Remove quotes
        this.birthYear = Integer.parseInt(scanner.next());
        this.deathYear = scanner.hasNext() && !scanner.next().equals("null") ? Integer.parseInt(scanner.next()) : null;
        this.nationality = scanner.next().replaceAll("\"", "");
        this.works = Integer.parseInt(scanner.next());
        this.gender = scanner.next().replaceAll("\"", "");
    }

    // Getters (same as before)
    public String getName() { return name; }
    public int getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public String getNationality() { return nationality; }
    public int getWorks() { return works; }
    public String getGender() { return gender; }

    // Updated toString method for desired output format
    @Override
    public String toString() {
        return name + ", " + birthYear + ", " + (deathYear != null ? deathYear : "") + ", " + nationality + ", " + works + ", " + gender;
    }
}
