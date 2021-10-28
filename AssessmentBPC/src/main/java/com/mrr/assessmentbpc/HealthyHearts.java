/**
 * @author Melissa Rogenski
 * email: rogenskim@gmail.com
 * date: 10/28/21
 * purpose: HealthyHearts
 */

package com.mrr.assessmentbpc;

import java.util.*;

public class HealthyHearts 
{
    //method for calculating and displaying maximum heartrate given int age
    public static void printMaxHeartRate(int age)
    {
        //calculating and displaying
        System.out.println("Your maximum heart rate should be " + (220-age) + " beats per minute");
        //calling printHRZone method
        printHRZone(220-age);
    }
    
    //method for calculating and displaying HR Zone given int maxHr
    public static void printHRZone(int maxHr)
    {
        System.out.println("Your target HR Zone is " + Math.round((maxHr * 0.50)) + " - "
                + Math.round((maxHr * 0.85)) + " beats per minute");
    }
    
    public static void main (String[] args) 
    {
        //initializing scanner
        Scanner in = new Scanner(System.in);
        
        
        //prompting user for age and calling my getMaxHeartRate function
        System.out.println("What is your age? ");
        printMaxHeartRate(in.nextInt());
    }
}    