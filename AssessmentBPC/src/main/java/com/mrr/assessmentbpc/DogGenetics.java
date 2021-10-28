/**
 * @author Melissa Rogenski
 * email: rogenskim@gmail.com
 * date: 10/28/21
 * purpose: DogGenetics
 */

package com.mrr.assessmentbpc;

import java.util.*;

public class DogGenetics 
{
    public static void main (String[] args) 
    {
        //declaring variable sum of type int
        int sum = 0;
        
        //initializing scanner
        Scanner in = new Scanner(System.in);
        
        //initializing random
        Random dogBreed = new Random();
        
        //declaring strings for my 5 dog breeds, could also have a array of
        //string dog breeds and have random pick 5 unique of them
        String breed1 = "Poodle";
        String breed2 = "Australian Shepard";
        String breed3 = "Dalmation";
        String breed4 = "Labrador Retriever";
        String breed5 = "German Shepard";
        
        //declaring string vasriable for dogs name
        String dogName = "";
        
        //declaring percentage variables and assigning 0 to them
        int p1, p2, p3, p4, p5 = 0;
        
        //prompting user for dog name
        System.out.println("What is your dog's name? ");
        dogName = in.nextLine();
        
        //informing user of dogs name and that we have genetic results
        System.out.println("Well then, I have this highly reliable report on "
                          + dogName + "'s prestigious background right here.\n ");
       
        //do while because i want to run it at least one time, and keep trying till i get sum of 100
        do
        {
            // will generate random numbers in range of 1-100 inclusively
            p1 = (dogBreed.nextInt(100) + 1);
            p2 = (dogBreed.nextInt(100) + 1);
            p3 = (dogBreed.nextInt(100) + 1);
            p4 = (dogBreed.nextInt(100) + 1);
            p5 = (dogBreed.nextInt(100) + 1);
            sum = p1 + p2 + p3 + p4 + p5;
        }
        while(sum != 100);

        //printing int information
        System.out.println(dogName + " is: \n");
        System.out.println(p1 + "% " + breed1);
        System.out.println(p2 + "% " + breed2);
        System.out.println(p3 + "% " + breed3);
        System.out.println(p4 + "% " + breed4);
        System.out.println(p5 + "% " + breed5);
        System.out.println("\nWow, that's QUITE the dog!");
    }
}
