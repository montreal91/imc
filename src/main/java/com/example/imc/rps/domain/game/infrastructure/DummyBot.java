package com.example.imc.rps.domain.game.infrastructure;

import com.example.imc.rps.domain.game.core.model.Action;
import com.example.imc.rps.domain.game.core.ports.outgoing.GameBot;

import java.util.List;

public class DummyBot implements GameBot {
    private final List<Action> sequence;
    private int index = 0;

    public DummyBot(List<Action> sequence) {
        this.sequence = sequence;
    }

    @Override
    public Action getNextAction() {
        Action res = sequence.get(index);
        index++;
        index %= sequence.size();
        return res;
    }
}
