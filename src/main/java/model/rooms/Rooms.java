package model.rooms;

import model.bookedTime.BookedTime;

import java.util.ArrayList;

public class Rooms {

    private Room[] rooms;
    private int size;

    public Rooms(int size){
        this.rooms=new Room[size];
    }
    public ArrayList<Room> getRoomsList(){
        ArrayList<Room>roomsList=new ArrayList<>();
        for (int i=0;i< rooms.length;i++){
            if (!(rooms[i]==null)){
                roomsList.add(rooms[i]);
            }
        }
        return roomsList;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void setRooms(Room[] rooms) {
        this.rooms = rooms;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addRoom(Room room){
        for (int i=0;i<rooms.length;i++){
            if ((rooms[i]==null)){
                rooms[i]=room;
                break;
            }
        }
    }
    public ArrayList<Room> getAvailableRooms(BookedTime bookedTime){
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (int i = 0; i < getRoomsList().size() ; i++) {
            if(getRoomsList().get(i).isAvailable(bookedTime)){
                availableRooms.add(getRoomsList().get(i));
            }
        }
        return availableRooms;
    }
    public boolean containRoom(Room room){
        for (int i = 0; i < getRoomsList().size(); i++) {
            if (getRoomsList().get(i).equals(room)){
                return true;
            }
        }
        return false;
    }




}
