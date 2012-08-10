package org.knownspace.gamemaker.server.services;

import org.knownspace.gamemaker.server.dao.GameRatingDao;
import org.knownspace.gamemaker.server.dao.GameScoreDao;
import org.knownspace.gamemaker.server.entity.GameRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRatingServiceImpl implements GameRatingService {

	@Autowired
	private GameRatingDao gameRatingDao;
	
	@Override
	public Boolean saveGameRating(GameRating rating) {
		return gameRatingDao.saveGameRating(rating);
	}
	
	@Override
	public double getUserRating(long userId, long gameId) {
		return gameRatingDao.getUserRating(userId, gameId);
	}
	

}
