import bookedTime.BookedTime;
import courses.Course;
import courses.Courses;
import data.Data;
import person.Student;
import person.Students;
import person.Teacher;
import person.Teachers;
import rooms.Room;
import rooms.Rooms;
import schedule.Lesson;
import schedule.Schedule;
import schedule.Schedules;

import java.util.ArrayList;

public class ScheduleModelManger implements SchedulesModel{
    private Schedules schedules;
    private Data storage;

    public ScheduleModelManger(){
        schedules = new Schedules();
        storage = new Data();
    }

    @Override
    public void fetchStudents(String filePath, Students students) {

    }

    @Override
    public void fetchTeachersAndCourses(String filePath, Teachers teachers, Courses courses) {

    }

    @Override
    public void fetchRooms(String filePath, Rooms room) {

    }

    @Override
    public void addStudentToCourse(Student student, Course course) {



    }

    @Override
    public void removeStudentFromCourse(Student student, Course course) {

    }

    @Override
    public void assignTeacherToCourse(Teacher teacher, Course course) {

    }

    @Override
    public void unassignedTeacherFromCourse(Teacher teacher, Course course) {

    }

    @Override
    public void addNewTeacher(Teacher teacher) {

    }

    @Override
    public ArrayList<Rooms> getAvailableRooms(BookedTime bookedTime) {
        return null;
    }

    @Override
    public boolean isAvailableTeacher(BookedTime bookedTime, String shorName) {
        return false;
    }

    @Override
    public void scheduleNewLesson(BookedTime bookedTime, Course course, Room room, Teacher teacher) {

    }

    @Override
    public void deleteLesson(Lesson lesson) {

    }

    @Override
    public void saveSchedule(Schedule schedule) {

    }

    @Override
    public void deleteSchedule(Schedule schedule) {

    }

    @Override
    public int getCapacity(Room room) {
        return 0;
    }

    @Override
    public int getECTS(Course course) {
        return 0;
    }

    @Override
    public ArrayList<Room> sortRooms(String scheduleId, ArrayList<Room> rooms) {
        return null;
    }

    @Override
    public ArrayList<BookedTime> getAllSelectedDateTimeInLongPeriod(BookedTime startBookedTime, BookedTime endBookedTime) {
        return null;
    }

    @Override
    public Schedule getScheduleBySemesterClass(String semester, String className) {
        return null;
    }

    @Override
    public Teachers getTeachersForCourse(Course course,Teachers teachers) {
        Teachers teacherInCourse = new Teachers();
        for (String x: course.getTeachers() ) {
            for (Teacher y: teachers.getTeachers() ) {
                if(x.equals(y.getShortName())){
                    teacherInCourse.addTeacher(y);
                }
            }
        }
        return teacherInCourse;
    }

    @Override
    public Students getStudentsForCourse(Course course) {
       return course.getStudents();

    }
}
