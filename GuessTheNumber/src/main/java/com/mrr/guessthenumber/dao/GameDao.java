/*
 * Author: mroge
 * Purpose: interface to be implemented by the GameDaoDB class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 */
package com.mrr.guessthenumber.dao;

import com.mrr.guessthenumber.entity.Game;
import java.util.List;

// declaring interface
public interface GameDao {

    /**
     * function to get all of the games in the DB
     * @return List of Games
     */
    List<Game> getAllGames();

    /**
     * function to get a particular game by game id
     * @param gameId
     * @return Game
     */
    Game getGameById(int gameId);

    /**
     * function to create a new game and add it to the database
     * @param game
     * @return Game
     */
    Game addGame(Game game);

    /**
     * function to update game in database given a round
     * @param round
     */
    void updateGame(Game round);
}
