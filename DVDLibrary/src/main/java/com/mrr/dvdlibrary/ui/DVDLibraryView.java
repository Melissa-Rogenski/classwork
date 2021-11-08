/*
 * Author: mroge
 * Purpose: the class that handles all UI logic
 */
package com.mrr.dvdlibrary.ui;

import com.mrr.dvdlibrary.dto.DVD;
import java.util.List;

// defining what a DVDLibraryView is
public class DVDLibraryView {
    
    // private variable of type UserIO
    private UserIO io;
    
    // constructor
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    // public function to print the menu and return the selection in int format
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs in Library");
        io.print("2. Add a DVD to Library");
        io.print("3. Remove a DVD");
        io.print("4. Edit a DVD");
        io.print("5. View a DVD");
        io.print("6. Exit");

        // getting user input
        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    // public function that prompts user for new DVD info and returns a DVD object with the user entered info
    public DVD getNewDVDInfo() {
        // prompting user and capturing input
        String title = io.readString("Please enter DVD Title");
        String releaseDate = io.readString("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter Studio");
        String note = io.readString("Please enter note");
        
        // creating dvd and setting values to user input data
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        // returning the completed dvd object
        return currentDVD;
    }
    
    // public function that prints a banner to the console
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    
    // public function that prints a banner to the console
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue.");
    }
    
    // public function that displays all of the dvds in the library, takes parameter List of dvds
    public void displayDVDList(List<DVD> dvdList) {
        // for each to loop through all dvds
        for(DVD currentDVD : dvdList) {
            // formatting information into a string
            String dvdInfo = String.format("Title: %s | Director(s): %s | Release Date: %s",
                    currentDVD.getTitle(),
                    currentDVD.getDirectorName(),
                    currentDVD.getReleaseDate());
            // passing string to function to print out the info
            io.print(dvdInfo);
        }
        // prompting user for input to continue
        io.readString("Please hit enter to continue.");
    }
    
    // public function that prints a banner to the console
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    // public function that prints a banner to the console
    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD ===");
    }

    // public function to request and save input from the user
    // returns a type string
    public String getTitleChoice() {
        // prompting user for title and returning it
        return io.readString("Please enter the Title.");
    }

    // public function to display a specified dvd, takes one parameter dvd
    public void displayDVD(DVD dvd) {
        // checking if the dvd exists in the library otherwise notifying user that no such dvd exists in the library
        if (dvd != null) {
            // if dvd is found, all info on that dvd is printed
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getNote());
            io.print("");
        } else {
            io.print("No such dvd.");
        }
        // prompting user for input to continue
        io.readString("Please hit enter to continue.");
    }

    // public function that prints a banner to the console
    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }

    // public function to display wiether or not a dvd was successfully removed takes one parameter dvd
    public void displayRemoveResult(DVD dvdRecord) {
        // if the dvdrecord is found print out it has been successfully removed otherwise state that no such dvd exists
        if(dvdRecord != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such dvd.");
        }
        // proimpting user for input to continue
        io.readString("Please hit enter to continue.");
    }
    
    // public function that prints a banner to the console
    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }
    
    // public function that prints a banner to the console
    public void displayEditSuccessBanner() {
        // prompting user for input to continue
        io.readString("DVD successfully edited.  Please hit enter to continue.");
    }
    
    // public function that prints a banner to the console
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    // public function that prints a banner to the console
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    // public function that prints a banner to the console
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
