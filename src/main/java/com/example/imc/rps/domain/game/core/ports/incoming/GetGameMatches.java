package com.example.imc.rps.domain.game.core.ports.incoming;

import com.example.imc.rps.domain.game.core.model.ActionMatch;
import com.example.imc.rps.domain.game.core.model.GameMatchQuery;

import java.util.List;

public interface GetGameMatches {
    List<ActionMatch> handle(GameMatchQuery query);
}
