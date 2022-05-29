package com.example.imc.rps.domain.game.core.ports.incoming;

import com.example.imc.rps.domain.game.core.model.Score;

import java.util.List;

public interface GetAllScores {
    List<Score> handle();
}
