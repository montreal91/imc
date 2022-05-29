package com.example.imc.rps.domain.game.core.ports.outgoing;

import com.example.imc.rps.domain.game.core.model.Action;

public interface GameBot {
    Action getNextAction();
}
