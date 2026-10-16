package FeedbackModule;

import java.sql.*;

public class FeedbackDAO {
    private Connection conn;

    public FeedbackDAO(Connection conn) {
        this.conn = conn;
    }


    public boolean addFeedback(Feedback fb) {
        try {
            String sql = "INSERT INTO feedback(movie_id, username, rating, comments) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fb.getMovieId());
            ps.setString(2, fb.getUsername());
            ps.setFloat(3, fb.getRating());
            ps.setString(4, fb.getComments());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }


    public void printFeedbackByMovie(int movieId) {
        try {
            String sql = "SELECT * FROM feedback WHERE movie_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Reviewer: " + rs.getString("username") + " | Rating: " + rs.getFloat("rating"));
                System.out.println("Comment: " + rs.getString("comments"));
                System.out.println("-----------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
