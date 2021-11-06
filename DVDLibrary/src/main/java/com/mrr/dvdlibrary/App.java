/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrr.dvdlibrary;

import com.mrr.dvdlibrary.controller.DVDLibraryController;
import com.mrr.dvdlibrary.dao.DVDLibraryDao;
import com.mrr.dvdlibrary.dao.DVDLibraryDaoFileImpl;
import com.mrr.dvdlibrary.ui.DVDLibraryView;
import com.mrr.dvdlibrary.ui.UserIO;
import com.mrr.dvdlibrary.ui.UserIOConsoleImpl;

/**
 *
 * @author mroge
 */
public class App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }   
}