package moviemain;

import LoginModule.*;
import MovieModule.*;
import movie.analytics.*;
import FeedbackModule.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MovieRecordSystem {
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/movie_db", "root", ""
            );

            Scanner sc = new Scanner(System.in);

            UserDAO userDao = new UserDAO();
            Login login = new Login(userDao, sc);
            MovieModule movieModule = new MovieModule();
            AnalyticsModule analyticsModule = new AnalyticsModule(conn);
            FeedbackModule feedbackModule = new FeedbackModule(conn);

            User loggedInUser = null;
            int choice;

            do {
                if (loggedInUser == null) {
                    System.out.println("\n==== MOVIE RECORD SYSTEM ====");
                    System.out.println("1. Login");
                    System.out.println("2. Register (viewer)");
                    System.out.println("0. Exit");
                    System.out.print("Enter choice: ");
                    if (!sc.hasNextInt()) break;
                    choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {
                        case 1 -> {
                            loggedInUser = login.LoginUser();
                        }
                        case 2 -> login.registerUser();
                        case 0 -> System.out.println("Exiting system...");
                        default -> System.out.println("Invalid choice!");
                    }
                } else {
                    // role-specific dashboard
                    String role = loggedInUser.getRole();
                    System.out.println("\n==== DASHBOARD (" + role.toUpperCase() + ") ====");
                    if ("admin".equalsIgnoreCase(role)) {
                        System.out.println("1. Movie Management (Add/View/Search/Delete)");
                        System.out.println("2. Analytics & Reports");
                        System.out.println("3. Feedback & Reviews (View)");
                        System.out.println("4. Logout");
                        System.out.print("Enter choice: ");
                        if (!sc.hasNextInt()) break;
                        choice = sc.nextInt();
                        sc.nextLine();

                        switch (choice) {
                            case 1 -> movieModule.showMovieMenu(role);
                            case 2 -> analyticsModule.showAnalyticsMenu();
                            case 3 -> feedbackModule.showFeedbackMenu(); // admin can view feedback
                            case 4 -> {
                                System.out.println("Logged out successfully.");
                                loggedInUser = null;
                            }
                            default -> System.out.println("Invalid choice!");
                        }
                    } else { // viewer
                        System.out.println("1. Search Movie");
                        System.out.println("2. Feedback & Reviews (Add/View)");
                        System.out.println("3. Analytics & Reports (View Only)");
                        System.out.println("4. Logout");
                        System.out.print("Enter choice: ");
                        if (!sc.hasNextInt()) break;
                        choice = sc.nextInt();
                        sc.nextLine();

                        switch (choice) {
                            case 1 -> movieModule.showMovieMenu(role);
                            case 2 -> feedbackModule.showFeedbackMenu();
                            case 3 -> analyticsModule.showAnalyticsMenu();
                            case 4 -> {
                                System.out.println("Logged out successfully.");
                                loggedInUser = null;
                            }
                            default -> System.out.println("Invalid choice!");
                        }
                    }
                }
            } while (true);

            sc.close();
            conn.close();
            System.out.println("Thank you for using Movie Record System!");

        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }
}
