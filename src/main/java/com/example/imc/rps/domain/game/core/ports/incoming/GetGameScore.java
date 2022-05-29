package com.example.imc.rps.domain.game.core.ports.incoming;

import com.example.imc.rps.domain.game.core.model.GetGameScoreQuery;
import com.example.imc.rps.domain.game.core.model.Score;

import java.util.Optional;

public interface GetGameScore {
    Optional<Score> handle(GetGameScoreQuery query);
}
