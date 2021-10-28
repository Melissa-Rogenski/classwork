/**
 * @author Melissa Rogenski
 * email: rogenskim@gmail.com
 * date: 10/27/21
 * purpose: Rock, Paper, Scissors
 */

package com.mrr.assessmentbpc;

import java.util.*;

public class RockPaperScissors 
{
    //declaring constants for possible moves: rock, paper, and scissors 
    public static final String ROCK = "Rock";
    public static final String PAPER = "Paper";
    public static final String SCISSORS = "Scissors";
    
    //function to get number of rounds to play from the user
    public static int getRounds()
    {
        System.out.println("Please enter the number of rounds you would like to play between 1 and 10: ");
        int input = 0;
        //initializing scanner
        Scanner in = new Scanner(System.in);
        //checking if input is going to be a int and valid number, if not call closeGame function
        if(in.hasNextInt())
        {
            input = in.nextInt();
            if(input < 1 || input > 10)
                closeGame();
        }
        else
            closeGame();
        
        return input;
    }
    
    //function to get the players move from the user
    public static String getPlayerMove()
    {
        String playerMove = "";
        //initializing scanner
        Scanner in = new Scanner(System.in);
        //checking if input is going to be a int and a valid number, if not call closeGame function
        if(in.hasNextInt())
        {
           int input = in.nextInt();
           if(input == 1)
               playerMove = RockPaperScissors.ROCK;
           else if(input == 2)
               playerMove = RockPaperScissors.PAPER;
           else if(input == 3)
               playerMove = RockPaperScissors.SCISSORS;
           else
               closeGame();
        }
        else
            closeGame();
        
        return playerMove;
    }
    
    //function to get the computers move
    public static String getComputerMove()
    {
        String computerMove = "";
        Random random = new Random();
        //generating random number in range of 1-3
        int input = random.nextInt(3) + 1;
        //assigning computers move according to number
        if(input == 1)
            computerMove = RockPaperScissors.ROCK;
        else if(input == 2)
            computerMove = RockPaperScissors.PAPER;
        else
            computerMove = RockPaperScissors.SCISSORS;
        
        return computerMove;
    }
    
    //function to close the application after printing out error message
    public static void closeGame()
    {
        System.out.println("Invalid Selection, application closing...");
        System.exit(0);
    }
    
    //function to display the score
    public static void displayScore(int tie, int userWin, int compWin)
    {
        System.out.println("Ties: " + tie + 
                "\nUser Wins: " + userWin + 
                "\nCPU Wins: " + compWin + "\n");
    }
    
