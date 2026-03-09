import java.util.Scanner;

abstract class Item {
    String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void show();
}

class Laptop extends Item {
    public Laptop(String name) {
        super(name);
    }

    public void show() {
        System.out.println("Item Type: Laptop");
        System.out.println("Model: " + name);
    }
}

class Ball extends Item {
    public Ball(String name) {
        super(name);
    }

    public void show() {
        System.out.println("Item Type: Sports Ball");
        System.out.println("Brand: " + name);
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Student extends Person {
    public Student(String name) {
        super(name);
    }
}

class Teacher extends Person {
    public Teacher(String name) {
        super(name);
    }
}

class BorrowSystem {
    public void process(Person person, Item item) {
        System.out.println("\n----- Receipt -----");
        System.out.println("User: " + person.getName());
        item.show();
        System.out.println("Status: Successfully Borrowed");
    }
}

class Menu {
    public void display() {
        System.out.println("\nSelect Item to Borrow");
        System.out.println("1. Laptop");
        System.out.println("2. Basketball");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BorrowSystem system = new BorrowSystem();
        Menu menu = new Menu();

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Student user = new Student(name);

        menu.display();
        System.out.print("Enter choice (1-2): ");
        int choice = input.nextInt();
        input.nextLine();

        Item selectedItem;

        if (choice == 1) {
            System.out.print("Enter Laptop Brand: ");
            String brand = input.nextLine();
            selectedItem = new Laptop(brand);
        } else {
            System.out.print("Enter Ball Brand: ");
            String brand = input.nextLine();
            selectedItem = new Ball(brand);
        }

        system.process(user, selectedItem);

        input.close();
    }
}