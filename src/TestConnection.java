import java.sql.*;

class TestConnection {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/movie_db", "root", ""
            );
            System.out.println("Connected to MySQL successfully!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

