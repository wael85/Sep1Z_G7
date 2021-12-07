package model.courses;

import model.person.*;

import java.util.ArrayList;


public class Course {

    private int eCTS;
    private String semester;
    private String name;
    private String className;

   // private Rooms model.rooms;
    private Students students;
    private ArrayList<String> teachersShortName;


    public Course(String courseName, String semester, String className, String eCTS) {
        this.eCTS = Integer.parseInt(eCTS);
        this.semester=semester;
        this.name = courseName;
        this.className = className;
      //  this.model.rooms = new Rooms();
        this.students = new Students();
        this.teachersShortName = new ArrayList<>();
    }

    public int getECTS() {
        return eCTS;
    }

    public void setECTS(int ECTS) {
        this.eCTS = ECTS;
    }

    public String getCourseName() {
        return name;
    }

    public void setCourseName(String courseName) {
        this.name = courseName;
    }

    public String getClassID() {
        return className;
    }

    public void setClassID(String className) {
        this.className = className;
    }

   /* public ArrayList<Room> getRooms() {
        return model.rooms;
    }*/

   /* public void setRooms(ArrayList<Room> model.rooms) {
        this.model.rooms = model.rooms;
    }*/

    public String getSemester() {
        return semester;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public ArrayList<String> getTeachers() {
        return teachersShortName;
    }

   /* public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }*/

    public boolean equals(Object obj){
        if ((obj instanceof Course)){
            return false;
        }
        Course other= (Course) obj;

        return eCTS==other.eCTS && semester.equals(other.semester) && name.equals(other.name) && className.equals(other.className);
    }

}