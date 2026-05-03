import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static List<Recipe> recipes = new ArrayList<>();
    static List<IngredientItem> inventory = new ArrayList<>();
    static User loggedInUser = null;

    public static void main(String[] args) {
        // Seed some data
        users.add(new User(1, "john_doe", "pass1234", "john@email.com"));
        users.add(new User(2, "mary_jane", "qwerty", "mary@email.com"));
        recipes.add(new Recipe("Spaghetti Carbonara"));
        recipes.add(new Recipe("Chicken Adobo"));
        inventory.add(new IngredientItem("Tomato"));
        inventory.add(new IngredientItem("Garlic"));
        inventory.add(new IngredientItem("Chicken"));

        // Pre-create admin (not in users list to keep separation)
        Admin admin = new Admin(99, "admin", "admin123", "admin@system.com", 1);

        System.out.println("=========================================");
        System.out.println("   RECIPE MANAGEMENT SYSTEM");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            if (loggedInUser == null) {
                showGuestMenu();
                int choice = readInt();
                switch (choice) {
                    case 1 -> registerUser();
                    case 2 -> loginUser(admin);
                    case 3 -> { System.out.println("Goodbye!"); running = false; }
                    default -> System.out.println("[!] Invalid option.");
                }
            } else if (loggedInUser instanceof Admin a) {
                showAdminMenu(a);
                int choice = readInt();
                switch (choice) {
                    case 1 -> a.manageUsers(users);
                    case 2 -> { Report r = a.generateReport(users, recipes, inventory); System.out.println(r); }
                    case 3 -> deleteRecipeMenu(a);
                    case 4 -> { List<IngredientItem> inv = a.viewAllInventory(inventory); printInventory(inv); }
                    case 5 -> { loggedInUser = null; System.out.println("[✓] Logged out."); }
                    default -> System.out.println("[!] Invalid option.");
                }
            } else {
                showUserMenu(loggedInUser);
                int choice = readInt();
                switch (choice) {
                    case 1 -> updateProfileMenu(loggedInUser);
                    case 2 -> viewFavoritesMenu(loggedInUser);
                    case 3 -> viewRecipes();
                    case 4 -> { loggedInUser = null; System.out.println("[✓] Logged out."); }
                    default -> System.out.println("[!] Invalid option.");
                }
            }
            System.out.println();
        }

        scanner.close();
    }

    static void showGuestMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("  MAIN MENU  (not logged in)");
        System.out.println("-----------------------------------------");
        System.out.println("  [1] Register");
        System.out.println("  [2] Login");
        System.out.println("  [3] Exit");
        System.out.print("  Choice: ");
    }

    static void showUserMenu(User u) {
        System.out.println("-----------------------------------------");
        System.out.println("  USER MENU  | Hello, " + u.getUsername() + "!");
        System.out.println("-----------------------------------------");
        System.out.println("  [1] Update profile");
        System.out.println("  [2] View favorites");
        System.out.println("  [3] Browse recipes");
        System.out.println("  [4] Logout");
        System.out.print("  Choice: ");
    }

    static void showAdminMenu(Admin a) {
        System.out.println("-----------------------------------------");
        System.out.println("  ADMIN MENU  | Level " + a.getAdminLevel());
        System.out.println("-----------------------------------------");
        System.out.println("  [1] Manage users");
        System.out.println("  [2] Generate report");
        System.out.println("  [3] Delete a recipe");
        System.out.println("  [4] View all inventory");
        System.out.println("  [5] Logout");
        System.out.print("  Choice: ");
    }

    static void registerUser() {
        System.out.println("\n--- REGISTER ---");
        System.out.print("  Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("  Password: ");
        String password = scanner.nextLine().trim();
        System.out.print("  Email: ");
        String email = scanner.nextLine().trim();

        // Check duplicate username
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                System.out.println("[!] Username already taken.");
                return;
            }
        }

        int newId = users.size() + 1;
        User newUser = new User(newId, username, password, email);
        newUser.register();
        users.add(newUser);
    }

    static void loginUser(Admin admin) {
        System.out.println("\n--- LOGIN ---");
        System.out.print("  Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("  Password: ");
        String password = scanner.nextLine().trim();

        // Check admin credentials first
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            admin.login();
            loggedInUser = admin;
            return;
        }

        // Check regular users
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                u.login();
                loggedInUser = u;
                return;
            }
        }

        System.out.println("[!] Invalid username or password.");
    }

    static void updateProfileMenu(User u) {
        System.out.println("\n--- UPDATE PROFILE ---");
        System.out.print("  New username (current: " + u.getUsername() + "): ");
        String newUsername = scanner.nextLine().trim();
        System.out.print("  New email (current: " + u.getEmail() + "): ");
        String newEmail = scanner.nextLine().trim();

        if (!newUsername.isEmpty() && !newEmail.isEmpty()) {
            u.updateProfile(newUsername, newEmail);
        } else {
            System.out.println("[!] Fields cannot be empty. Profile not updated.");
        }
    }

    static void viewFavoritesMenu(User u) {
        System.out.println("\n--- FAVORITES ---");
        List<Recipe> favs = u.viewFavorites();
        if (favs.isEmpty()) {
            System.out.println("  No favorites yet.");
        } else {
            for (Recipe r : favs) {
                System.out.println("  - " + r.getTitle());
            }
        }
    }

    static void viewRecipes() {
        System.out.println("\n--- RECIPES ---");
        if (recipes.isEmpty()) {
            System.out.println("  No recipes available.");
        } else {
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + recipes.get(i).getTitle());
            }
        }
    }

    static void deleteRecipeMenu(Admin a) {
        System.out.println("\n--- DELETE RECIPE ---");
        if (recipes.isEmpty()) {
            System.out.println("  No recipes to delete.");
            return;
        }
        viewRecipes();
        System.out.print("  Enter recipe number to delete: ");
        int idx = readInt() - 1;
        if (idx >= 0 && idx < recipes.size()) {
            Recipe toDelete = recipes.get(idx);
            a.deleteRecipe(toDelete);
            recipes.remove(idx);
        } else {
            System.out.println("[!] Invalid selection.");
        }
    }

    static void printInventory(List<IngredientItem> inv) {
        System.out.println("\n--- INVENTORY ---");
        if (inv.isEmpty()) {
            System.out.println("  No items in inventory.");
        } else {
            for (int i = 0; i < inv.size(); i++) {
                System.out.println("  [" + (i + 1) + "] " + inv.get(i).getName());
            }
        }
    }

    static int readInt() {
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}