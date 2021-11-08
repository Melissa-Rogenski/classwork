/*
 * Author: mroge
 * Purpose: This file implements the DVDLibraryDAO interface
 * The text file-specific implementation of the DVDLibraryDao interface
 */
package com.mrr.dvdlibrary.dao;

import com.mrr.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


//implements keyword indicates this class implements the specified interface
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    // creating a String variable that is public static final, 
    // this variable holds the file name that is to serve as a database for the DVDs
    public static final String LIBRARY_FILE = "library.txt";
    // creating a String variable that is public static final, 
    // this variable holds the delimiter that is to be used to marshall and unmarshall the data
    public static final String DELIMITER = "::";
    
    // creating a private HashMap that has a key string and holds DVDs
    private Map<String, DVD> dvds = new HashMap<>();
    
    // public function to add a dvd to the library, takes two parameters: a String and a DVD; returns type DVD
    // throws DVDLibraryDaoException
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        // call to loadLibrary method
        loadLibrary();
        // declaring a dvd and calling the put function
        DVD prevDvd = dvds.put(title, dvd);
        // call to writeLibrary method
        writeLibrary();
        // returning the dvd
        return prevDvd;
    }

    // public function that gets all of the dvds in the library, returns type List<DVD>
    // throws DVDLibraryDaoException
    @Override
    public List<DVD> getAllDvds() throws DVDLibraryDaoException {
        // calling loadLibrary function
        loadLibrary();
        // returning an Arraylist of dvds
        return new ArrayList(dvds.values());
    }

    // public function that gets a specific dvd from the library
    // has one parameter a String
    // returns type DVD
    // throws DVDLibraryDaoException
    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        // calling loadLibrary function
        loadLibrary();
        // returning the dvd object with the specified title
        return dvds.get(title);
    }

    // public function that removes a specified DVD from the library
    // has one parameter, a string
    // returns type DVD
    // throws DVDLibraryDaoException
    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        // calling loadLibraryFunction
        loadLibrary();
        // declaring a DVD and calling the remove function
        DVD removedDvd = dvds.remove(title);
        // calling the writeLibrary function
        writeLibrary();
        // returning the removedDvd
        return removedDvd;
    }
    
    //public function that edits a specidfied DVD from the library
    //has two parameters: String and DVD
    // returns type DVD
    // throws DVDLibraryDaoException
    @Override
    public DVD editDVD(String title, DVD dvd)  throws DVDLibraryDaoException {
        // calling loadLibrary function
        loadLibrary();
        // calling the remove function
        dvds.remove(title);
        // declaring a dvd and calling the put function
        DVD prevDvd = dvds.put(title, dvd);
        // calling the writeLibrary function
        writeLibrary();
        // returning the edited dvd
        return prevDvd;
    }
    
    // method to unmarshall the String passed to it in its parameters
    private DVD unmarshallDVD(String dvdAsText){
        // dvdAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // title::release date::rating::director name::studio::note
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in dvdTokens.
        // Which should look like this:
        // ________________________________________________
        // |     |            |      |        |      |    |
        // |title|release date|rating|director|Studio|note|
        // |     |            |      |        |      |    |
        // ------------------------------------------------
        //  [0]       [1]        [2]     [3]     [4]   [5]
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the title is in index 0 of the array.
        String title = dvdTokens[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(title);

        // However, there are 5 remaining tokens that need to be set into the
        // new dvd object. Do this manually by using the appropriate setters.

        // Index 1 - release date
        dvdFromFile.setReleaseDate(dvdTokens[1]);

        // Index 2 - rating
        dvdFromFile.setMpaaRating(dvdTokens[2]);

        // Index 3 - director
        dvdFromFile.setDirectorName(dvdTokens[3]);
        
        // Index 4 - Studio
        dvdFromFile.setStudio(dvdTokens[4]);

        // Index 5 - note
        dvdFromFile.setNote(dvdTokens[5]);
        
        // We have now created a student! Return it!
        return dvdFromFile;
    }
    
    // private function to load the dvds from a file
    // throws DVDLibraryDaoException
    private void loadLibrary() throws DVDLibraryDaoException {
        // declaring scanner
        Scanner scanner;

        // try catch block
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // end of try catch block
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent dvd unmarshalled
        DVD currentDvd;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallDVD method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDvd = unmarshallDVD(currentLine);

            // We are going to use the title as the map key for our dvd object.
            // Put currentDvd into the map using Title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }
    
    // private function to store the dvd to a file
    // has one parameter a DVD
    private String marshallDVD(DVD aDvd){
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // title::release date::rating::director name::studio::note

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the title, since that's supposed to be first.
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // add the rest of the properties in the correct order:

        // Release Date
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // rating
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;

        // director
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        
        // studio
        dvdAsText += aDvd.getStudio() + DELIMITER;
        
        // note - don't forget to skip the DELIMITER here.
        dvdAsText += aDvd.getNote();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }
    
    /**
    * Writes all dvds in the library out to a LIBRARY_FILE.  See loadLibrary
    * for file format.
    * 
    * @throws DVDLibraryDaoException if an error occurs writing to the file
    */
    private void writeLibrary() throws DVDLibraryDaoException {
       // creating a PrinterWriter variable
       PrintWriter out;

       // try catch block
       try {
           out = new PrintWriter(new FileWriter(LIBRARY_FILE));
       } catch (IOException e) {
           throw new DVDLibraryDaoException(
                   "Could not save dvd data.", e);
       }
       // end of try catch block

       // creating a String variable 
       String dvdAsText;
       // creating a List of DVDs using getAllDvds function
       List<DVD> dvdList = this.getAllDvds();
       // a for each loop to loop through the dvds list
       for (DVD currentDvd : dvdList) {
           // turn a dvd into a String
           dvdAsText = marshallDVD(currentDvd);
           // write the Dvd object to the file
           out.println(dvdAsText);
           // force PrintWriter to write line to the file
           out.flush();
       }
       // Clean up
       out.close();
   }
    
}
