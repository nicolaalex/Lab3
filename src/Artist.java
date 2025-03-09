// Represents an artist with details such as name, birth year, death year, country, and number of artworks

public class Artist {
    private String name;
    private int birthYear;
    private Integer deathYear;
    private String nationality;
    private int works;

    // Constructor to initialize the Artist object with provided values.
    public Artist(String name, int birthYear, Integer deathYear, String nationality, int works) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.works = works;
    }

    // Getters for each field to retrieve artist info
    public String getName() { return name; }
    public int getBirthYear() { return birthYear; }
    public Integer getDeathYear() { return deathYear; }
    public String getNationality() { return nationality; }
    public int getWorks() { return works; }

    @Override
    public String toString() {
        return name + " (" + birthYear + " - " + (deathYear != null ? deathYear : "present") + ") | " + nationality + " | Works: " + works;
    }
}
