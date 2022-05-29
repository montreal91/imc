package com.example.imc.rps.domain.game.core.ports.outgoing;

import com.example.imc.rps.domain.game.core.model.Game;
import com.example.imc.rps.domain.game.core.model.GameId;

import java.util.List;
import java.util.Optional;

public interface GameDatabase {
    GameId save(Game game);
    Optional<Game> getGameById(GameId id);
    List<Game> getAllGames();
}
