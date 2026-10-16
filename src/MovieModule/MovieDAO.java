package MovieModule;

import java.sql.*;

public class MovieDAO {
    private Connection conn;

    public MovieDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public boolean addMovie(Movie m) {
        try {
            String sql = "INSERT INTO movies(title, language, year, producer, director, budget, box_office, viewer_rating, imdb_rating, genre, feedback) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getLanguage());
            ps.setInt(3, m.getYear());
            ps.setString(4, m.getProducer());
            ps.setString(5, m.getDirector());
            ps.setDouble(6, m.getBudget());
            ps.setDouble(7, m.getBoxOffice());
            ps.setFloat(8, m.getViewerRating());
            ps.setFloat(9, m.getImdbRating());
            ps.setString(10, m.getGenre());
            ps.setString(11, m.getFeedback());
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public Movie[] getAllMovies() {
        Movie[] movies = null;
        try {
            Statement stCount = conn.createStatement();
            ResultSet rsCount = stCount.executeQuery("SELECT COUNT(*) FROM movies");
            int count = 0;
            if (rsCount.next()) count = rsCount.getInt(1);

            movies = new Movie[count];

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies");
            int i = 0;
            while (rs.next() && i < count) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setYear(rs.getInt("year"));
                movies[i++] = m;
            }
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return movies;
    }

    public boolean updateMovie(Movie m) {
        try {
            String sql = "UPDATE movies SET title=?, language=?, year=?, producer=?, director=?, budget=?, box_office=?, viewer_rating=?, imdb_rating=?, genre=?, feedback=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, m.getTitle());
            ps.setString(2, m.getLanguage());
            ps.setInt(3, m.getYear());
            ps.setString(4, m.getProducer());
            ps.setString(5, m.getDirector());
            ps.setDouble(6, m.getBudget());
            ps.setDouble(7, m.getBoxOffice());
            ps.setFloat(8, m.getViewerRating());
            ps.setFloat(9, m.getImdbRating());
            ps.setString(10, m.getGenre());
            ps.setString(11, m.getFeedback());
            ps.setInt(12, m.getId());
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public boolean deleteMovie(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM movies WHERE id=?");
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    public Movie[] searchMovies(String keyword) {
        Movie[] movies = null;
        try {
            Statement stCount = conn.createStatement();
            ResultSet rsCount = stCount.executeQuery("SELECT COUNT(*) FROM movies WHERE title LIKE '%" + keyword + "%'");
            int count = 0;
            if(rsCount.next()) count = rsCount.getInt(1);

            movies = new Movie[count];
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM movies WHERE title LIKE '%" + keyword + "%'");
            int i = 0;
            while(rs.next() && i < count) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setTitle(rs.getString("title"));
                m.setDirector(rs.getString("director"));
                m.setYear(rs.getInt("year"));
                movies[i++] = m;
            }
        } catch(Exception e) {
            System.out.println("Error: " + e);
        }
        return movies;
    }
}



