import java.time.LocalDate;
import java.time.LocalTime;
//import java.time.DayOfWeek;

public class Calendar {
    /* getThreeLetterDay: takes an integer 0-6 and returns
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

    public static void printCalendar(int firstDateOfMonth, int daysInFirstMonth) {
        int firstDateOfWeek = firstDateOfMonth;
    
        for(int i = 0; i < 4; i++){
          System.out.println("+----------+----------+----------+----------+----------+----------+----------+");
  
          for(int j = 0; j < 7; j++) {
              if(firstDateOfWeek + j > daysInFirstMonth) {
                  firstDateOfWeek = 1 - j;
              }
              System.out.print("| " + getThreeLetterDay(j + 1) + " " + (firstDateOfWeek + j));
              if((firstDateOfWeek + j) < 10) {
                  System.out.print("    "); //4 spaces
              } else {
                  System.out.print("   "); //3 spaces
              }
          }
          System.out.println("|");
  
          System.out.println("|==========|==========|==========|==========|==========|==========|==========|");
          
          //output events here
          System.out.println("|          |          |          |          |          |          |          |");
  
          firstDateOfWeek += 7;
        }
        
        System.out.println("+----------+----------+----------+----------+----------+----------+----------+");

    }


    public static void main(String[] args) {
      //display calendar (4 weeks, each sunday to saturday, where current week is first week displayed)
      //LocalDate today = LocalDate.now(); //today
      LocalDate today = LocalDate.of(2021, 8, 25); //another day (year,month,day of month)
      int todayDayOfWeek = today.getDayOfWeek().getValue();
      int todayDayOfMonth = today.getDayOfMonth();
      int todayMonth = today.getMonthValue();
      int todayYear = today.getYear();

      int firstDateOnCalendar = todayDayOfMonth - todayDayOfWeek;
      //System.out.println(firstDateOnCalendar + " = " + todayDayOfMonth + " - " + todayDayOfWeek);
      int daysInFirstMonth;
      if(firstDateOnCalendar < 1) { //if calendar is starting in previous month
        if(todayMonth == 1) {
            daysInFirstMonth = getDaysInMonth(todayMonth - 1, todayYear - 1);
        } else {
            daysInFirstMonth = getDaysInMonth(todayMonth - 1, todayYear);
        }
        printCalendar(daysInFirstMonth + firstDateOnCalendar, daysInFirstMonth);
      } else {
          daysInFirstMonth = getDaysInMonth(todayMonth, todayYear);
          printCalendar(firstDateOnCalendar, daysInFirstMonth);
      }

      /*
      Event myevent = new Event("my event", 3, 25);
      LocalTime thisTime = myevent.getTime();
      System.out.println(thisTime);
      */
    }
}