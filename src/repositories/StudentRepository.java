package repositories;

import enums.SortStudentBy;
import enums.SortType;
import models.Student;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class StudentRepository {

    private static SortType sortType;

    // Private constructor to prevent direct instantiation
    private StudentRepository() {
    }

    private static final class InstanceHolder {
        private static final StudentRepository instance = new StudentRepository();
    }

    public static StudentRepository getInstance() {
        return InstanceHolder.instance;
    }

    public void saveStudents(String fileName, List<Student> students) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(students);
            System.out.println("Student data has been saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public List<Student> loadStudents(String fileName) {
        List<Student> students = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object obj = objectIn.readObject();
            if (obj instanceof List<?>) {
                students = (List<Student>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
        return students;
    }

    public Student findStudentById(String filename, Integer studentId) {
        List<Student> students = getInstance().loadStudents(filename);
        for (Student student : students) {
            if (student.getStudentID().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public int updateStudentInformation(String filename, Student student) {
        List<Student> students = loadStudents(filename);
        for (Student student1 : students) {
            Integer studentIDNo = student.getStudentID();
            if (studentIDNo.equals(student1.getStudentID())) {
                student1.updateInformation(student);
                break;
            }
        }

        try {
            getInstance().saveStudents(filename, students);
            return 1;
        } catch (Exception ex) {
            System.out.println("FAIL >> StudentRepository.updateStudentInformation: " + ex.getMessage());
            return 0;
        }
    }

    public void deleteStudent(String filename, Integer studentId) {
        List<Student> students = getInstance().loadStudents(filename);
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentID().equals(studentId)) {
                iterator.remove();
                break;
            }
        }
        getInstance().saveStudents(filename, students);
    }

    public Integer getStudentIdMax(String filename) {
        int studentID = 0;

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            List<Student> studentList = (List<Student>) objectIn.readObject();
            studentID = studentList.size();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return studentID;
    }
}
