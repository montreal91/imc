package com.example.imc.rps.domain.game.core;

import com.example.imc.rps.domain.game.core.model.ActionComparator;
import com.example.imc.rps.domain.game.core.model.ActionMatch;
import com.example.imc.rps.domain.game.core.model.Game;
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
import com.example.imc.rps.domain.game.core.ports.outgoing.GameBot;
import com.example.imc.rps.domain.game.core.ports.outgoing.GameDatabase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Service.
 *
 * This class shows how commands and queries should be handled.
 */
public class GameService implements
        StartNewGame,
        MakeAction,
        GetGameScore,
        GetGameMatches,
        GetAllScores
{
    private final GameDatabase gameDatabase;
    private final GameBot gameBot;

    public GameService(GameDatabase gameDatabase, GameBot gameBot) {
        this.gameDatabase = gameDatabase;
        this.gameBot = gameBot;
    }

    @Override
    public GameId handle(StartNewGameCommand command) {
        Game newGame = new Game(command.roundsToWin(), new ActionComparator());
        return gameDatabase.save(newGame);
    }

    @Override
    public Optional<Score> handle(MakeActionCommand command) {
        gameDatabase.getGameById(command.id())
                    .ifPresent(game -> {
                        game.setPlayerOneAction(command.action());
                        game.setPlayerTwoAction(gameBot.getNextAction());
                        game.match();
                        gameDatabase.save(game);
                    });
        return gameDatabase.getGameById(command.id())
                           .map(this::makeScore);
    }

    @Override
    public Optional<Score> handle(GetGameScoreQuery query) {
        Optional<Game> optionalGame = gameDatabase.getGameById(query.gameId());
        if (optionalGame.isEmpty()) {
            return Optional.empty();
        }
        Game game = optionalGame.get();
        return Optional.of(makeScore(game));
    }

    @Override
    public List<Score> handle() {
        return gameDatabase.getAllGames()
                           .stream()
                           .map(this::makeScore)
                           .collect(Collectors.toList());
    }

    @Override
    public List<ActionMatch> handle(GameMatchQuery query) {
        Optional<Game> game = gameDatabase.getGameById(query.gameId());
        if (game.isEmpty()) {
            return List.of();
        }

        return game.get()
                   .getMatches();
    }

    private Score makeScore(Game game) {
        return new Score(
                game.getPlayerOneScore(),
                game.getPlayerTwoScore(),
                "You",
                "Ai",
                game.isOver()
        );
    }
}
