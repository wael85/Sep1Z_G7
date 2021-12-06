package person;

import java.util.ArrayList;

public class Teachers {
    private ArrayList<Teacher> teachers;

    public Teachers(){
        teachers = new ArrayList<>();
    }
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
    public void removeTeacher(int index){
        teachers.remove(index);
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public boolean hasTeacher(Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).equals(teacher)){
                return true;
            }
        }
        return false;
    }
    public Teacher getTeacherByShortName(String shorName){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getShortName().equals(shorName)){
                return teachers.get(i);
            }
        }
        return null;
    }
}
