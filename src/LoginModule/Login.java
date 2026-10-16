package LoginModule;

import java.util.Scanner;

public class Login {

    private UserDAO userDao;
    private Scanner sc;

    public Login(UserDAO userDao, Scanner sc) {
        this.userDao = userDao;
        this.sc = sc;
    }

    // returns the logged-in User (or null)
    public User LoginUser() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = userDao.validateUser(username, password);
        if (user != null) {
            System.out.println("Login successful! Role: " + user.getRole());
            return user;
        } else {
            System.out.println("Invalid username or password!");
            return null;
        }
    }

    public void registerUser() {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();
        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        if (userDao.registerUser(username, password, "viewer")) {
            System.out.println("User registered successfully as viewer!");
        } else {
            System.out.println("Registration failed!");
        }
    }
}
