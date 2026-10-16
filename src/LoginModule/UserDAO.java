package LoginModule;

import java.sql.*;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/movie_db", "root", ""
            );
        } catch (Exception e) {
            System.out.println("Database connection error: " + e);
        }
    }

    public boolean registerUser(String username, String password, String role) {
        try {
            String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e);
            return false;
        }
    }


    public User validateUser(String username, String password) {
        try {
            String query = "SELECT id, username, password, role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                String role = rs.getString("role");
                return new User(id, user, pass, role);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error validating user: " + e);
            return null;
        }
    }
}

