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
    public int getDaysInMonth(int month, int year) {
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


    public static void main(String[] args) {
      //display calendar (4 weeks, each sunday to saturday, where current week is first week displayed)
      LocalDate today = LocalDate.now();
      int todayDayOfWeek = today.getDayOfWeek().getValue();
      int todayDayOfMonth = today.getDayOfMonth();
      int todayMonth = today.getMonthValue();
      int todayYear = today.getYear();

      int startingDate = 1;
    
      for(int i = 0; i < 4; i++){
        System.out.println("+----------+----------+----------+----------+----------+----------+----------+");

        for(int j = 0; j < 7; j++) {
            System.out.print("| " + getThreeLetterDay(j + 1) + " " + (startingDate + j));
            if((startingDate + j) < 10) {
                System.out.print("    "); //4 spaces
            } else {
                System.out.print("   "); //3 spaces
            }
        }
        System.out.println("|");

        System.out.println("|==========|==========|==========|==========|==========|==========|==========|");
        
        //output events here
        System.out.println("|          |          |          |          |          |          |          |");

        startingDate += 7;
      }
      
      System.out.println("+----------+----------+----------+----------+----------+----------+----------+");

      /*
      Event myevent = new Event("my event", 3, 25);
      LocalTime thisTime = myevent.getTime();
      System.out.println(thisTime);
      */
    }
}