import bookedTime.BookedTime;
import courses.*;
import person.*;

import rooms.Room;
import rooms.Rooms;
import schedule.Lesson;
import schedule.Schedule;
import schedule.Schedules;

import java.util.ArrayList;

public interface SchedulesModel {
    void fetchStudents(String filePath, Students students);
    void fetchTeachersAndCourses(String filePath, Teachers teachers, Courses courses);
    void fetchRooms(String filePath, Rooms room);
    void addStudentToCourse(Student student, Course course);
    void removeStudentFromCourse(Student student, Course course);
    void assignTeacherToCourse(Teacher teacher, Course course);
    void unassignedTeacherFromCourse(Teacher teacher, Course course);
    void addNewTeacher(Teacher teacher);
    ArrayList<Rooms> getAvailableRooms(BookedTime bookedTime);
    boolean isAvailableTeacher(BookedTime bookedTime , String shorName);
    void scheduleNewLesson(BookedTime bookedTime, Course course , Room room,Teacher teacher);
    void deleteLesson(Lesson lesson);
    void saveSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
    int getCapacity(Room room);
    int getECTS(Course course);
    ArrayList<Room> sortRooms(String scheduleId , ArrayList<Room> rooms);
    ArrayList<BookedTime> getAllSelectedDateTimeInLongPeriod(BookedTime startBookedTime, BookedTime endBookedTime);
    Schedule getScheduleBySemesterClass(String semester,String className);
    Teachers getTeachersForCourse(Course course,Teachers teachers);
    Students getStudentsForCourse(Course course);




}
