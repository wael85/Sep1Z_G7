package model.courses;

import java.util.ArrayList;

public class Courses {

    private ArrayList<Course> coursesList;

    public Courses() {

        coursesList = new ArrayList<>();
    }

    public void addCourse(Course course){
        coursesList.add(course);
    }

    public void removeCourse(Course course){
        coursesList.remove(course);
    }

    public int size(){
        return coursesList.size();
    }

    public ArrayList<Course> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(ArrayList<Course> coursesList) {
        this.coursesList = coursesList;
    }
    public Course getCourse(int index){
        return coursesList.get(index);
    }
    public boolean hasCourse(Course course){
        for (int i = 0; i < coursesList.size(); i++) {
            if(coursesList.get(i).equals(course)){
                return true;
            }
        }
        return false;
    }
}
