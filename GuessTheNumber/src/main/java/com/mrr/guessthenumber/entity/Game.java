/*
 * Author: mroge
 * Purpose: The DTO that holds all the Game info
 */
package com.mrr.guessthenumber.entity;

import java.util.Objects;

// defining what is in a Game object
public class Game {
    
    private int gameId;
    private String answer;
    private boolean finished;

    /**
     * default constructor
     */
    public Game() {
    }

    /**
     * constructor
     * @param answer
     * @param finished
     */
    public Game(String answer, boolean finished) {
        this.answer = answer;
        this.finished = finished;
    }
    
    /**
     * constructor
     * @param gameId
     * @param answer
     * @param finished
     */
    public Game(int gameId, String answer, boolean finished) {
        this.gameId = gameId;
        this.answer = answer;
        this.finished = finished;
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
     * getter for answer
     * @return String
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * setter for answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * getter for finished
     * @return Boolean
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * setter for finished
     * @param finished
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // hash function override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.gameId;
        hash = 47 * hash + Objects.hashCode(this.answer);
        hash = 47 * hash + (this.finished ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.finished != other.finished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    // toString function override
    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", finished=" + finished + '}';
    }
   
}
