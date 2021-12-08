package model;

import model.bookedTime.BookedTime;
import model.courses.*;
import model.person.*;

import model.rooms.Room;
import model.rooms.Rooms;
import model.schedule.Lesson;
import model.schedule.Schedule;

import java.util.ArrayList;

public interface SchedulesModel {
    void fetchStudents(String filePath, Students students) throws Exception;
    void fetchTeachersAndCourses(String filePath, Teachers teachers, Courses courses) throws Exception;
    void fetchRooms(String filePath, Rooms room) throws Exception;
    void addStudentToCourse(Student student, Course course);
    void removeStudentFromCourse(Student student, Course course);
    void assignTeacherToCourse(Teacher teacher, Course course);
    void unassignedTeacherFromCourse(Teacher teacher, Course course);
    void addNewTeacher(Teacher teacher);
    ArrayList<Room> getAvailableRooms(BookedTime bookedTime);
    boolean isAvailableTeacher(BookedTime bookedTime , String shorName);
    void scheduleNewLesson(String scheduleId,BookedTime bookedTime, Course course , Room room,Teacher teacher);
    void deleteLesson(Lesson lesson , String id);
    void saveSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
    int getCapacity(Room room);
    int getECTS(Course course);
    ArrayList<Room> sortRooms(String scheduleId , ArrayList<Room> rooms);
    ArrayList<BookedTime> getAllSelectedDateTimeInLongPeriod(BookedTime startBookedTime, BookedTime endBookedTime);
    Schedule getScheduleBySemesterClass(String semester,String className);
    Teachers getTeachersForCourse(Course course);
    Students getStudentsForCourse(Course course);




}
