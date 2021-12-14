package model.person;

import model.courses.Course;
import model.bookedTime.*;

import java.util.ArrayList;

public class Teacher extends Person {
    private String shortName;
    private ArrayList<Course> teachingCourses;
    private ArrayList<BookedTime> occupiedTime ;

    public Teacher(String shortName,String firstName, String lastName, String email, String telephoneNumber) {
        super(firstName, lastName, email, telephoneNumber);
        this.shortName = shortName;
        this.teachingCourses =new ArrayList<>();
        this.occupiedTime = new ArrayList<>();
    }

    public void addTeachingHours(BookedTime teachingTime){
        if (isAvailable(teachingTime)) {
            occupiedTime.add(teachingTime);
        }
        else
            System.out.println("is working");

    }

    public boolean isAvailable(BookedTime period){
        for (BookedTime time:occupiedTime) {
            DateTime start = time.getStart();
            DateTime end = time.getEnd();

            if ((period.getStart().isBefore(end) && period.getStart().isAfter(start))
                    || (period.getEnd().isBefore(end) && period.getEnd().isAfter(start))
                    || (period.getStart().isBefore(start) && period.getEnd().isAfter(end))
                    || period.getStart().equals(start) || period.getEnd().equals(end)) {
                return false;
            }
        }
        return true;

    }

    public void addOffDay(BookedTime bookedTime){

        occupiedTime.add(bookedTime);


    }

    public void assignToCourse(Course course){
        teachingCourses.add(course);

    }

    public void unassignFromCourse(Course course){
        teachingCourses.remove(course);

    }

    public String getShortName() {
        return shortName;
    }

    public ArrayList<BookedTime> getOccupiedTime() {
        return occupiedTime;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String getFullName() {
        return getFirstName()+" "+getLastName();
    }

    public String toString(){
        return super.toString() + "Teaching model.courses = " + teachingCourses + " Occupied time = " + occupiedTime;
    }
    @Override
    public boolean equals(Object o){
        if (!(o instanceof Teacher)){
            return false;
        }
        return  shortName.equals(((Teacher) o).getShortName());
    }
}
