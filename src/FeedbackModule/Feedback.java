package FeedbackModule;

public class Feedback {
    private int id;
    private int movieId;
    private String username;
    private float rating;
    private String comments;


    public int getId()
    {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
}
