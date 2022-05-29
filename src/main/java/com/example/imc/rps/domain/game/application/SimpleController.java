package com.example.imc.rps.domain.game.application;

import com.example.imc.rps.domain.game.core.model.ActionMatch;
import com.example.imc.rps.domain.game.core.model.GameId;
import com.example.imc.rps.domain.game.core.model.GameMatchQuery;
import com.example.imc.rps.domain.game.core.model.GetGameScoreQuery;
import com.example.imc.rps.domain.game.core.model.MakeActionCommand;
import com.example.imc.rps.domain.game.core.model.Score;
import com.example.imc.rps.domain.game.core.model.StartNewGameCommand;
import com.example.imc.rps.domain.game.core.ports.incoming.GetAllScores;
import com.example.imc.rps.domain.game.core.ports.incoming.GetGameMatches;
import com.example.imc.rps.domain.game.core.ports.incoming.GetGameScore;
import com.example.imc.rps.domain.game.core.ports.incoming.MakeAction;
import com.example.imc.rps.domain.game.core.ports.incoming.StartNewGame;

import java.util.List;
import java.util.Optional;

/**
 * A gateway from outside world to the core domain.
 */
public class SimpleController {
    private final StartNewGame startNewGame;
    private final GetGameScore getGameScore;
    private final MakeAction makeAction;
    private final GetAllScores getAllScores;
    private final GetGameMatches getGameMatches;

    public SimpleController(
            StartNewGame startNewGame,
            GetGameScore getGameScore,
            MakeAction makeAction,
            GetAllScores getAllScores,
            GetGameMatches getGameMatches) {
        this.startNewGame = startNewGame;
        this.getGameScore = getGameScore;
        this.makeAction = makeAction;
        this.getAllScores = getAllScores;
        this.getGameMatches = getGameMatches;
    }

    public GameId startNewGame(StartNewGameCommand command) {
        return startNewGame.handle(command);
    }

    public Optional<Score> getGameScore(GetGameScoreQuery query) {
        return getGameScore.handle(query);
    }

    public Optional<Score> makeAction(MakeActionCommand command) {
        return makeAction.handle(command);
    }

    public List<Score> getAllScores() {
        return getAllScores.handle();
    }

    public List<ActionMatch> getGameMatches(GameMatchQuery query) {
        return getGameMatches.handle(query);
    }
}
