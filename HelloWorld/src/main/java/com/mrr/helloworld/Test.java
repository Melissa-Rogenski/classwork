/**
 * @author Melissa Rogenski
 * email: rogenskim@gmail.com
 * date: 10/26/21
 * purpose: Just checking my answers for a quiz
 */

package com.mrr.helloworld;

public class Test {
    public static void main (String[] args) {
        boolean definitelyFalse = false;
        boolean veryTrue = true;
	System.out.println("!definitelyFalse || !veryTrue = " + (!definitelyFalse || !veryTrue));
        System.out.println("definitelyFalse != veryTrue = " + (definitelyFalse != veryTrue));
        System.out.println("definitelyFalse || !definitelyFalse = " + (definitelyFalse || !definitelyFalse));
        System.out.println("veryTrue && !veryTrue = " + (veryTrue && !veryTrue));
    
        System.out.println("veryTrue || definitelyFalse = " + (veryTrue || definitelyFalse));
        System.out.println("veryTrue && definitelyFalse = " + (veryTrue && definitelyFalse));
    
        int start = 5;
        int stop = 26;

        for(int i = start; i < stop; i++){
            System.out.println("beep!");
        }

    }
}