package model.bookedTime;

public class BookedTime {
    private DateTime start;
    private DateTime end;

    public BookedTime(DateTime startTime, DateTime endTime){
        this.start=startTime;
        this.end=endTime;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime startTime) {
        this.start = startTime;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime endTime) {
        this.end = endTime;
    }

    @Override
    public String toString() {
        return  start +":" + end;
    }
}
