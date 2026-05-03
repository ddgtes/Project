//temp code

public class Report {

    private int totalUsers;
    private int totalRecipes;
    private int totalInventoryItems;

    public Report(int totalUsers, int totalRecipes, int totalInventoryItems) {
        this.totalUsers = totalUsers;
        this.totalRecipes = totalRecipes;
        this.totalInventoryItems = totalInventoryItems;
    }

    @Override
    public String toString() {
        return "\n  [System Report]" +
                "\n  Total Users    : " + totalUsers +
                "\n  Total Recipes  : " + totalRecipes +
                "\n  Inventory Items: " + totalInventoryItems;
    }
}