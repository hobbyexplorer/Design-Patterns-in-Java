package org.knownspace.gamemaker.server.dao;

import org.knownspace.gamemaker.server.entity.GameRating;

public interface GameRatingDao {
	
	Boolean saveGameRating(GameRating rating);

	double getUserRating(long userId, long gameId);

}
