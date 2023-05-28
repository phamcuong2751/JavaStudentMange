package models;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer studentID;
    private String name;
    private Double point;
    private String image;
    private String address;
    private String remark;
    public Student(){};
    public Student(Integer studentID, String name, Double point, String image, String address, String remark) {
        this.studentID = studentID;
        this.name = name;
        this.point = point;
        this.image = image;
        this.address = address;
        this.remark = remark;
    }

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void updateInformation(Student student) {
        this.name = student.getName();
        this.point = student.getPoint();
        this.image = student.getImage();
        this.address = student.getAddress();
        this.remark = student.getRemark();
    }

    @Override
    public String toString() {
        return "Student: " +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", image='" + image + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'';

    }
}