    //function to display the user and computer choices opposing each other
    public static void display(String user, String comp)
    {
        // rock vs rock
        if(user.equals(ROCK) && comp.equals(ROCK))
        {
            System.out.println("-USER----------------CPU-----------------");
            System.out.println("|    _______        |        _______    |");    
            System.out.println("|---'   ____)       |       (____   '---|");
            System.out.println("|      (_____)      |      (_____)      |");
            System.out.println("|      (_____)      |      (_____)      |");
            System.out.println("|      (____)       |       (____)      |");
            System.out.println("|---.__(___)        |        (___)__.---|");
            System.out.println("-----------------------------------------");     
            System.out.println("This round is a tie!");
        }
        //rock vs paper
        else if(user.equals(ROCK) && comp.equals(PAPER))
        {
            System.out.println("-USER----------------CPU-----------------");
            System.out.println("|    _______        |        _______    |");    
            System.out.println("|---'   ____)       |   ____(____   '---|");
            System.out.println("|      (_____)      |  (______          |");
            System.out.println("|      (_____)      | (_______          |");
            System.out.println("|      (____)       |  (_______         |");
            System.out.println("|---.__(___)        |    (__________.---|");
            System.out.println("-----------------------------------------");
            System.out.println("            Paper wraps Rock");
            System.out.println("CPU Wins this round!");
        }
        //rock vs scissors
        else if(user.equals(ROCK) && comp.equals(SCISSORS))
        {
            System.out.println("-USER----------------CPU-----------------");
            System.out.println("|    _______        |        _______    |");    
            System.out.println("|---'   ____)       |   ____(____   '---|");
            System.out.println("|      (_____)      |  (______          |");
            System.out.println("|      (_____)      | (__________       |");
            System.out.println("|      (____)       |       (____)      |");
            System.out.println("|---.__(___)        |        (___)__.---|");
            System.out.println("-----------------------------------------");
            System.out.println("           Rock breaks Scissors");
            System.out.println("USER Wins this round!");
        }
        //paper vs paper
        else if(user.equals(PAPER) && comp.equals(PAPER))
        {
            System.out.println("-USER----------------CPU-----------------");     
            System.out.println("|    _______        |        _______    |");
            System.out.println("|---'   ____)____   |   ____(____   '---|");
            System.out.println("|          ______)  |  (______          |");    
            System.out.println("|          _______) | (_______          |");
            System.out.println("|         _______)  |  (_______         |");
            System.out.println("|---.__________)    |    (__________.---|");
            System.out.println("-----------------------------------------");
            System.out.println("This round is a tie!");
        }
        //paper vs rock
        else if(user.equals(PAPER) && comp.equals(ROCK))
        {
            System.out.println("-USER----------------CPU-----------------");     
            System.out.println("|    _______        |        _______    |");
            System.out.println("|---'   ____)____   |       (____   '---|");
            System.out.println("|          ______)  |      (_____)      |");    
            System.out.println("|          _______) |      (_____)      |");
            System.out.println("|         _______)  |       (____)      |");
            System.out.println("|---.__________)    |        (___)__.---|");
            System.out.println("-----------------------------------------");
            System.out.println("            Paper wraps Rock");
            System.out.println("USER Wins this round!");
        }
        //paper vs scissors
        else if(user.equals(PAPER) && comp.equals(SCISSORS))
        {
            System.out.println("-USER----------------CPU-----------------");     
            System.out.println("|    _______        |        _______    |");
            System.out.println("|---'   ____)____   |   ____(____   '---|");
            System.out.println("|          ______)  |  (______          |");    
            System.out.println("|          _______) | (__________       |");
            System.out.println("|         _______)  |       (____)      |");
            System.out.println("|---.__________)    |        (___)__.---|");
            System.out.println("-----------------------------------------");
            System.out.println("          Scissors cut Paper");
            System.out.println("CPU Wins this round!");
        }
        //scissors vs scissors
        else if(user.equals(SCISSORS) && comp.equals(SCISSORS))
        {
            System.out.println("-USER----------------CPU-----------------");
            System.out.println("|    _______        |        _______    |");    
            System.out.println("|---'   ____)____   |   ____(____   '---|");
            System.out.println("|          ______)  |  (______          |");
            System.out.println("|       __________) | (__________       |");
            System.out.println("|      (____)       |       (____)      |");
            System.out.println("|---.__(___)        |        (___)__.---|");
            System.out.println("-----------------------------------------");
            System.out.println("This round is a tie!");
        }
        //scissors vs rock
        else if(user.equals(SCISSORS) && comp.equals(ROCK))
        {
            System.out.println("-USER----------------CPU-----------------");
            System.out.println("|    _______        |        _______    |");    
            System.out.println("|---'   ____)____   |       (____   '---|");
            System.out.println("|          ______)  |      (_____)      |");
            System.out.println("|       __________) |      (_____)      |");
            System.out.println("|      (____)       |       (____)      |");
            System.out.println("|---.__(___)        |        (___)__.---|");
            System.out.println("-----------------------------------------");
            System.out.println("           Rock breaks Scissors");
            System.out.println("CPU Wins this round!");
        }
        //scissors vs paper
        else if(user.equals(SCISSORS) && comp.equals(PAPER))
        {
            System.out.println("-USER----------------CPU-----------------");     
            System.out.println("|    _______        |        _______    |");
            System.out.println("|---'   ____)____   |   ____(____   '---|");
            System.out.println("|          ______)  |  (______          |");    
            System.out.println("|          _______) | (_______          |");
            System.out.println("|      (____)       |  (_______         |");
            System.out.println("|---.__(___)        |    (__________.---|");
            System.out.println("-----------------------------------------");
            System.out.println("          Scissors cut Paper");
            System.out.println("USER Wins this round!");
        }
    }
    
