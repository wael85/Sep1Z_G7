package schedule;


import java.util.ArrayList;

public class Schedules {
    private ArrayList<Schedule> schedules;

    public Schedules(){

        schedules = new ArrayList<>();
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
    }
    public boolean containsSchedule(Schedule schedule){
        for (int i = 0; i < schedules.size(); i++) {
            if(schedules.get(i).equals(schedule)){
                return true;
            }
        }
        return false;
    }
    public void deleteSchedule(int index){
        schedules.remove(index);
    }
}
