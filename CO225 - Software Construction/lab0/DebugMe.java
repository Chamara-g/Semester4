/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chanaka
 */
public class DebugMe {
    public static void main (String[] args)
    {
        for ( int i = 1 ; i< 20 ; i++)
        {
            System.out.println("count ="+i);
            System.out.println("--------");
            ShowCurrentTime();
        }
    }
    
    public static void ShowCurrentTime() 
    {
        // Obtain the total milliseconds since the midnight, Jan 1, 1970
        long totalMilliseconds = System.currentTimeMillis();

        // Obtain the total seconds since the midnight, Jan 1, 1970
        long totalSeconds = totalMilliseconds / 1000;

        // Compute the current second in the minute in the hour
        int currentSecond = (int)(totalSeconds % 60);

        // Obtain the total minutes
        long totalMinutes = totalSeconds / 60;
        
        // Compute the current minute in the hour
        int currentMinute = (int)(totalMinutes % 60);

       // Obtain the total hours
        long totalHours = totalMinutes / 60;

       // Compute the current hour
        int currentHour = (int)(totalHours % 24);

        // Display results
        System.out.println("Current time is " + currentHour + ":"
                        + currentMinute + ":" + currentSecond + " GMT");

    }
    
}
