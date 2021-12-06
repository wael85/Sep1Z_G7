package schedule;

import bookedTime.BookedTime;
import courses.Course;
import person.Teacher;

import java.util.Objects;

public class Lesson {
    private Course course;
    private Teacher teacher;
    private String roomId;
    private BookedTime reservedDateTime;

    // remeber each time creating lesson teachers' occupied time and room reserved time should update;
    public Lesson(Course course, Teacher teacher, String roomId, BookedTime reservedDateTime) {
        this.course = course;
        this.teacher = teacher;
        this.roomId = roomId;
        this.reservedDateTime = reservedDateTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
                "course=" + course +
                ", teacher=" + teacher +
                ", roomId='" + roomId + '\'' +
                ", reservedDateTime=" + reservedDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return course.equals(lesson.course) && teacher.equals(lesson.teacher) && roomId.equals(lesson.roomId) && reservedDateTime.equals(lesson.reservedDateTime);
    }


}
