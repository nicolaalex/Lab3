// Artist class with gender field

public class Artist {
    private String name;
    private int birthYear;
    private Integer deathYear;
    private String nationality;
    private int works;
    private String gender;  // Added gender field

    // Constructor
    public Artist(String name, int birthYear, Integer deathYear, String nationality, int works, String gender) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.works = works;
        this.gender = gender;  // Initialize gender
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public String getNationality() {
        return nationality;
    }

    public int getWorks() {
        return works;
    }

    public String getGender() {
        return gender;  // Getter for gender
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", nationality='" + nationality + '\'' +
                ", works=" + works +
                ", gender='" + gender + '\'' +
                '}';
    }
}
