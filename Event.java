import java.time.*;

public class Event {
    private String eventName;
    private boolean isAllDay;
    private LocalDate eventDate;
    private LocalTime eventTime;

    //constructor with date as integers and time as integers
    public Event(String eventName, int year, int month, int dayOfMonth, int hour, int minute) {
       this.eventName = eventName;
       this.eventDate = LocalDate.of(year, month, dayOfMonth);
       this.eventTime = LocalTime.of(hour, minute);
    }

    //constructor with date as LocalDate and time as integers
    public Event(String eventName, LocalDate eventDate, int hour, int minute) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = LocalTime.of(hour, minute);
    }

    //constructor with date as integers and time as LocalTime
    public Event(String eventName, int year, int month, int dayOfMonth, LocalTime eventTime) {
        this.eventName = eventName;
        this.eventDate = LocalDate.of(year, month, dayOfMonth);
        this.eventTime = eventTime;
    }

    //contructor with date as LocalDate and time as Local Time
    public Event(String eventName, LocalDate eventDate, LocalTime eventTime) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    //constructor with date as integers and event being all day
    public Event(String eventName, int year, int month, int dayOfMonth, boolean isAllDay) {
        this.eventName = eventName;
        this.eventDate = LocalDate.of(year, month, dayOfMonth);
        this.isAllDay = isAllDay;
    }

    //constructor with date as LocalDate and event being all day
    public Event(String eventName, LocalDate eventDate, boolean isAllDay) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.isAllDay = isAllDay;
    }

    public void setName(String eventName) {
        this.eventName = eventName;
    }

    public String getName() {
         return this.eventName;
    }

    public void setIsAllDay(boolean isAllDay) {
        this.isAllDay = isAllDay;
    }

    public boolean getIsAllDay() {
        return this.isAllDay;
    }

    //date setter where parameters are integers
    public void setDate(int year, int month, int dayOfMonth) {
        this.eventDate = LocalDate.of(year, month, dayOfMonth);
    }

    //date setter where parameter is a LocalDate object
    public void setDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDate getDate() {
        return this.eventDate;
    }

    //add time setters

    public LocalTime getTime() {
        return this.eventTime;
    }

    public String toString() {
        String output;
        if(isAllDay) {
            output = "Event name: " + eventName + "\nEvent date: " + eventDate + "\nEvent time: All day";
        } else {
            output = "Event name: " + eventName + "\nEvent date: " + eventDate + "\nEvent time: " + eventTime;
        }
        return output;
    }
}