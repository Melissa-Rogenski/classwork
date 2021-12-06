/*
 * Author: mroge
 * Purpose: service layer of the application
 */

package com.mrr.guessthenumber.service;

import com.mrr.guessthenumber.dao.GameDao;
import com.mrr.guessthenumber.dao.RoundDao;
import com.mrr.guessthenumber.entity.Game;
import com.mrr.guessthenumber.entity.Round;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// declaring class
@Service
public class ServiceLayer {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    // getting all games and returning them as a list of game type
    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            // filltering output as to not give away answer
            if (!game.isFinished()) {
                game.setAnswer("****");
            }
        }

        return games;
    }

    // getting all rounds for a given game by game id and returning them as a list of round type
    public List<Round> getAllRoundsByGameId(int gameId) {
        return roundDao.getAllRoundsByGameId(gameId);
    }

    // function to implement the making of a guess by the user
    public Round makeGuess(Round round) {
        String answer = gameDao.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        // call to determine the results of the guess given the guess and the correct answer
        String result = determineResult(guess, answer);
        round.setResult(result);
        
        if (guess.equals(answer)) {
            Game game = getGameById(round.getGameId());
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        
        return roundDao.addRound(round);
    }

    // function to call the get game by id function from the gamedao
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        if (!game.isFinished()) {
            game.setAnswer("****");
        }

        return game;
    }

    // function to add a game to the database
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    // function to create a new game and add it to the databse using the addGame function above
    public int newGame() {
        Game game = new Game();
        game.setAnswer(generateAnswer());
        game = gameDao.addGame(game);

        return game.getGameId();
    }

    // function to randomly generate a answer for a new game
    private String generateAnswer() {
        Random rnd = new Random();
        int digit1 = rnd.nextInt(10);

        int digit2 = rnd.nextInt(10);
        while (digit2 == digit1) {
            digit2 = rnd.nextInt(10);
        }

        int digit3 = rnd.nextInt(10);
        while (digit3 == digit2 || digit3 == digit1) {
            digit3 = rnd.nextInt(10);
        }

        int digit4 = rnd.nextInt(10);
        while (digit4 == digit3 || digit4 == digit2 || digit4 == digit1) {
            digit4 = rnd.nextInt(10);
        }

        String answer = String.valueOf(digit1) + String.valueOf(digit2)
                + String.valueOf(digit3) + String.valueOf(digit4);

        return answer;
    }

    // function to determine how close the guess is to the result
    public String determineResult(String guess, String answer) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exact = 0;
        int partial = 0;
        
        for (int i = 0; i < guessChars.length; i++) {
            // -1 indicates that index value of guessChars DNE in answer
            // otherwise the number must be in the string. Then check where
            if (answer.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exact++;
                } else {
                    partial++;
                }
            }
        }
        
        String result = "e:" + exact + ":p:" + partial;
        
        return result;
    }

}
