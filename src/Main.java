import data.DataLoader;
import enums.MenuOption;
import models.Student;
import services.StudentService;
import utils.InputUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String filename = "resources/data/student.dat";
    private static final String exportFile = "resources/data/students.csv";

//    private static final List<Student> studentList = DataLoader.getInstance().getStudentList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuOption choice;
        StudentService studentService = new StudentService();
//        studentService.saveStudent(filename, studentList);

        do {
            InputUtils.getInstance().displayMenu();
            int option = scanner.nextInt();
            try {
                choice = MenuOption.values()[option - 1];
            } catch (Exception ex) {
                System.out.println("ERROR: >>>> " + ex.getMessage());
                InputUtils.getInstance().displayMenu();
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
                    studentService.inputStudentUpdate(filename);
                    break;
                case DELETE:
                    System.out.print("Enter student ID you want to delete (0: EXIT): ");
                    int studentID = scanner.nextInt();
                    if (studentID > 0) {
                        studentService.deleteStudent(filename, studentID);
                    }
                    break;
                case SORT:
                    System.out.print("Do you want sort by (0: ID, 1: POINT)? ");
                    String sortBy = scanner.next();

                    System.out.print("Do you want sort type (0: ASC, 1: DESC)? ");
                    String sortType = scanner.next();

                    studentService.loadAndSortStudentList(filename, sortBy, sortType);
                    studentService.printStudentList(filename);

                    break;
                case EXPORT:
//                    String rootPath = System.getProperty("user.dir");
//                    System.out.println("Root Path: " + rootPath);
                    studentService.exportToCSV(filename, exportFile);
                    break;
                case IMPORT:
                    System.out.println("The function is not yet completed!");
                    break;
                default:
                    System.out.println("Goodbye!!!");
                    break;
            }

        } while (choice != MenuOption.EXIT);

        scanner.close();
    }


}