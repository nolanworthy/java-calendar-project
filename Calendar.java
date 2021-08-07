import java.time.*;

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

    /* printCalendar: takes two LocalDate objects: one representing this first day of
        today's week (weeks are Sun-Sat) and one representing today. prints a 5-week
        calendar starting on the first day of today's week. */
    public static void printCalendar(LocalDate today) {
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
          
          //output events here
          System.out.println("|          |          |          |          |          |          |          |");
        }
        
        System.out.println("+----------+----------+----------+----------+----------+----------+----------+");

    }


    public static void main(String[] args) {
      LocalDate today = LocalDate.now(); //today
      //LocalDate today = LocalDate.of(2021, 6, 1); //a custom day (year,month,day of month)
      /*
      int todayDayOfWeek = today.getDayOfWeek().getValue();
      int todayDayOfMonth = today.getDayOfMonth();
      int todayMonth = today.getMonthValue();
      int todayYear = today.getYear();
      */

      printCalendar(today);

      /*
      Event myevent = new Event("my event", 3, 25);
      LocalTime thisTime = myevent.getTime();
      System.out.println(thisTime);
      */
    }
}