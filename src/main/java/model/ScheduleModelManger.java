package model;

import model.bookedTime.BookedTime;
import model.courses.Course;
import model.courses.Courses;
import model.data.Data;
import model.data.ReadFromTXTFile;
import model.person.Student;
import model.person.Students;
import model.person.Teacher;
import model.person.Teachers;
import model.rooms.Room;
import model.rooms.Rooms;
import model.schedule.Lesson;
import model.schedule.Schedule;
import model.schedule.Schedules;

import java.util.ArrayList;

public class ScheduleModelManger implements SchedulesModel {
    private Schedules schedules;
    private Data storage;

    public ScheduleModelManger(){
        schedules = new Schedules();
        storage = new Data();
    }

    @Override
    public void fetchStudents(String filePath, Students students)
            throws Exception
    {
        ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile(filePath);
        storage.setStudentsData(dataOnFile);
    }

    @Override
    public void fetchTeachersAndCourses(String filePath, Teachers teachers, Courses courses)
            throws Exception
    {
        ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile(filePath);
        storage.setTeachersCoursesData(dataOnFile);
    }

    @Override
    public void fetchRooms(String filePath, Rooms room) throws Exception
    {
        ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile(filePath);
        storage.setRoomsData(dataOnFile);
    }

    @Override
    public void addStudentToCourse(Student student, Course course) {
        course.getStudents().addStudent(student);
    }

    @Override
    public void removeStudentFromCourse(Student student, Course course) {
        course.getStudents().addStudent(student);
    }

    @Override
    public void assignTeacherToCourse(Teacher teacher, Course course) {
        course.getTeachers().add(teacher.getShortName());
    }

    @Override
    public void unassignedTeacherFromCourse(Teacher teacher, Course course) {
        for (int i = 0; i <course.getTeachers().size() ; i++) {
            if(course.getTeachers().get(i).equals(teacher.getShortName())){
                course.getTeachers().remove(i);
            }
        }

    }

    @Override
    public void addNewTeacher(Teacher teacher) {
       storage.teachers.addTeacher(teacher);
    }

    @Override
    public ArrayList<Room> getAvailableRooms(BookedTime bookedTime) {
        ArrayList<Room> availableRooms= new ArrayList<>();
        for (int i = 0; i < storage.rooms.getSize(); i++) {
            if(storage.rooms.getRoomsList().get(i).isAvailable(bookedTime)){
                availableRooms.add(storage.rooms.getRoomsList().get(i));
            }
        }
        return availableRooms;
    }

    @Override
    public boolean isAvailableTeacher(BookedTime bookedTime, String shorName) {
       return storage.teachers.getTeacherByShortName(shorName).isAvailable(bookedTime);
    }

    @Override
    public void scheduleNewLesson(String scheduleId,BookedTime bookedTime, Course course, Room room, Teacher teacher) {
        schedules.getScheduleById(scheduleId).scheduleNewLesson(new Lesson(course,teacher,room.getRoomId(),bookedTime));
    }

    @Override
    public void deleteLesson(Lesson lesson, String id) {
        schedules.getScheduleById(id).getLessons().remove(lesson);
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        schedules.addSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Schedule schedule) {
        schedules.getSchedules().remove(schedule);
    }

    @Override
    public int getCapacity(Room room) {
        return room.getRoomCapacity();
    }

    @Override
    public int getECTS(Course course) {
        return course.getECTS();
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
    public Teachers getTeachersForCourse(Course course) {
        ArrayList<String> teachersListShortName = course.getTeachers();
        Teachers teachers = new Teachers();

        for(int i=0; i < teachersListShortName.size(); i++)
        {
            teachers.addTeacher(storage.teachers.getTeacherByShortName(teachersListShortName.get(i)));
        }
        //Shouldn't it return Teacher? then change to public Teacher getTeacherstForCourse...
        return teachers;
    }

    @Override
    public Students getStudentsForCourse(Course course) {
        return course.getStudents();
    }
}
