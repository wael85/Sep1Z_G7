package model.schedule;

import model.bookedTime.BookedTime;
import model.courses.Course;
import model.person.Teacher;

public class Lesson {
    private Course course;
    private String teacher;
    private String roomId;
    private String start;
    private String end;
    private BookedTime reservedDateTime;

    // remeber each time creating lesson teachers' occupied time and room reserved time should update;
    public Lesson(Course course, String teacher, String roomId, BookedTime reservedDateTime) {
        this.course = course;
        this.teacher = teacher;
        this.roomId = roomId;
        this.reservedDateTime = reservedDateTime;
        this.start = reservedDateTime.getStart().toString();
        this.end = reservedDateTime.getEnd().toString();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

   /* public Teacher getTeacher() {
        return teacher;
    }*/

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomName(String roomId) {
        this.roomId = roomId;
    }

    public BookedTime getReservedDateTime() {
        return reservedDateTime;
    }

    public void setReservedDateTime(BookedTime reservedDateTime) {
        this.reservedDateTime = reservedDateTime;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                course.getCourseName()+roomId+teacher+reservedDateTime.getStart()+reservedDateTime.getEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return course.equals(lesson.course) && teacher.equals(lesson.teacher) && roomId.equals(lesson.roomId) && reservedDateTime.equals(lesson.reservedDateTime);
    }


}
