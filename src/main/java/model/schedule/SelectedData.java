package model.schedule;

import model.courses.Course;
import model.schedule.Schedule;

public class SelectedData {
    private Course course;
    private Schedule schedule;
    private String semester;
    private String className;

    public SelectedData(){
        this.course = null;
        this.schedule = null;
        this.semester = "";
        this.className = "";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
