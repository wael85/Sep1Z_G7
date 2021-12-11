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
        if(dataFromFile.size() == 0 || !(dataFromFile.get(0)[0].equals( "Students"))){
            throw new Exception("The file is empty or not a student file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                String firstName = strings[3].split(" ")[0];
                String lastName = strings[3].split(" ")[1];
                Student student = new Student(firstName,lastName,(firstName+lastName+"@via.dk")," ",strings[1],strings[0] ,strings[2]);
                if(!(students.hasStudent(student))){
                     students.addStudent(student);
                }
            }
        }
    }
    public void setTeachersCoursesData( ArrayList<String[]> dataFromFile) throws Exception{
        if(dataFromFile.size() == 0 || !(dataFromFile.get(0)[0].equals( "Courses"))){
            throw new Exception("The file is empty or not a Courses file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                Course course = new Course(strings[2],strings[0],strings[1],strings[4]);
                if(courses.getCoursesList().size() == 0){
                    Teacher teacher = new Teacher(strings[3],"firstName","lastName",(strings[3]+"@via.dk")," ");
                    if(!(teachers.hasTeacher(teacher))){
                        teachers.addTeacher(teacher);
                    }
                    course.getTeachers().add(teacher.getShortName());
                    for (Student x: students.getStudents() ) {
                        if(course.getClassID().equals(x.getStudentsClass()) && course.getSemester().equals(x.getSemester())){
                            course.getStudents().addStudent(x);
                        }
                    }
                    courses.getCoursesList().add(course);
                }else {
                        if(!(courses.hasCourse(course))) {
                            Teacher teacher = new Teacher(strings[3], "firstName", "lastName", (strings[3] + "@via.dk"), " ");
                            if (!(teachers.hasTeacher(teacher))) {
                                teachers.addTeacher(teacher);
                            }
                            course.getTeachers().add(teacher.getShortName());
                            for (Student x : students.getStudents()) {
                                if (course.getClassID().equals(x.getStudentsClass()) && course.getSemester().equals(x.getSemester())) {
                                    course.getStudents().addStudent(x);
                                }
                            }
                            courses.addCourse(course);
                        }else {
                            Teacher teacher = new Teacher(strings[3],"firstName","lastName",(strings[3]+"@via.dk")," ");
                            for (int k = 0; k < courses.size(); k++) {
                                if (  courses.getCourse(k).getClassID().equals(course.getClassID()) &&
                                        courses.getCourse(k).getSemester().equals(course.getSemester()) &&
                                        courses.getCourse(k).getCourseName().equals(course.getCourseName())){
                                    courses.getCourse(k).getTeachers().add(teacher.getShortName());
                                }
                            }
                            System.out.println("here 2");
                        }


                }

              /*  Teacher teacher = new Teacher(strings[3],"firstName","lastName",(strings[3]+"@via.dk")," ");
                if(!(teachers.hasTeacher(teacher))){
                    teachers.addTeacher(teacher);
                }
                Course course = new Course(strings[2],strings[0],strings[1],strings[4]);
                // check if model.courses list has this course but not this teacher then add this teacher short name to this cours
                for (int j = 0; j < courses.size(); j++) {
                    if(courses.getCourse(j).equals(course)){
                        if(!(courses.getCourse(j).hasTeacher(teacher.getShortName()))){
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
                }*/

            }
        }
    }

    public void setRoomsData( ArrayList<String[]> dataFromFile) throws Exception{
        if(dataFromFile.size() == 0 || !(dataFromFile.get(0)[0].equals( "Rooms"))){
            throw new Exception("The file is empty or not a model.rooms file");
        }else {
            for (int i = 1; i < dataFromFile.size(); i++) {
                String[] strings =  dataFromFile.get(i);
                int capacity = Integer.parseInt(strings[1]);
                if(strings.length == 3){
                    Room room = new Room(capacity,strings[0],strings[2]);
                    // if the room not exist add it to model.rooms
                    if(!(rooms.containRoom(room))){
                       this.rooms.addRoom(room);
                    }
                }else {
                    Room room = new Room(capacity,strings[0]);
                    if(!(rooms.containRoom(room))){
                        rooms.addRoom(room);
                    }
                }

            }
        }
    }



    public static void main(String[] args) {
        Data d = new Data();
        try {
            ArrayList<String[]> data = ReadFromTXTFile.ReadTXTFile("C:\\Users\\drwae\\Downloads\\Rooms.txt");
            try {
                d.setRoomsData(data);
                System.out.println(d.rooms.getRoomsList().get(0).getRoomId());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

