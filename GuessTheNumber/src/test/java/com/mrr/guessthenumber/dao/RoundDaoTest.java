package com.mrr.guessthenumber.dao;

import com.mrr.guessthenumber.TestApplicationConfiguration;
import com.mrr.guessthenumber.entity.Game;
import com.mrr.guessthenumber.entity.Round;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mroge
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundDaoTest() {
    }
    
    // test for the add and get all methods
    @Test
    public void testAddGetGetAll() {
        int gameId = 1;
        
        Game game = new Game();
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Round round = new Round();
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameId(gameId);
        roundDao.addRound(round);

        Round round2 = new Round();
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameId(gameId);
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

        assertEquals(2, rounds.size());
        assertNotNull(round = roundDao.getRoundById(round.getRoundId()));
    }
}
