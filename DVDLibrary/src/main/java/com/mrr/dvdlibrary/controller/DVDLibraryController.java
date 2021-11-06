/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrr.dvdlibrary.controller;

import com.mrr.dvdlibrary.dao.DVDLibraryDao;
import com.mrr.dvdlibrary.dao.DVDLibraryDaoException;
import com.mrr.dvdlibrary.dto.DVD;
import com.mrr.dvdlibrary.ui.DVDLibraryView;
import java.util.List;

/**
 *
 * @author mroge
 */
public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDVDList(dvdList);
    }
    
    private void viewDVD() throws DVDLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        DVD dvd = dao.getDVD(title);
        view.displayDVD(dvd);
    }
    
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDvdBanner();
        String title = view.getTitleChoice();
        dao.removeDVD(title);
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayEditSuccessBanner();
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDvdBanner();
        String title = view.getTitleChoice();
        DVD removedDvd = dao.removeDVD(title);
        view.displayRemoveResult(removedDvd);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
    
}