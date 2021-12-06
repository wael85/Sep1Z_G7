package person;

import java.util.ArrayList;

public class Students {
    private ArrayList<Student> students;

    public Students(){
        students = new ArrayList<>();
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void removeStudent(int index){
        students.remove(index);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    @Override
    public String toString(){
        String s = " ";
        for (Student x: students ) {
            s += x.toString();
        }
        return s;
    }
    public boolean hasStudent(Student student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).equals(students)){
                return true;
            }
        }
        return false;
    }
}
