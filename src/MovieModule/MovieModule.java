package MovieModule;

import java.util.Scanner;

public class MovieModule {

    private MovieDAO movieDAO = new MovieDAO();


    public void showMovieMenu(String role) {
        Scanner sc = new Scanner(System.in);
        int choice=-1;

        if ("admin".equalsIgnoreCase(role)) {
            do {
                System.out.println("\n--- Movie Management (Admin) ---");
                System.out.println("1. Add Movie");
                System.out.println("2. View All Movies");
                System.out.println("3. Search Movie");
                System.out.println("4. Delete Movie");
                System.out.println("0. Back to Dashboard");
                System.out.print("Enter choice: ");
                if (!sc.hasNextInt()) {
                    sc.nextLine();
                    System.out.println("Invalid input!");
                    continue;
                }
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        Movie m = new Movie();
                        System.out.print("Title: ");
                        m.setTitle(sc.nextLine());

                        System.out.print("Language: ");
                        m.setLanguage(sc.nextLine());

                        System.out.print("Year: ");
                        m.setYear(sc.nextInt());
                        sc.nextLine();

                        System.out.print("Producer: ");
                        m.setProducer(sc.nextLine());

                        System.out.print("Director: ");
                        m.setDirector(sc.nextLine());

                        System.out.print("Budget: ");
                        m.setBudget(sc.nextDouble());
                        sc.nextLine();

                        System.out.print("Box Office: ");
                        m.setBoxOffice(sc.nextDouble());
                        sc.nextLine();

                        System.out.print("Viewer Rating: ");
                        m.setViewerRating(sc.nextFloat());
                        sc.nextLine();

                        System.out.print("IMDB Rating: ");
                        m.setImdbRating(sc.nextFloat());
                        sc.nextLine();

                        System.out.print("Genre: ");
                        m.setGenre(sc.nextLine());

                        System.out.print("Feedback: ");
                        m.setFeedback(sc.nextLine());

                        if (movieDAO.addMovie(m)) {
                            System.out.println("Movie added successfully!");
                        } else {
                            System.out.println("Failed to add movie.");
                        }
                    }
                    case 2 -> {
                        Movie[] allMovies = movieDAO.getAllMovies();
                        if (allMovies != null) {
                            for (Movie movie : allMovies) {
                                System.out.println(movie.getId() + " | " + movie.getTitle()
                                        + " | " + movie.getDirector() + " | " + movie.getYear());
                            }
                        } else {
                            System.out.println("No movies found.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter search keyword: ");
                        String keyword = sc.nextLine();
                        Movie[] result = movieDAO.searchMovies(keyword);
                        if (result != null) {
                            for (Movie movie : result) {
                                System.out.println(movie.getId() + " | " + movie.getTitle() +
                                        " | " + movie.getDirector() + " | " + movie.getYear());
                            }
                        } else {
                            System.out.println("No results found.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Movie ID to delete: ");
                        if (!sc.hasNextInt()) {
                            sc.nextLine();
                            System.out.println("Invalid input!");
                        } else {
                            int deleteId = sc.nextInt();
                            sc.nextLine();
                            if (movieDAO.deleteMovie(deleteId)) {
                                System.out.println("Movie deleted successfully!");
                            } else {
                                System.out.println("Failed to delete movie.");
                            }
                        }
                    }
                    case 0 -> System.out.println("Returning to dashboard...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 0);
        } else {
            do {
                System.out.println("\n--- Movie (Viewer) ---");
                System.out.println("1. Search Movie");
                System.out.println("0. Back to Dashboard");
                System.out.print("Enter choice: ");
                if (!sc.hasNextInt()) {
                    sc.nextLine();
                    System.out.println("Invalid input!");
                    continue;
                }
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter search keyword: ");
                        String keyword = sc.nextLine();
                        Movie[] result = movieDAO.searchMovies(keyword);
                        if (result != null) {
                            for (Movie movie : result) {
                                System.out.println(movie.getId() + " | " + movie.getTitle() +
                                        " | " + movie.getDirector() + " | " + movie.getYear());
                            }
                        } else {
                            System.out.println("No results found.");
                        }
                    }
                    case 0 -> System.out.println("Returning to dashboard...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 0);
        }
    }
}
