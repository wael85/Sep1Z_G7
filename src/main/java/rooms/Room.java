package rooms;

import java.util.ArrayList;
import bookedTime.*;

public class Room {

    private ArrayList<BookedTime> bookTime;
    private int roomCapacity;
    private String roomId;
    private String neighborRoom;

    public Room(int roomCapacity, String roomId) {
        this.bookTime = new ArrayList<>();
        this.roomCapacity = roomCapacity;
        this.roomId = roomId;
    }
    public Room(int roomCapacity, String roomId,String neighborRoom) {
         this( roomCapacity, roomId);
         this.neighborRoom = neighborRoom;

    }

    public int getRoomCapacity() {
        return roomCapacity;
    }


    public String getRoomId() {
        return roomId;
    }

    public boolean isFoldable() {
        return !(neighborRoom == null);
    }
    public void addNewBooking(BookedTime bookedTime){
        bookTime.add(bookedTime);
    }

    public void cancelBooking(BookedTime bookedTime){
        bookTime.remove(bookedTime);
    }
    public ArrayList<BookedTime> getBookedDates(){
        return bookTime;
    }
    public boolean isAvailable(BookedTime bookedTime){

        for (BookedTime time:bookTime) {
            DateTime start = time.getStart();
            DateTime end = time.getEnd();

            if ((bookedTime.getStart().isBefore(end) && bookedTime.getStart().isAfter(start))
                    || (bookedTime.getEnd().isBefore(end) && bookedTime.getEnd().isAfter(start))
                    || (bookedTime.getStart().isBefore(start) && bookedTime.getEnd().isAfter(end))
                    || bookedTime.getStart().equals(start) || bookedTime.getEnd().equals(end)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        return roomCapacity == ((Room) o).roomCapacity && roomId.equals(((Room) o).roomId) && neighborRoom.equals(neighborRoom);
    }


    public String toString() {
        return "Room "+ roomId +", room capacity: " + roomCapacity +", foldable: " + neighborRoom +", book time: " + bookTime;
    }
}
