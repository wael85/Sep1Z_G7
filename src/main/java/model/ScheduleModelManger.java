package model;

import model.bookedTime.BookedTime;
import model.bookedTime.DateTime;
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
import model.schedule.SelectedData;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collections;

public class ScheduleModelManger implements SchedulesModel {
    private Schedules schedules;
    private Data storage;
    private SelectedData selectedData;
    private Schedule selectedSchedule;

    public ScheduleModelManger(){
        schedules = new Schedules();
        storage = new Data();
        selectedData = new SelectedData();
        selectedSchedule = null;
    }
    @Override
    public SelectedData getSelectedData() {
        return selectedData;
    }
    @Override
    public void setSelectedData(SelectedData selectedData) {
        this.selectedData = selectedData;
    }

    @Override
    public Schedules getSchedules(){
        return schedules;
    }
    @Override
     public Data getStorage(){
        return this.storage;
    }

    @Override
    public void fetchStudents(String filePath)
            throws Exception
    {
        ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile(filePath);
        storage.setStudentsData(dataOnFile);
    }

    @Override
    public void fetchTeachersAndCourses(String filePath)
            throws Exception
    {
        ArrayList<String[]> dataOnFile = ReadFromTXTFile.ReadTXTFile(filePath);
        storage.setTeachersCoursesData(dataOnFile);
    }

    @Override
    public void fetchRooms(String filePath) throws Exception
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
    public void scheduleNewLesson(String scheduleId,BookedTime bookedTime, Course course, Room room, String teacher) {
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
    public String getECTS(Course course) {
        return course.getECTS();
    }

    @Override
    public ArrayList<Room> sortRooms(String scheduleId, BookedTime bookedTime) {

        ArrayList<Room> availableRooms = storage.rooms.getAvailableRooms(bookedTime);
        if( schedules.getScheduleById(scheduleId).getLessons().size() > 0) {
            String id = schedules.getScheduleById(scheduleId).getLessons().get(0).getRoomId();
            for (int i = 0; i < availableRooms.size(); i++) {
                if (availableRooms.get(i).getRoomId().equals(id)) {
                    Collections.swap(availableRooms, 0, i);
                }
            }
        }
        return availableRooms;
    }

    @Override
    public ArrayList<BookedTime> getAllSelectedDateTimeInLongPeriod(BookedTime startBookedTime, DateTime endPeriod)
    {

        ArrayList<BookedTime> longPeriod = new ArrayList<>();

        //12.12.2012 - 9:00 -12:00 <- 13.12.2012 - 9:00-13:00
        DateTime startBookedTimeStart = startBookedTime.getStart();
        DateTime startBookedTimeEnd = startBookedTime.getEnd();

        int hourStart = startBookedTimeStart.getHour();
        int minuteStart = startBookedTimeStart.getMinute();

        int hourEnd = startBookedTimeEnd.getHour();
        int minuteEnd = startBookedTimeEnd.getMinute();

        DateTime newStartBookedTime;
        DateTime newEndBookedTime ;

        longPeriod.add(startBookedTime);

        BookedTime bookedTime;

        LocalDate startPeriod = LocalDate.of(startBookedTimeEnd.getYear(), startBookedTimeEnd.getMonth(), startBookedTimeEnd.getDay());
        LocalDate endPeriodGiven = LocalDate.of(endPeriod.getYear(), endPeriod.getMonth(), endPeriod.getDay());

        long difference;
        long differenceInWeeks=0;

        for(int j=0; j<10; j++)
        //while(startBookedTime.getStart().getDatTimeInMillieSecond()<= endPeriod.getDatTimeInMillieSecond())
        {
            if(startPeriod.getYear() == endPeriod.getYear())
            {
                difference = endPeriodGiven.getLong(ChronoField.DAY_OF_YEAR) - startPeriod.getLong(ChronoField.DAY_OF_YEAR);
                differenceInWeeks = difference/7;
            }

            for(int i=1; i<=differenceInWeeks; i++)
            {
                LocalDate returnvalue = startPeriod.plusWeeks(i);

                newStartBookedTime = new DateTime(returnvalue.getDayOfMonth(), returnvalue.getMonthValue(), returnvalue.getYear(), hourStart, minuteStart);
                newEndBookedTime = new DateTime(returnvalue.getDayOfMonth(), returnvalue.getMonthValue(), returnvalue.getYear(), hourEnd, minuteEnd);

                bookedTime = new BookedTime(newStartBookedTime, newEndBookedTime);

                longPeriod.add(bookedTime);
            }
        }
        return longPeriod;
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

        return teachers;
    }

    @Override
    public Students getStudentsForCourse(Course course) {
        return course.getStudents();
    }

    @Override
    public void setSelectedSchedule(Schedule schedule) {
        selectedSchedule = schedule;
    }
    //Selected schedule for CourseEditingPageController
    @Override
    public Schedule getSelectedSchedule() {
        return selectedSchedule;
    }

    // test
    public static void main(String[] args) {
        SchedulesModel b = new ScheduleModelManger();
       try {
          String[] g = ReadFromTXTFile.ReadTXTFile("C:\\Users\\drwae\\Downloads\\Rooms.txt").get(1);
           System.out.println(g[1]);
           b.fetchRooms("C:\\Users\\drwae\\Downloads\\Rooms.txt");
           System.out.println(b.getStorage().rooms.getRooms()[1].getRoomId());
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

    }
}
