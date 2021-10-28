/**
 * @author Melissa Rogenski
 * email: rogenskim@gmail.com
 * date: 10/28/21
 * purpose: SummativeSums
 */

package com.mrr.assessmentbpc;

import java.util.*;

public class SummativeSums 
{
    //method that adds the values of all elements of an array together and 
    //returns that sum
    public static int arrayAddition(int[] arr)
    {
        //declaring variable sum of type int
        int sum = 0;
        
        //a for each so i dont care what the array size is
        for(int i: arr)
        {
            sum += i;
        }
        //return statement
        return sum;
    }
    
    public static void main (String[] args) 
    {
        //declaring my predetermined arrays
        int arr1[] = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int arr2[] = {999, -60, -77, 14, 160, 301};
        int arr3[] = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
            140, 150, 160, 170, 180, 190, 200, -99};
        
        //declaring sum variables for each array
        int sum1, sum2, sum3 = 0;
        
        //calling arrayAddition function
        sum1 = arrayAddition(arr1);
        sum2 = arrayAddition(arr2);
        sum3 = arrayAddition(arr3);
        
        //displaying results
        System.out.println("#1 Array Sum: " + sum1);
        System.out.println("#2 Array Sum: " + sum2);
        System.out.println("#3 Array Sum: " + sum3);            
    }
}    