    //main function
    public static void main(String[] args)
    {
        //string for the users answer to wether or not they want to play again
        String again = "";
        
        //string variables to store the player and computers moves
        String playerMove;
        String computerMove;
        
        // int variables to store the score variables for the overall game
        int ties;
        int userWins;
        int computerWins;
        
        // int variable rounds to store the number of rounds the player wishes to play
        int rounds;
        
        //do while loop because i want the game to run at least once, and as many times as the user wants to play again
        do
        {
            //assigning blank values the player and computers moves variables
            playerMove = "";
            computerMove = "";
            
            // assigning 0 values to the score variables for the overall game
            ties = 0;
            userWins = 0;
            computerWins = 0;
            
            // filling rounds variable with user input
            rounds = getRounds();
            
            // a for loop going from 0-the number of rounds entered by the user
            for(int i = 0; i < rounds; i++)
            {
                //displaying what round of the game is being played
                System.out.println("\nRound " + (i+1));
                
                //asking user for input on what move they want to use
                System.out.println("Please enter any one of the following inputs:");
                System.out.println("1 = Rock");
                System.out.println("2 = Paper");
                System.out.println("3 = Scissors");
                
                // assigning the returned value of the getPlayerMove function to the playerMove variable
                playerMove = getPlayerMove();
                // assigning the returned value of the getComputerMove function to the computerMove variable
                computerMove = getComputerMove();
                
                //printing extra line for formatting purposes
                System.out.println();
                
                // calling display to print out visual representation of the moves selected by the user and the computer
                display(playerMove, computerMove);
                
                // checking if the user and the computer used the same move
                if(playerMove.equals(computerMove))
                    ties++;
                //checking if the USER has Rock and CPU has paper OR USER has Paper and CPU has Scissors OR USER has Scissors and CPU has Rock
                else if(((playerMove.equals(RockPaperScissors.ROCK))&&(computerMove.equals(RockPaperScissors.PAPER)))
                        ||((playerMove.equals(RockPaperScissors.PAPER))&&(computerMove.equals(RockPaperScissors.SCISSORS)))
                        ||((playerMove.equals(RockPaperScissors.SCISSORS))&&(computerMove.equals(RockPaperScissors.ROCK))))
                    computerWins++;
                //otherwise USER wins
                else
                    userWins++;
                
                
            }    
            
            System.out.println("\n---------------FINAL-SCORE---------------")
            //calling the displayScore function with the completed tallies of ties userWins and computerWins
            displayScore(ties, userWins, computerWins);
                
            //calculating who won the overall game based on the tallies 
            //if the user win count is the same as the computer win count then it is a tie
            if(userWins == computerWins)
                System.out.println("This game is a tie!");
            //if the user has more wins than the computer has wins, then the user wins the game
            else if(userWins > computerWins)
                System.out.println("The User wins this game!");
            //only option left is that the computer has more wins than the user so the computer wins the game
            else
                System.out.println("The Computer wins this game!");       
            
            //asking the user if they would like to play again
            System.out.println("\nWould you like to play again? Yes/No");
            //initializing scanner
            Scanner in = new Scanner(System.in);
            //capturing string from user input to determine if they would like to play again or not
            again = in.nextLine();
        }
        //if the users input to continue was yes or y of and capitalization then the loop will repeat, otherwise it will end the loop
        while(again.equalsIgnoreCase("yes") || again.equalsIgnoreCase("y"));
        
        //final message from application to console thanking the user for playing
        System.out.println("Thanks for playing!");
    }
}
