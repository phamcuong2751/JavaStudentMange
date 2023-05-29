package services;

import enums.SortStudentBy;
import enums.SortType;
import models.Student;
import repositories.StudentRepository;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
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
        List<Student> students = StudentRepository.getInstance().loadStudents(filename);
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object obj = objectIn.readObject();
            if (obj instanceof List<?>) {
                students = (List<Student>) obj;

                try {
                    if (Objects.equals(sortBy, SortStudentBy.ID.getValue())) {
                        if (Objects.equals(sortType, SortType.DESC.getValue())) {
                            students.sort((s2, s1) -> s1.getStudentID().compareTo(s2.getStudentID()));
                        } else {
                            students.sort((s1, s2) -> s1.getStudentID().compareTo(s2.getStudentID()));
                        }
                    } else if (Objects.equals(sortBy, SortStudentBy.POINT.getValue())) {

                        if (Objects.equals(sortType, SortType.DESC.getValue())) {
                            students.sort((s2, s1) -> s1.getPoint().compareTo(s2.getPoint()));
                        } else {
                            students.sort((s1, s2) -> s1.getPoint().compareTo(s2.getPoint()));
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Sort Student list fail: " + ex.getMessage());
                }

                this.saveStudent(filename, students);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
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
            List<Student> studentListGet =  StudentRepository.getInstance().loadStudents(filename);
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

        int studentID = StudentRepository.getInstance().getStudentIdMax(filename);
        ++studentID;

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

    public void inputStudentUpdate(String filename) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID you want to update (0: EXIT): ");
        int studentIDUpdate = scanner.nextInt();
        Student student = this.findStudentByID(filename, studentIDUpdate);

        Field[] fields = student.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.getName().equals("studentID")) continue;

            System.out.print("Change " + field.getName() + " (0: N, 1: Y): ");
            int isChangeField = scanner.nextInt();

            if (isChangeField == 1) {
                // Consume the remaining newline character
                scanner.nextLine();

                System.out.print("New " + field.getName() + ": ");
                String fieldValue = scanner.nextLine();

                try {
                    if (field.getType().equals(Integer.class)) {
                        field.set(student, Integer.parseInt(fieldValue));
                    } else if (field.getType().equals(Double.class)) {
                        field.set(student, Double.parseDouble(fieldValue));
                    } else {
                        field.set(student, fieldValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        this.updateStudentInfo(filename, student);
    }
}
