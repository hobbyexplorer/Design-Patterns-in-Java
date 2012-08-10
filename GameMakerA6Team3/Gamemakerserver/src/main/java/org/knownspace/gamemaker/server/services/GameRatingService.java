package org.knownspace.gamemaker.server.services;

import org.knownspace.gamemaker.server.entity.GameRating;

public interface GameRatingService {

	Boolean saveGameRating(GameRating rating);

	double getUserRating(long userId, long gameId);
}
