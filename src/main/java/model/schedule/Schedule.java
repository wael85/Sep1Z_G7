package model.schedule;

import model.bookedTime.BookedTime;

import java.util.ArrayList;

public class Schedule {
    private String id;
    private String semester;
    private String className;
    private ArrayList<Lesson> lessons;

    public Schedule(String id,String semester,String className){
        this.id=id;
        this.semester=semester;
        this.className=className;

        this.lessons=new ArrayList<>();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
    public void scheduleNewLesson(Lesson lesson){
        lessons.add(lesson);
    }
    public ArrayList<Lesson> getAlLessonsInDay(BookedTime day) throws Exception{
        try {
            ArrayList<Lesson> lessons = new ArrayList<>();
            for (Lesson x:lessons ) {
                if(x.getReservedDateTime().getStart().getDatTimeInMillieSecond() >= day.getStart().getDatTimeInMillieSecond() &&
                   x.getReservedDateTime().getStart().getDatTimeInMillieSecond() <= day.getStart().getDatTimeInMillieSecond()
                ){
                   lessons.add(x);
                }
            }
            return lessons;
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", semester='" + semester + '\'' +
                ", className='" + className + '\'' +
                ", lessons=" + lessons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id.equals(schedule.id) && semester.equals(schedule.semester) && className.equals(schedule.className);
    }

}
