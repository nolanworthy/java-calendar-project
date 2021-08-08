# java-calendar-project
Calendar program with ability to create, view, and delete events.

The calendar displays 5 weeks beginning with the current week. The current date is indicated by a `>` next to the day of the week and date. Users select actions by inputting
integers (1: View events, 2: Add event, 3: Delete event). Events are stored as name, date, time, and a boolean representing if the event is all day (time is not stored in
this case). Each event is represented by a `*` on its corresponding date. Where there are more events on a given date than can be shown on the calendar, `...` indicates this.

This was a Java practice project.

### Downloading and running
Download both **Calendar.java** and **Event.java** to the same directory. Compile with `javac Calendar.java Event.java`. Run with `java Calendar`.
