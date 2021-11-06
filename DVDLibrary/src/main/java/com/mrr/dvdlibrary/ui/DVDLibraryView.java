/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrr.dvdlibrary.ui;

import com.mrr.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author mroge
 */
public class DVDLibraryView {
    
    private UserIO io;
    
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs in Library");
        io.print("2. Add a DVD to Library");
        io.print("3. Remove a DVD");
        io.print("4. Edit a DVD");
        io.print("5. View a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter DVD Title");
        String releaseDate = io.readString("Please enter Release Date");
        String mpaaRating = io.readString("Please enter MPAA Rating");
        String directorName = io.readString("Please enter Director's Name");
        String studio = io.readString("Please enter Studio");
        String note = io.readString("Please enter note");
        DVD currentDVD = new DVD(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setNote(note);
        return currentDVD;
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue.");
    }
    
    public void displayDVDList(List<DVD> dvdList) {
        for(DVD currentDVD : dvdList) {
            String dvdInfo = String.format("Title: %s | Director(s): %s | Release Date: %s",
                    currentDVD.getTitle(),
                    currentDVD.getDirectorName(),
                    currentDVD.getReleaseDate());
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayDisplayDvdBanner () {
        io.print("=== Display DVD ===");
    }

    public String getTitleChoice() {
        return io.readString("Please enter the Title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
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
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDvdBanner () {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such dvd.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayEditDvdBanner () {
        io.print("=== Edit DVD ===");
    }
    
    public void displayEditSuccessBanner() {
        io.readString("DVD successfully edited.  Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
}
