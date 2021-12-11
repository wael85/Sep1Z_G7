package model.person;

import model.courses.Course;

import java.util.ArrayList;

public class Student extends Person {
    private String studentsClass;
    private String semester;
    private String id;
    private ArrayList<Course> courses;

    public Student (String firstName, String lastName, String email, String telephoneNumber, String studentsClass , String semester , String id) {
        super(firstName,lastName,email,telephoneNumber);
        this.id = id;
        this.studentsClass = studentsClass;
        this.semester = semester;
        this.courses= new ArrayList<>();

    }

    public void assignStudentToCourse(Course course){
        courses.add(course);

    }

    public String getId() {
        return id;
    }

    public void unassignStudentFromCourse(Course course){
        courses.remove(course);
    }

    public String getStudentsClass() {
        return studentsClass;
    }

    public void setStudentsClass(String studentsClass) {
        this.studentsClass = studentsClass;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String getFullName() {
        return null;
    }

    public String toString(){
        return super.toString() + " " + id;//"Class = " + studentsClass + "Semester = " + semester;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Person)) return false;

        Student other = (Student) obj;
        return semester.equals(other.semester)&&studentsClass.equals(other.studentsClass) && super.equals(other);
    }
}