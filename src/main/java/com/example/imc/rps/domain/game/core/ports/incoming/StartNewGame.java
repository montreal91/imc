package com.example.imc.rps.domain.game.core.ports.incoming;

import com.example.imc.rps.domain.game.core.model.GameId;
import com.example.imc.rps.domain.game.core.model.StartNewGameCommand;

public interface StartNewGame {
    GameId handle(StartNewGameCommand command);
}
