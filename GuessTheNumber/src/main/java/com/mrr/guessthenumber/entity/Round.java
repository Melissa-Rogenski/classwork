/*
 * Author: mroge
 * Purpose: The DTO that holds all the Round info
 */
package com.mrr.guessthenumber.entity;

import java.time.LocalDateTime;
import java.util.Objects;

// defining what is in a Round object
public class Round {
    private int roundId;
    private int gameId;
    private LocalDateTime guessTime;
    private String guess;
    private String result;

    /**
     * default constructor
     */
    public Round() {
    }

    /**
     * constructor
     * @param gameId
     * @param guess
     */
    public Round(int gameId, String guess) {
        this.gameId = gameId;
        this.guess = guess;
    }
    
    /**
     * constructor
     * @param roundId
     * @param gameId
     * @param guessTime
     * @param guess
     * @param result
     */
    public Round(int roundId, int gameId, LocalDateTime guessTime, String guess, String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guessTime = guessTime;
        this.guess = guess;
        this.result = result;
    }

    /**
     * getter for roundId
     * @return int
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * setter for roundId
     * @param roundId
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * getter for gameId
     * @return int
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * setter for gameId
     * @param gameId
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * getter for guessTime
     * @return LocalDateTime
     */
    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    /**
     * setter for guessTime
     * @param guessTime
     */
    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    /**
     * getter for guess
     * @return String
     */
    public String getGuess() {
        return guess;
    }

    /**
     * setter for guess
     * @param guess
     */
    public void setGuess(String guess) {
        this.guess = guess;
    }

    /**
     * getter for result
     * @return String
     */
    public String getResult() {
        return result;
    }

    /**
     * setter for result
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    // hash function override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.roundId;
        hash = 43 * hash + this.gameId;
        hash = 43 * hash + Objects.hashCode(this.guessTime);
        hash = 43 * hash + Objects.hashCode(this.guess);
        hash = 43 * hash + Objects.hashCode(this.result);
        return hash;
    }

    // equals function override
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guessTime, other.guessTime)) {
            return false;
        }
        return true;
    }
}
