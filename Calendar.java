import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

//import jdk.internal.icu.lang.UCharacter.NumericType;

public class Calendar {
    /* getThreeLetterDay: takes an integer 1-7 and returns
        a 3-letter string representing the corresponding day
        of the week. 1 = Sun, 2 = Mon, etc.
        could be implemented as enum instead(?) */
    public static String getThreeLetterDay(int day) {
        String threeLetters;
        switch(day) {
            case 1:
                threeLetters = "Sun";
                break;
            case 2:
                threeLetters = "Mon";
                break;
            case 3:
                threeLetters = "Tue";
                break;
            case 4:
                threeLetters = "Wed";
                break;
            case 5:
                threeLetters = "Thu";
                break;
            case 6:
                threeLetters = "Fri";
                break;
            case 7:
                threeLetters = "Sat";
                break;
            default:
                threeLetters = "NAD"; //error: Not A Day
        }
        return threeLetters;
    }

    /* getDaysInMonth: takes a month and year as integers and returns
        the number of days in that month, accounting for leap years */
    public static int getDaysInMonth(int month, int year) {
        int daysInMonth = 0;
        if(month == 2) { //february
            //checking for leap year
            if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ) {
                daysInMonth = 29;
            } else {
                daysInMonth = 28;
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) { //april, june, september, november
            daysInMonth = 30;
        } else { //all the other months
            daysInMonth = 31;
        }

        return daysInMonth;
    }

    /* printCalendar: takes a LocalDate object representing today and
        prints a 5-week calendar starting on the first day of today's week. */
    public static void printCalendar(LocalDate today, ArrayList<Event> eventList) {
        //determine the first date to be displayed on calendar
        int firstDateOnCalendar = today.getDayOfMonth() - today.getDayOfWeek().getValue();
        int firstMonthOnCalendar;
        int firstYearOnCalendar;
        if(firstDateOnCalendar < 1) { //if calendar is starting in month previous to today's month
            if(today.getMonthValue() == 1) {
                firstYearOnCalendar = today.getYear() - 1;
                firstMonthOnCalendar = 12;
            } else {
                firstYearOnCalendar = today.getYear();
                firstMonthOnCalendar = today.getMonthValue() - 1;
            }
            firstDateOnCalendar = getDaysInMonth(firstMonthOnCalendar, firstYearOnCalendar) + firstDateOnCalendar;
        } else {
            firstMonthOnCalendar = today.getMonthValue();
            firstYearOnCalendar = today.getYear();
        }
        LocalDate currentDay = LocalDate.of(firstYearOnCalendar, firstMonthOnCalendar, firstDateOnCalendar);
        LocalDate dayAtBeginningOfWeek = currentDay;
    
        //print header at top of calendar with today's month and year
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.print("|                                   " + today.getMonthValue() + "/" + today.getYear());
        if(today.getMonthValue() < 10) {
            System.out.println("                                   |"); //35 spaces
        } else {
            System.out.println("                                  |"); //34 spaces
        }

        for(int i = 0; i < 5; i++){
          System.out.println("+----------+----------+----------+----------+----------+----------+----------+");
  
          dayAtBeginningOfWeek = currentDay;
          for(int j = 0; j < 7; j++) {
              if(currentDay.equals(today)) {
                System.out.print("| > " + getThreeLetterDay(j + 1) + " " + currentDay.getDayOfMonth());
                if(currentDay.getDayOfMonth() < 10) {
                    System.out.print("  "); //2 spaces
                } else {
                    System.out.print(" "); //1 space
                }  
              } else {
                System.out.print("| " + getThreeLetterDay(j + 1) + " " + currentDay.getDayOfMonth());
                if(currentDay.getDayOfMonth() < 10) {
                    System.out.print("    "); //4 spaces
                } else {
                    System.out.print("   "); //3 spaces
                }  
              }
              
              //if the next day is past the end of the month, adequately adjust current year, month, and day. else, increment currentDay by 1 day.
              if(currentDay.getDayOfMonth() + 1 > getDaysInMonth(currentDay.getMonthValue(), currentDay.getYear())) {
                if(currentDay.getMonthValue() == 12) {
                    currentDay = LocalDate.of(currentDay.getYear() + 1, 1, 1);
                } else {
                    currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonthValue() + 1, 1); 
                }
              } else {
                currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonthValue(), currentDay.getDayOfMonth() + 1);
              }
          }
          System.out.println("|");
  
          System.out.println("|==========|==========|==========|==========|==========|==========|==========|");
          
