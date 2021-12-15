package model.courses;

import model.person.*;

import java.util.ArrayList;


public class Course {

    private String eCTS;
    private String semester;
    private String name;
    private String className;

   // private Rooms model.rooms;
    private Students students;
    private ArrayList<String> teachersShortName;


    public Course(String courseName, String semester, String className, String eCTS) {
        this.eCTS = eCTS;
        this.semester=semester;
        this.name = courseName;
        this.className = className;
      //  this.model.rooms = new Rooms();
        this.students = new Students();
        this.teachersShortName = new ArrayList<>();
    }

    public String getECTS() {
        return eCTS;
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
    public boolean hasTeacher(String shortName){
        if (teachersShortName.contains(shortName)){
            return true;
        }
        return false;
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

        return  semester.equals(other.getSemester()) && name.equals(other.getCourseName()) && className.equals(other.getClassID());
    }

    @Override
    public String toString() {
        return   this.getCourseName();
    }
}