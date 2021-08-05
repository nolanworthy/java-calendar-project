import java.time.*;

public class Event {
    private String eventName;
    private boolean isAllDay;
    private LocalDate eventDate;
    private LocalTime eventTime;

    public Event(String eventName, int hour, int minute) {
       this.eventName = eventName;
       this.eventTime = LocalTime.of(hour, minute);
    }

    public String getName() {
         return eventName;
    }

    public LocalTime getTime() {
        return eventTime;
    }
}