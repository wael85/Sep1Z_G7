package model.data;

import model.courses.Course;
import model.courses.Courses;
import model.person.*;
import model.rooms.Room;
import model.rooms.Rooms;
import model.schedule.Schedules;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Data {
    public Teachers teachers;
    public Students students;
    public Rooms rooms;
    public Courses courses;
   // public Schedules schedules;

    public Data() {
        this.teachers = new Teachers();
        this.students = new Students();
        this.rooms = new Rooms(50);
        this.courses = new Courses();
      //  this.schedules = new Schedules();
    }
    public void setStudentsData( ArrayList<String[]> dataFromFile) throws Exception{
        if(dataFromFile.size() == 0 || dataFromFile.get(0)[0].equals( "Students".toLowerCase())){
            throw new Exception("The file is empty or not a student file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                String firstName = strings[2].split(" ")[0];
                String lastName = strings[2].split(" ")[1];
                Student student = new Student(firstName,lastName,(firstName+lastName+"@via.dk")," ",strings[1],strings[0]);
                if(!(students.hasStudent(student))){
                     students.addStudent(student);
                }
            }
        }
    }
    public void setTeachersCoursesData( ArrayList<String[]> dataFromFile) throws Exception{
        if(dataFromFile.size() == 0 || dataFromFile.get(0)[0].equals( "Courses".toLowerCase())){
            throw new Exception("The file is empty or not a Courses file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                Teacher teacher = new Teacher(strings[3],"firstName","lastName",(strings[3]+"@via.dk")," ");
                if(!(teachers.hasTeacher(teacher))){
                    teachers.addTeacher(teacher);
                }
                Course course = new Course(strings[2],strings[0],strings[1],strings[4]);
                // check if model.courses list has this course but not this teacher then add this teacher short name to this cours
                for (int j = 0; j < courses.size(); j++) {
                    if(courses.getCourse(j).equals(course)){
                        if(!(course.getTeachers().contains(teacher.getShortName()))){
                            courses.getCourse(j).getTeachers().add(teacher.getShortName());
                        }
                    }
                }
                // if this course not in the model.courses list then first add this teacher short name to this course and then add this course to this model.courses
                if(!(courses.hasCourse(course))){
                    course.getTeachers().add(teacher.getShortName());
                    // add all students for course
                    for (Student x: students.getStudents() ) {
                        if(course.getClassID().equals(x.getStudentsClass()) && course.getSemester().equals(x.getSemester())){
                            course.getStudents().addStudent(x);
                        }
                    }
                    courses.addCourse(course);
                }

            }
        }
    }

    public void setRoomsData( ArrayList<String[]> dataFromFile) throws Exception{
        if(dataFromFile.size() == 0 || dataFromFile.get(0)[0].equals( "Rooms".toLowerCase())){
            throw new Exception("The file is empty or not a model.rooms file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                int capacity = Integer.parseInt(strings[1]);
                if(strings.length == 3){
                    Room room = new Room(capacity,strings[0],strings[2]);
                    // if the room not exist add it to model.rooms
                    if((rooms.containRoom(room))){
                       rooms.addRoom(room);
                    }
                }else {
                    Room room = new Room(capacity,strings[0]);
                    if((rooms.containRoom(room))){
                        rooms.addRoom(room);
                    }
                }

            }
        }
    }



    public static void main(String[] args) {
        try {
            ArrayList<String[]> data = ReadFromTXTFile.ReadTXTFile("C:\\Users\\drwae\\Downloads\\Rooms.txt");
            for ( String[]x: data) {
                for (String y:x) {
                    System.out.println(y);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}

