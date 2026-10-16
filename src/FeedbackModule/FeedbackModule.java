package FeedbackModule;
import java.sql.Connection;
import java.util.Scanner;

public class FeedbackModule {
    private FeedbackDAO dao;
    private Scanner sc;

    public FeedbackModule(Connection conn) {
        dao = new FeedbackDAO(conn);
        sc = new Scanner(System.in);
    }

    // Menu for feedback operations
    public void showFeedbackMenu() {
        int choice;
        do {
            System.out.println("\n==== FEEDBACK MENU ====");
            System.out.println("1. Add Feedback");
            System.out.println("2. View Feedback for a Movie");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addFeedback();
                case 2 -> viewFeedback();
                case 0 -> System.out.println("Exiting Feedback Module...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }


    private void addFeedback() {
        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Your Name: ");
        String username = sc.nextLine();

        System.out.print("Enter Rating (1–10): ");
        float rating = sc.nextFloat();
        sc.nextLine();

        System.out.print("Enter Comments: ");
        String comments = sc.nextLine();

        Feedback fb = new Feedback();
        fb.setMovieId(movieId);
        fb.setUsername(username);
        fb.setRating(rating);
        fb.setComments(comments);

        if (dao.addFeedback(fb)) {
        System.out.println("Feedback added successfully!");
        }
        else {
            System.out.println("Failed to add feedback.");
        }
    }

    private void viewFeedback() {
        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();
        sc.nextLine();

        dao.printFeedbackByMovie(movieId);
    }



}
