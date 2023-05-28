package data;

import models.Student;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    private static DataLoader instance;
    private final List<Student> studentList;

    private DataLoader() {
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Nguyen Tien Anh", 7.5, "image1.jpg", "97 HAO NAM, P.O CHO DUA, DONG DA, HA NOI", "Remark 1"));
        studentList.add(new Student(2, "Pham Phong Phu Cuong", 4.4, "image2.jpg", "88 Duong So 8, Binh Hung, Binh Chanh", "Remark 2"));
        studentList.add(new Student(3, "Nguyen Van Linh", 9.0, "image3.jpg", "Hem 180 Xo Viet Nghe Tinh P21 Q Binh Thanh", "Remark 3"));
        studentList.add(new Student(4, "Quach Thanh Tung", 4.25, "image4.jpg", "So 170 Quang Trung, Phuong 10, Quan Go Vap", "Remark 4"));
        studentList.add(new Student(5, "Nguyen Ly Hong", 9.7, "image5.jpg", "Thoi Tam Thon 15, Tam Dong 2, Thoi Tam Thon, Hoc Mon", "Remark 5"));
        studentList.add(new Student(6, "Nguyen Thu Trang", 5.5, "image6.jpg", "02 Phan Van Dang, Phuong Thanh My Loi, Tp Thu Duc", "Remark 6"));
        studentList.add(new Student(7, "Tran Xuan Bach", 7.2, "image7.jpg", "So 7, Duong Ngo Quyen, Phuong Hiep Phu", "Remark 7"));
        studentList.add(new Student(8, "Vu Van Tien", 6.3, "image8.jpg", "So 359 Pham Van Chieu, Phuong 14, Quan Go Vap", "Remark 8"));
        studentList.add(new Student(9, "Le Thong Nhat", 4.5, "image9.jpg", "45/6 Le Van Huan, phuong 13, quan Tan Binh TP. HCM", "Remark 9"));
        studentList.add(new Student(10, "Tran Nhat Kha", 8.3, "image0.jpg", "46 Nguyen Truong To, Phuong 13, Quan 4", "Remark 10"));
    }
    public static DataLoader getInstance() {
        if (instance == null) {
            synchronized (DataLoader.class) {
                if (instance == null) {
                    instance = new DataLoader();
                }
            }
        }
        return instance;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

}