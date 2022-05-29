package com.example.imc.rps.domain.game.core.ports.incoming;

import com.example.imc.rps.domain.game.core.model.MakeActionCommand;
import com.example.imc.rps.domain.game.core.model.Score;

import java.util.Optional;

public interface MakeAction {
    Optional<Score> handle(MakeActionCommand command);
}
