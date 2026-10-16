package movie.analytics;

import java.sql.*;
import java.util.Scanner;

public class AnalyticsModule {

    private Connection conn;
    private Scanner sc;

    public AnalyticsModule(Connection conn) {
        this.conn = conn;
        sc = new Scanner(System.in);
    }


    public void getHighestGrossingMovie() {
        try {
            String sql = "SELECT title, box_office FROM movies ORDER BY box_office DESC LIMIT 1";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println("\nHighest-Grossing Movie:");
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Box Office: ₹" + rs.getDouble("box_office"));
            } else {
                System.out.println("No movie data found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching highest-grossing movie: " + e);
        }
    }

    // Method 2: Top 10 by IMDb rating
    public void getTopRatedMovies() {
        try {
            String sql = "SELECT title, imdb_rating FROM movies ORDER BY imdb_rating DESC LIMIT 10";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n Top 10 Movies by IMDb Rating:");
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — IMDb: " + rs.getFloat("imdb_rating"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching top-rated movies: " + e);
        }
    }

    // Method 3: Movies by Decade
    public void getMoviesByDecade(String decade) {
        try {
            int start = Integer.parseInt(decade);
            int end = start + 9;

            String sql = "SELECT title, viewer_rating FROM movies WHERE year BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, end);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n🎞 Movies from " + decade + "s:");
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — Viewer Rating: " + rs.getFloat("viewer_rating"));
            }
        } catch (Exception e) {
            System.out.println("Error fetching movies by decade: " + e);
        }
    }

    public void showAnalyticsMenu() {
        int choice;
        do {
            System.out.println("Analytics & Reporting Menu:");
            System.out.println("1. Highest-Grossing Movie");
            System.out.println("2. Top 10 by IMDb Rating");
            System.out.println("3. Movies by Decade");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> getHighestGrossingMovie();
                case 2 -> getTopRatedMovies();
                case 3 -> {
                    System.out.print("Enter decade (e.g., 1990): ");
                    String decade = sc.nextLine();
                    getMoviesByDecade(decade);
                }
                case 0 -> System.out.println("Exiting Analytics Module...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }
}
