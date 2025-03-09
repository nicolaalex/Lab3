class Artist {
    private String name;
    private int birthYear;
    private Integer deathYear;
    private String nationality;
    private int works;

    public Artist(String name, int birthYear, Integer deathYear, String nationality, int works) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.nationality = nationality;
        this.works = works;
    }

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