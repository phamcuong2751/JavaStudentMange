package services;

import models.Student;
import repositories.StudentRepository;

import javax.swing.text.DefaultTextUI;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    public StudentService() {
    }

    public void saveStudent(String filename, List<Student> studentList) {
        StudentRepository.getInstance().saveStudents(filename, studentList);
    }

    public List<Student> loadAllStudents(String filename) {
        return StudentRepository.getInstance().loadStudents(filename);
    }

    public Student findStudentByID(String filename, Integer studentId) {
        return StudentRepository.getInstance().findStudentById(filename, studentId);
    }

    public void updateStudentInfo(String filename, Student student) {
        try {
            if (StudentRepository.getInstance().updateStudentInformation(filename, student) == 1) {
                System.out.println("Update student " + student.getName() + " successful!");
            }
        } catch (Exception ex) {
            System.out.println("Update student fail: " + ex.getMessage());
        }
    }

    public List<Student> loadAndSortStudentList(String filename, String sortBy, String sortType) {
        return StudentRepository.getInstance().loadStudents(filename, sortBy, sortType);
    }

    public void deleteStudent(String filename, Integer studentID) {
        try {
            StudentRepository.getInstance().deleteStudent(filename, studentID);
            System.out.println("Delete student successful!");
        } catch (Exception ex) {
            System.out.println("Delete student fail: " + ex.getMessage());
        }
    }

    public void printStudentList(String filename) {
        try {
            List<Student> studentListGet = loadAllStudents(filename);
            for (Student student : studentListGet) {
                System.out.println(student.toString());
            }
        } catch (Exception ex) {
            System.out.println("Load students list fail: " + ex.getMessage());
        }
    }

    public void printStudentList(String filename, String sortBy, String sortType) {
        try {
            List<Student> studentListGet = loadAndSortStudentList(filename, sortBy, sortType);
            for (Student student : studentListGet) {
                System.out.println(student.toString());
            }
        } catch (Exception ex) {
            System.out.println("Load students list fail: " + ex.getMessage());
        }
    }

    public void inputStudent(String filename) {
        List<Student> studentList = loadAllStudents(filename);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student details:");

        int studentID =  StudentRepository.getInstance().renderNewID(filename);

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Point: ");
        double point = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Image: ");
        String image = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Remark: ");
        String remark = scanner.nextLine();

        Student newStudent = new Student(studentID, name, point, image, address, remark);

        studentList.add(newStudent);
        saveStudent(filename, studentList);
        System.out.println("Add student " + name + " successful!");
    }

    public void exportToCSV(String filename, String filePath) {

        List<Student> studentList = loadAllStudents(filename);

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.write("Student ID,Name,Point,Image,Address,Remark\n");

            // Write student data
            for (Student student : studentList) {
                writer.write(student.getStudentID() + ",");
                writer.write(student.getName() + ",");
                writer.write(student.getPoint() + ",");
                writer.write(student.getImage() + ",");
                writer.write(student.getAddress() + ",");
                writer.write(student.getRemark() + "\n");
            }

            System.out.println("Student data exported to CSV successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
