import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        StudentManager manager = new StudentManager(FILE_NAME);
        Scanner sc = new Scanner(System.in);

        manager.loadFromFile();

        int choice;
        do {
            System.out.println("===== Student Record Management System – Multithreaded =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Sort Students by Marks");
            System.out.println("7. Save & Exit");
            System.out.print("Enter choice: ");

            choice = readInt(sc);

            switch (choice) {
                case 1:
                    addStudentFlow(manager, sc);
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    updateStudentFlow(manager, sc);
                    break;
                case 4:
                    deleteStudentFlow(manager, sc);
                    break;
                case 5:
                    searchStudentFlow(manager, sc);
                    break;
                case 6:
                    manager.sortByMarks();
                    break;
                case 7:
                    manager.saveToFile();
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        } while (choice != 7);

        sc.close();
    }

    private static void addStudentFlow(StudentManager manager, Scanner sc) {
        System.out.print("Enter roll no: ");
        int roll = readInt(sc);

        System.out.print("Enter name: ");
        String name = readNonEmpty(sc, "Name");

        System.out.print("Enter email: ");
        String email = readNonEmpty(sc, "Email");

        System.out.print("Enter marks (0–100): ");
        double marks = readMarks(sc);

        Student s = new Student(roll, name, email, marks);
        manager.addStudent(s);
    }

    private static void updateStudentFlow(StudentManager manager, Scanner sc) {
        System.out.print("Enter roll no to update: ");
        int roll = readInt(sc);
        System.out.print("Enter new marks (0–100): ");
        double marks = readMarks(sc);
        try {
            manager.updateStudent(roll, marks);
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteStudentFlow(StudentManager manager, Scanner sc) {
        System.out.print("Enter roll no to delete: ");
        int roll = readInt(sc);
        try {
            manager.deleteStudent(roll);
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchStudentFlow(StudentManager manager, Scanner sc) {
        System.out.print("Enter roll no to search: ");
        int roll = readInt(sc);
        try {
            Student s = manager.findStudent(roll);
            System.out.println("Student found:");
            s.displayInfo();
        } catch (StudentNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readMarks(Scanner sc) {
        while (true) {
            while (!sc.hasNextDouble()) {
                System.out.print("Enter a valid number: ");
                sc.next();
            }
            double marks = sc.nextDouble();
            sc.nextLine();
            if (marks < 0 || marks > 100) {
                System.out.print("Marks must be between 0 and 100. Enter again: ");
            } else {
                return marks;
            }
        }
    }

    private static String readNonEmpty(Scanner sc, String fieldName) {
        while (true) {
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.print(fieldName + " cannot be empty. Enter again: ");
        }
    }
}
