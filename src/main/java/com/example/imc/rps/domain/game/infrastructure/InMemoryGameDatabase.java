package com.example.imc.rps.domain.game.infrastructure;

import com.example.imc.rps.domain.game.core.model.Game;
import com.example.imc.rps.domain.game.core.model.GameId;
import com.example.imc.rps.domain.game.core.ports.outgoing.GameDatabase;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryGameDatabase implements GameDatabase {
    private final Map<UUID, Game> storage = new LinkedHashMap<>();
    @Override
    public GameId save(Game game) {
        if (game.getId() == null) {
            game.setId(new GameId(UUID.randomUUID()));
        }

        storage.put(game.getId().id(), game);
        return game.getId();
    }

    @Override
    public Optional<Game> getGameById(GameId id) {
        return Optional.ofNullable(storage.get(id.id()));
    }

    @Override
    public List<Game> getAllGames() {
        return List.copyOf(storage.values());
    }
}
