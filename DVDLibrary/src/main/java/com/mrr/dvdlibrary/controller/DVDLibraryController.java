/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.dvdlibrary.controller;

import com.mrr.dvdlibrary.dao.DVDLibraryDao;
import com.mrr.dvdlibrary.dao.DVDLibraryDaoException;
import com.mrr.dvdlibrary.dto.DVD;
import com.mrr.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

public class DVDLibraryController {

    // privare variable of type DVDLibraryView
    private DVDLibraryView view;
    // private variable of type DVDLibraryDao
    private DVDLibraryDao dao;

    // public constructor for DVDLibraryController takes parameters DVDLibraryDao and DVDLibraryView
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        // assigning value to dao
        this.dao = dao;
        // assigning value to dao
        this.view = view;
    }
    
    // public function with return type void to call functionss to run the program
    public void run() {
        // boolean variable to determine if the loop stops or not
        boolean keepGoing = true;
        // int variable to determine the users selection in the menu
        int menuSelection = 0;
        // try catch block
        try {
            // while loop to allow the user to use the menu as many time as they want
            while (keepGoing) {

                // function call to getMenuSelection, returns int value and stores it in menuSelection
                menuSelection = getMenuSelection();

                //switch statement to determine what methods are called based on user selection
                switch (menuSelection) {
                    // case 1 to make a call to the listDVDs function
                    case 1:
                        listDVDs();
                        break;
                    // case 2 to make a call to the createDVD function
                    case 2:
                        createDVD();
                        break;
                    // case 3 to make a call to the removeDVD function
                    case 3:
                        removeDVD();
                        break;
                    // case 4 to make a call to the editDVD function
                    case 4:
                        editDVD();
                        break;
                    // case 5 to make a call to the viewDVD function
                    case 5:
                        viewDVD();
                        break;
                    // case 6 to end the loop
                    case 6:
                        keepGoing = false;
                        break;
                    // default case to make a call to the unknownCommand function
                    default:
                        unknownCommand();
                }

            } 
            // end of while loop
            
            // calling exitMessage function
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            // call to displayErrorMessage function
            view.displayErrorMessage(e.getMessage());
        } 
        // end of try catch block
    }
    // end of function run
    
    // private function that returns type int
    private int getMenuSelection() {
        // a call to the printMenuAndGetSelection function to print the menu
        // and get the users selection
        return view.printMenuAndGetSelection();       
    }
    
    // private function that returns type void and throws DVDLibraryDaoException
    private void createDVD() throws DVDLibraryDaoException {
        // a call to displayCreateDVDBanner function
        view.displayCreateDVDBanner();
        // creating a DVD object and calling the function getNewDVDInfo
        DVD newDVD = view.getNewDVDInfo();
        // calling the addDVD function
        dao.addDVD(newDVD.getTitle(), newDVD);
        // calling the displayCreateSuccessBanner
        view.displayCreateSuccessBanner();
    }
    
    // private method that returns type void and throws DVDLibraryDaoException
    private void listDVDs() throws DVDLibraryDaoException {
        // a call to displayDisplayAllBanner function
        view.displayDisplayAllBanner();
        // creating a list of DVDs called dvdList and initializing it with the getAllDvds function
        List<DVD> dvdList = dao.getAllDvds();
        // call to the displayDVDList function
        view.displayDVDList(dvdList);
    }
    
    // private method that returns type void and throws DVDLibraryDaoException
    private void viewDVD() throws DVDLibraryDaoException {
        // call to the displayDisplayDvdBanner function
        view.displayDisplayDvdBanner();
        // creating a String and initalizing it with the getTitleChoice function
        String title = view.getTitleChoice();
        // creating a DVD and initalizing it with the getDVD function
        DVD dvd = dao.getDVD(title);
        // call to the displayDVD function
        view.displayDVD(dvd);
    }
    
    // private method that returns type void and throws DVDLibraryDaoException
    private void editDVD() throws DVDLibraryDaoException {
        // call to the displayEditDvdBanner function
        view.displayEditDvdBanner();
        // creating a String and initializing it with the getTitleChoice function
        String title = view.getTitleChoice();
        // call to the removeDVD function
        dao.removeDVD(title);
        // creating a DVD and initializing it with the getNewDVDInfo function
        DVD newDVD = view.getNewDVDInfo();
        // call to the addDVD function
        dao.addDVD(newDVD.getTitle(), newDVD);
        // call to the displayEditSuccessBanner function
        view.displayEditSuccessBanner();
    }
    
    // private method that returns type void and throws DVDLibraryDaoException
    private void removeDVD() throws DVDLibraryDaoException {
        // call to the displayRemoveDvdBanner function
        view.displayRemoveDvdBanner();
        // creating a String and initializing it with the getTitleChoice function
        String title = view.getTitleChoice();
        // creating a DVD and initializing it with the removeDVD function
        DVD removedDvd = dao.removeDVD(title);
        // call to the displayRemoveResult function
        view.displayRemoveResult(removedDvd);
    }
    
    // private method that returns type void
    private void unknownCommand() {
        // call to displayUnknownCommandBanner function
        view.displayUnknownCommandBanner();
    }

    // private method that returns type void
    private void exitMessage() {
        // call to displayExitBanner function
        view.displayExitBanner();
    }
    
}