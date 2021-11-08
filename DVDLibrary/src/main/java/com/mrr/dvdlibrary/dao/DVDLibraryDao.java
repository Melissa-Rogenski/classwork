/*
 * Author: mroge
 * Purpose: interface to be implemented by the DVDLibraryDaoFileImpl class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 *         
 * The DVDLibraryController only uses this interface to reference the DAO — it 
 * is completely unaware of the implementation details.
 */
package com.mrr.dvdlibrary.dao;

import com.mrr.dvdlibrary.dto.DVD;
import java.util.List;

// interface declaration
public interface DVDLibraryDao {
    /**
     * Adds the given DVD to the library and associates it with the given
     * title. If there is already a DVD associated with the given
     * title it will return that DVD object, otherwise it will
     * return null.
     *
     * @param title title with which dvd is to be associated
     * @param dvd dvd to be added to the library
     * @return the DVD object previously associated with the given  
     * title if it exists, null otherwise
     * @throws DVDLibraryDaoException 
     */
    DVD addDVD(String title, DVD dvd) throws DVDLibraryDaoException;

    /**
     * Returns a List of all dvds in the library.
     *
     * @return List containing all dvds in the library.
     * @throws DVDLibraryDaoException 
     */
    List<DVD> getAllDvds() throws DVDLibraryDaoException;

    /**
     * Returns the dvd object associated with the given title.
     * Returns null if no such dvd exists
     *
     * @param title title of the dvd to retrieve
     * @return the DVD object associated with the given title,  
     * null if no such dvd exists
     * @throws DVDLibraryDaoException 
     */
    DVD getDVD(String title) throws DVDLibraryDaoException ;

    /**
     * Removes from the library the dvd associated with the given title.
     * Adds the given DVD to the library and associates it with the given
     * title. If there is already a DVD associated with the given
     * title it will return that DVD object, otherwise it will
     * return null.
     *
     * @param title title of dvd to be removed
     * @return the DVD object newly associated with the new given  
     * title if it exists, null otherwise
     * @throws DVDLibraryDaoException 
     */
    DVD editDVD(String title, DVD dvd) throws DVDLibraryDaoException;
    
    /**
     * Removes from the library the dvd associated with the given title.
     * Returns the dvd object that is being removed or null if
     * there is no dvd associated with the given title
     *
     * @param title title of dvd to be removed
     * @return Dvd object that was removed or null if no dvd
     * was associated with the given title
     * @throws DVDLibraryDaoException
     */
    DVD removeDVD(String title) throws DVDLibraryDaoException;
}
