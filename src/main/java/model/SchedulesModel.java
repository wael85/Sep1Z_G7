package model;

import model.bookedTime.BookedTime;
import model.bookedTime.DateTime;
import model.courses.*;
import model.data.Data;
import model.person.*;

import model.rooms.Room;
import model.rooms.Rooms;
import model.schedule.Lesson;
import model.schedule.Schedule;
import model.schedule.Schedules;
import model.schedule.SelectedData;

import java.util.ArrayList;

public interface SchedulesModel {
    Data getStorage();
    Schedules getSchedules();
    SelectedData getSelectedData();
    void setSelectedData(SelectedData selectedData);
    void fetchStudents(String filePath) throws Exception;
    void fetchTeachersAndCourses(String filePath) throws Exception;
    void fetchRooms(String filePath) throws Exception;
    void addStudentToCourse(Student student, Course course);
    void removeStudentFromCourse(Student student, Course course);
    void assignTeacherToCourse(Teacher teacher, Course course);
    void unassignedTeacherFromCourse(Teacher teacher, Course course);
    void addNewTeacher(Teacher teacher);
    ArrayList<Room> getAvailableRooms(BookedTime bookedTime);
    boolean isAvailableTeacher(BookedTime bookedTime , String shorName);
    void scheduleNewLesson(String scheduleId,BookedTime bookedTime, Course course , Room room,String teacher);
    void deleteLesson(Lesson lesson , String id);
    void saveSchedule(Schedule schedule);
    void deleteSchedule(Schedule schedule);
    int getCapacity(Room room);
    String getECTS(Course course);
    ArrayList<Room> sortRooms(String scheduleId , BookedTime bookedTime);
    ArrayList<BookedTime> getAllSelectedDateTimeInLongPeriod(BookedTime startBookedTime, DateTime endPeriod);
    Schedule getScheduleBySemesterClass(String semester,String className);
    Teachers getTeachersForCourse(Course course);
    Students getStudentsForCourse(Course course);
    void setSelectedSchedule(Schedule schedule);
    Schedule getSelectedSchedule();




}
