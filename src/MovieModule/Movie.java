package MovieModule;

public class Movie {
    private int id;
    private String title, language, producer, director, genre, feedback;
    private int year;
    private double budget, boxOffice;
    private float viewerRating, imdbRating;

    public Movie() {}

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getProducer() { return producer; }
    public void setProducer(String producer) { this.producer = producer; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public double getBoxOffice() { return boxOffice; }
    public void setBoxOffice(double boxOffice) { this.boxOffice = boxOffice; }

    public float getViewerRating() { return viewerRating; }
    public void setViewerRating(float viewerRating) { this.viewerRating = viewerRating; }

    public float getImdbRating() { return imdbRating; }
    public void setImdbRating(float imdbRating) { this.imdbRating = imdbRating; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