          //output events
          currentDay = dayAtBeginningOfWeek;
          for(int j = 0; j < 7; j++) {
            int numOfEventsCurrentDay = 0;
            System.out.print("| ");
            for(int k = 0; k < eventList.size(); k++) {
                if(eventList.get(k).getDate().equals(currentDay)) {
                    if(numOfEventsCurrentDay < 2) {
                        System.out.print("* ");
                    }
                    numOfEventsCurrentDay++;
                }
            }

            if(numOfEventsCurrentDay > 2) {
                System.out.print("...  ");
            } else {
                for(int k = 0; k < (9 - (numOfEventsCurrentDay * 2)); k++) {
                    System.out.print(" ");
                }
            }
            
            //if the next day is past the end of the month, adequately adjust current year, month, and day. else, increment currentDay by 1 day.
            if(currentDay.getDayOfMonth() + 1 > getDaysInMonth(currentDay.getMonthValue(), currentDay.getYear())) {
              if(currentDay.getMonthValue() == 12) {
                  currentDay = LocalDate.of(currentDay.getYear() + 1, 1, 1);
              } else {
                  currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonthValue() + 1, 1); 
              }
            } else {
              currentDay = LocalDate.of(currentDay.getYear(), currentDay.getMonthValue(), currentDay.getDayOfMonth() + 1);
            }
          }
          System.out.println("|");
        }
        
        System.out.println("+----------+----------+----------+----------+----------+----------+----------+");

    }


    public static void main(String[] args) {
      LocalDate today = LocalDate.now(); //today
      //LocalDate today = LocalDate.of(2021, 6, 1); //a custom day for testing (year,month,day of month)
      Scanner keyboard = new Scanner(System.in);
      ArrayList<Event> calEvents = new ArrayList<Event>();
      int userInput = -1;
      String inputName = "", inputDate = "", inputIsAllDay = "", inputTime = "";
      LocalDate inputLD = LocalDate.now();
      boolean dateInputCorrect = false, isAllDayInputCorrect = false, timeInputCorrect = false;

      printCalendar(today, calEvents);
      System.out.print("\n1: View events\t2: Add event\t3: Delete event\t\tAny other value: Quit\nEnter number: ");
      if(keyboard.hasNextInt()) {
        userInput = Integer.parseInt(keyboard.nextLine()); //call nextLine() and parse as int in order to resolve error when calling nextInt()
        while(userInput == 1 || userInput == 2 || userInput == 3) {
            if(userInput == 1) {
                //view events
                inputDate = "";
                inputLD = LocalDate.now();
                dateInputCorrect = false;

                System.out.print("Enter date you wish to view (YYYY-MM-DD): ");
                inputDate = keyboard.nextLine();
                while(!dateInputCorrect) {
                    try {
                        inputLD = LocalDate.parse(inputDate);
                        dateInputCorrect = true;
                    } catch(DateTimeParseException e) {
                        System.out.print("Invalid date. Try again: ");
                        inputDate = keyboard.nextLine();
                    }
                }
                for(int i = 0; i < calEvents.size(); i++) {
                    if(calEvents.get(i).getDate().equals(inputLD)) {
                        System.out.println(calEvents.get(i));
                        System.out.println();
                    }
                }
            } else if (userInput == 2) {
                //add event
                inputName = "";
                inputIsAllDay = "";
                inputDate = "";
                inputLD = LocalDate.now();
                dateInputCorrect = false;
                isAllDayInputCorrect = false;
                timeInputCorrect = false;

                System.out.println("\nEnter the following information about your new event.");
                System.out.print("Event name: ");
                inputName = keyboard.nextLine();

                System.out.print("Event date (YYYY-MM-DD): ");
                inputDate = keyboard.nextLine();
                while(!dateInputCorrect) {
                    try {
                        inputLD = LocalDate.parse(inputDate);
                        dateInputCorrect = true;
                    } catch(DateTimeParseException e) {
                        System.out.print("Invalid date. Try again: ");
                        inputDate = keyboard.nextLine();
                    }
                }

                System.out.print("Is this an all-day event? ('y' or 'n'): ");
                inputIsAllDay = keyboard.nextLine().toLowerCase();
                while(!isAllDayInputCorrect) {
                    if(inputIsAllDay.equals("y") || inputIsAllDay.equals("yes")) {
                        //if the new event is all day
                        calEvents.add(new Event(inputName, inputLD, true));
                        System.out.println("Event \"" + inputName + "\" added to calendar.");
                        isAllDayInputCorrect = true;
                    } else if(inputIsAllDay.equals("n") || inputIsAllDay.equals("no")) {
                        //if the new event is not all day
                        System.out.print("Event time: ");
                        //calEvents.add(new Event(name, date, time));
                        System.out.println("Event \"" + inputName + "\" added to calendar.");
                        isAllDayInputCorrect = true;
                    } else {
                        System.out.print("Invalid input. Try again: ");
                        inputIsAllDay = keyboard.nextLine().toLowerCase();
                    }
                }
                
            } else {
                //delete event
                //select date to delete from
            }

            System.out.println();
            printCalendar(today, calEvents);
            System.out.print("\n1: View events\t2: Add event\t3: Delete event\t\tAny other value: Quit\nEnter number: ");
            if(keyboard.hasNextInt()) {
                userInput = Integer.parseInt(keyboard.nextLine()); //call nextLine() and parse as int in order to resolve error when calling nextInt()
            } else {
                break;
            }
          }
      }


      /*
      Event myevent = new Event("my event", 3, 25);
      LocalTime thisTime = myevent.getTime();
      System.out.println(thisTime);
      */
      keyboard.close();
    }
}