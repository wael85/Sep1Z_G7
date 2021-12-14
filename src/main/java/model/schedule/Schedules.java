package model.schedule;


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
    public Schedule getScheduleById(String Id){
        for (int i = 0; i < schedules.size(); i++) {
            if(schedules.get(i).getId().equals(Id)){
                return schedules.get(i);
            }
        }
        return null;
    }
    public Schedule getSchedule(Schedule schedule){
        for (int i = 0; i < schedules.size(); i++) {
           if(schedules.get(i).getId().equals(schedule.getId())){
               return schedules.get(i);
           }
        }
        return null;
    }
    public void deleteSchedule(int index){
        schedules.remove(index);
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "schedules=" + schedules +
                '}';
    }
}
