/*
 * Author: mroge
 * Purpose: interface to be implemented by the RoundDaoDB class file
 * This interface defines the methods that must be implemented by any class that
 * wants to play the role of DAO in the application.
 */
package com.mrr.guessthenumber.dao;

import com.mrr.guessthenumber.entity.Round;
import java.util.List;

// declaring interface
public interface RoundDao {
    
    /**
     * function to get a list of all rounds associated with a given gameId
     * @param gameId
     * @return List of Round objects
     */
    List<Round> getAllRoundsByGameId(int gameId);
    
    /**
     * function to get a round by the given roundId
     * @param roundId
     * @return Round
     */
    Round getRoundById(int roundId);
    
    /**
     * function to add a round to the database
     * @param round
     * @return Round
     */
    Round addRound(Round round);
}
