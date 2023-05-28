import data.DataLoader;
import enums.MenuOption;
import models.Student;
import services.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filename = "student.dat";
    private static final List<Student> studentList = DataLoader.getInstance().getStudentList();

    private static final String prgramFunction = "1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuOption choice;
        StudentService studentService = new StudentService();
        studentService.saveStudent(filename, studentList);

        do {

            displayMenu();
            int option = scanner.nextInt();
            try {
                choice = MenuOption.values()[option - 1];
            } catch (Exception ex) {
                System.out.println("ERROR: >>>> " + ex.getMessage());
                displayMenu();
                option = scanner.nextInt();
                choice = MenuOption.values()[option - 1];
            }


            switch (choice) {
                case GET_ALL:
                    studentService.printStudentList(filename);
                    break;
                case INSERT:
                    studentService.inputStudent(filename);
                    break;
                case UPDATE:
//                    studentService.updateStudentInfo(filename);
                    break;
                case DELETE:
                    System.out.print("Enter student ID you want to delete (0: EXIT): ");
                    int studentID = scanner.nextInt();
                    if (studentID > 0) {
                        studentService.deleteStudent(filename, studentID);
                    }
                    break;
                case SORT:
                    System.out.print("Do you want sort by (0:  ID, 1: POINT)? ");
                    String sortBy = scanner.next();

                    System.out.print("Do you want sort type (0:  ASC, 1: DESC)? ");
                    String sortType = scanner.next();

                    studentService.printStudentList(filename, sortBy, sortType);
                    break;
                default:
                    System.out.println("Goodbye!!!");
                    break;
            }


        } while (choice != MenuOption.EXIT);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Welcome to the Console Program!");
        System.out.println("Choose an option:");

        System.out.println("1. " + "Get all Student");
        System.out.println("2. " + "Insert new Student");
        System.out.println("3. " + "Update Student");
        System.out.println("4. " + "Delete Student");
        System.out.println("5. " + "Sort Student List");
        System.out.println("6. " + "Exit");

        System.out.print("Enter your choice: ");
    }

}