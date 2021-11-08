/*
 * Author: mroge
 * Purpose: file that contains the main function
 */
package com.mrr.dvdlibrary;

import com.mrr.dvdlibrary.controller.DVDLibraryController;
import com.mrr.dvdlibrary.dao.DVDLibraryDao;
import com.mrr.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mrr.dvdlibrary.ui.DVDLibraryView;
import com.mrr.dvdlibrary.ui.UserIO;
import com.mrr.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        // creating a UserIO variable that calls the UserIOConsole constructor
        UserIO myIo = new UserIOConsoleImpl();
        // creating a DVDLibraryView variable that calls the DVDLibraryView constructor
        DVDLibraryView myView = new DVDLibraryView(myIo);
        // creating a DVDLibraryDao variable that calls the DVDLibraryDaoFileImpl
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        // creating a DVDLibraryController variable that calls the DVDLibraryController constructor
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        // calling the run function of controller
        controller.run();
    }   
}