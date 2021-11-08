/*
 * Author: mroge
 * Purpose: The DTO that holds all the DVD info
 */
package com.mrr.dvdlibrary.dto;

// defining what is in a DVD object
public class DVD {
    // declaring private variables for the aspects of a DVD
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String note;
    
    // constructor
    public DVD(String title) {
        this.title = title;
    }
    
    // getter method for title
    public String getTitle() {
        return title;
    }
    
    // setter method for title
    public void setTitle(String title) {
        this.title = title;
    }
   
    // getter method for release date
    public String getReleaseDate() {
        return releaseDate;
    }
    
    // setter methof for release date
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    // getter method for the rating
    public String getMpaaRating() {
        return mpaaRating;
    }
    
    // setter method for the rating
    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    
    // getter method for the director name
    public String getDirectorName() {
        return directorName;
    }
    
    // setter method for the director name
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    // getter method for the studio
    public String getStudio() {
        return studio;
    }
    
    // setter method for the studio
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    // getter method for the note
    public String getNote() {
        return note;
    }
    
    // setter method for the note
    public void setNote(String note) {
        this.note = note;
    }
}
