import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    ToDoMatrix matrix = new ToDoMatrix();


    public static void main(String[] args) {

        Main main = new Main();
        main.mainMenu();
    }

    public void mainMenu() {
        matrix.addItemsFromFile("data.csv");
        boolean isRunning = true;
        int choice;
        String menuOptions = "1. Add item\n" +
                "2. Mark/unmark\n" +
                "3. Print tasks\n" +
                "4. Archieve items\n" +
                "5. Remove task\n" +
                "6. Print specified quarter\n" +
                "0. Exit";

        while (isRunning) {
            System.out.println(menuOptions);
            choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addNewItem();
                    clearScreen();
                    break;
                case 2:
                    markUnmark();

                    break;
                case 3:
                    clearScreen();
                    System.out.println(matrix.toString());
                    break;
                case 4:
                    matrix.archiveItems();
                    break;
                case 5:
                    removeItem();
                    break;
                case 6:
                    printSpecifiedQuarter();
                    break;
                case 0:
                    matrix.saveItemsToFile("data.csv");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
            }
        }
    }

    public int getMenuChoice() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose an option:");
        int choice = -1;
        while (choice < 0) {
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong option, try again!");
            }
        }
        return choice;
    }

    public void addNewItem() {
        String title = getTitle();
        LocalDate deadline = getDeadlineFromInput();
        boolean isImportant = getIsImportant();

        System.out.println(title + deadline + isImportant);
        matrix.addItem(title, deadline, isImportant);
    }

    public String getTitle() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter title");
        String title = sc.nextLine();
        return title;
    }

    public LocalDate getDeadlineFromInput() {
        Scanner sc = new Scanner(System.in);

        int month, day;
        LocalDate deadline = null;
        while (deadline == null) {
            try {
                System.out.println("Month:");
                month = sc.nextInt();
                System.out.println("Day:");
                day = sc.nextInt();

                deadline = LocalDate.of(2018, month, day);
            } catch (DateTimeException e) {
                System.out.println("Invalid date, try again!");
            }
        }
        return deadline;
    }

    public boolean getIsImportant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Is task important? Type y/n");
        String isImportant = sc.nextLine().toLowerCase();
        if (isImportant.equals("yes")) {
            return true;
        } else
            return false;
    }

    public void clearScreen() {
        System.out.println("\n\n\n\n");
    }

    public void markUnmark() {
        System.out.println("Choose quarter: 'IU', 'IN', 'NU', 'NN'");
        try {
            Scanner sc = new Scanner(System.in);
            String key = sc.nextLine().toUpperCase();
            System.out.println(matrix.getQuarter(key).toString());
            int index = getIndex() - 1;
            boolean isDone = matrix.getQuarter(key).toDoItemList.get(index).getIsDone();
            if (isDone) {
                matrix.getQuarter(key).toDoItemList.get(index).unmark();
            } else {
                matrix.getQuarter(key).toDoItemList.get(index).mark();
            }
        } catch (Exception e) {
            System.out.println("Invalid quarter!");
        }
    }

    public int getIndex() {
        Scanner sc = new Scanner(System.in);
        int index = -1;
        try {
            System.out.println("Provide index");
            index = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid index, try again!");
        }
        return index;
    }

    public void removeItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose quarter: 'IU', 'IN', 'NU', 'NN'");

        String key = sc.nextLine().toUpperCase();
        System.out.println(matrix.getQuarter(key).toString());

        int index = getIndex() - 1;

        try {
            matrix.getQuarter(key).toDoItemList.remove(index);
        } catch (NullPointerException e) {
            System.out.println("Wrong index!");
        }
    }

    public void printSpecifiedQuarter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose quarter: 'IU', 'IN', 'NU', 'NN'");
        try {
            String key = sc.nextLine().toUpperCase();
            System.out.println(matrix.getQuarter(key).toString());
        } catch (InputMismatchException e) {
            System.out.println("Invalid quarter, try again!");
        }
    }

}
