/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author mroge
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, DVD> dvds = new HashMap<>();
    
    @Override
    public DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD prevDvd = dvds.put(title, dvd);
        writeLibrary();
        return prevDvd;
    }

    @Override
    public List<DVD> getAllDvds() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD getDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDVD(String title) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }
    
    @Override
    public DVD editDVD(String title, DVD dvd)  throws DVDLibraryDaoException {
        loadLibrary();
        dvds.remove(title);
        DVD prevDvd = dvds.put(title, dvd);
        writeLibrary();
        return prevDvd;
    }
    
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
    
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
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

        // We have now turned a student to text! Return it!
        return dvdAsText;
    }
    
    /**
    * Writes all dvds in the library out to a LIBRARY_FILE.  See loadLibrary
    * for file format.
    * 
    * @throws DVDLibraryDaoException if an error occurs writing to the file
    */
    private void writeLibrary() throws DVDLibraryDaoException {
       // NOTE FOR APPRENTICES: We are not handling the IOException - but
       // we are translating it to an application specific exception and 
       // then simple throwing it (i.e. 'reporting' it) to the code that
       // called us.  It is the responsibility of the calling code to 
       // handle any errors that occur.
       PrintWriter out;

       try {
           out = new PrintWriter(new FileWriter(LIBRARY_FILE));
       } catch (IOException e) {
           throw new DVDLibraryDaoException(
                   "Could not save dvd data.", e);
       }

       // Write out the DVD objects to the library file.
       // NOTE TO THE APPRENTICES: We could just grab the dvd map,
       // get the Collection of DVDs and iterate over them but we've
       // already created a method that gets a List of DVDs so
       // we'll reuse it.
       String dvdAsText;
       List<DVD> dvdList = this.getAllDvds();
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
