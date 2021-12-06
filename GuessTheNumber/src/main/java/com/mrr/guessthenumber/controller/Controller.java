/*
 * Author: mroge
 * Purpose: The orchestrator of the application. It knows what needs to be done, when it 
 *          needs to be done, and what component can do the job.
 */
package com.mrr.guessthenumber.controller;

import com.mrr.guessthenumber.entity.Game;
import com.mrr.guessthenumber.entity.Round;
import com.mrr.guessthenumber.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    ServiceLayer service;
    
    //postmapping for begining a game
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.newGame();
    }
    
    // post mapping to make a guess on a game
    @PostMapping("/guess")
    public Round makeGuess(@RequestBody Round round) {
        return service.makeGuess(round);   
    }
    
    // get mapping to view games
    @GetMapping("/game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }
    
    // get mapping to view a specific game by the game id
    @GetMapping("/game/{game_id}")
    public Game getGameById(@PathVariable("game_id") int gameId) {
        return service.getGameById(gameId);
    }
    
    // get mapping to view the rounds for a specific game by the game id
    @GetMapping("/rounds/{game_id}")
    public List<Round> getRoundsForGame(@PathVariable("game_id") int gameId) {
        return service.getAllRoundsByGameId(gameId);
    }
  
}
