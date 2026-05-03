import java.util.List;

public class Admin extends User {

    private int adminLevel;

    public Admin(int userId, String username, String password, String email, int adminLevel) {
        super(userId, username, password, email);
        this.adminLevel = adminLevel;
    }

    public void manageUsers(List<User> users) {
        System.out.println("\n  [Users in system]");
        if (users.isEmpty()) {
            System.out.println("  No users registered.");
        } else {
            for (User u : users) {
                System.out.println("  " + u);
            }
        }
    }

    public Report generateReport(List<User> users, List<Recipe> recipes, List<IngredientItem> inventory) {
        return new Report(users.size(), recipes.size(), inventory.size());
    }

    public void deleteRecipe(Recipe recipe) {
        System.out.println("Recipe '" + recipe.getTitle() + "' has been deleted.");
    }

    public List<IngredientItem> viewAllInventory(List<IngredientItem> inventory) {
        System.out.println("Fetching all inventory items...");
        return inventory;
    }

    public int getAdminLevel()              { return adminLevel; }
    public void setAdminLevel(int level)    { this.adminLevel = level; }

    @Override
    public String toString() {
        return "Admin{id=" + getUserId() + ", username='" + getUsername() + "', level=" + adminLevel + "}";
    }
}