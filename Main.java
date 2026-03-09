import java.util.Scanner;

abstract class Item { //1
    String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void show();
}

class Laptop extends Item { //2
    public Laptop(String name) {
        super(name);
    }

    public void show() {
        System.out.println("Item Type: Laptop");
        System.out.println("Model: " + name);
    }
}

class Ball extends Item { //3
    public Ball(String name) {
        super(name);
    }

    public void show() {
        System.out.println("Item Type: Sports Ball");
        System.out.println("Brand: " + name);
    }
}

class MakeUp extends Item { //4
    public MakeUp(String name) {
        super(name);
    }
    
    public void show() {
        System.out.println("Item Type: Lipstick");
        System.out.println("Brand: " + name);
    }
}


class Person { //5
    private String person;

    public Person(String person) {
        this.person = person;
    }

    public String getName() {
        return person;
    }
}

class Name extends Person { //6
    public Name(String name) {
        super(name);
    }
}

class Gender { //7
    private String gender;
    
    public Gender(String gender) {
        this.gender = gender;
    }
    
    public String getGender() {
        return gender;
    }
}
class Male extends Gender { //8
    public Male(String gender) {
        super(gender);
    }
}

class Female extends Gender { //9
    public Female(String gender) {
        super(gender);
    }
}



class Age { //10
    private int age;
    
    public Age(int age) {
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
}

class BorrowSystem { //11
    public void process(Person person, Item item, Gender gender, Age age) {
        System.out.println("\n----- Receipt -----");
        System.out.println("User: " + person.getName());
        System.out.println("Gender: " + gender.getGender());
        System.out.println("Age: " + age.getAge());
        item.show();
        System.out.println("Status: Successfully Borrowed");
    }
}

class Menu {//12
    public void display() {
        System.out.println("\nSelect Item to Borrow");
        System.out.println("1. Laptop");
        System.out.println("2. Basketball");
        System.out.println("3. Make-Up Kit");
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BorrowSystem system = new BorrowSystem();
        Menu menu = new Menu();

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        Person user = new Person(name);
        
        
        System.out.print("Enter Gender(F/M): ");
        String gender = input.nextLine();
        
        Gender selectedGender = new Gender(gender);
        
        System.out.print("Enter Age: ");
        int age = input.nextInt();
        
        Age selectedAge = new Age(age);
        
        menu.display();
        System.out.print("Enter choice (1-3): ");
        int choice = input.nextInt();
        input.nextLine();

        Item selectedItem;

        if (choice == 1) {
            System.out.print("Enter Laptop Brand: ");
            String brand = input.nextLine();
            selectedItem = new Laptop(brand);
        } else if(choice == 2){
            System.out.print("Enter Ball Brand: ");
            String brand = input.nextLine();
            selectedItem = new Ball(brand);
        }  else if (choice == 3) {
            System.out.print("Enter Makeup Brand: ");
            String brand = input.nextLine();
            selectedItem = new MakeUp(brand);
        } else {
            System.out.println("Invalid choice");
            return;
        }

        system.process(user, selectedItem, selectedGender, selectedAge);

        input.close();
    }
}
