package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.dao.GameScoreDao;
import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class GameScoreServiceImpl implements GameScoreService {

	@Autowired
	private GameScoreDao gameScoreDao;
	
	@Override
	public long getGameScore(long scoreId) {
		return gameScoreDao.getGameScore(scoreId);
	}

	@Override
	public GameScore getLatestGameScore(long userId, long gameId) {
		return gameScoreDao.getLatestGameScore(userId, gameId);
	}

	@Override
	public GameScore getBestGameScore(long userId, long gameId) {
		return gameScoreDao.getBestGameScore(userId, gameId);
	}

	@Override
	public GameScore getWorstGameScore(long userId, long gameId) {
		return gameScoreDao.getWorstGameScore(userId, gameId);
	}

	@Override
	public GameScore getWorstPerformanceForUser(long userId) {
		return gameScoreDao.getWorstPerformanceForUser(userId);
	}

	@Override
	public GameScore getBestPerformanceForUser(long userId) {
		return gameScoreDao.getBestPerformanceForUser(userId);
	}

	@Override
	public GameScore getWorstPerformanceForGame(long gameId) {
		return gameScoreDao.getWorstPerformanceForGame(gameId);
	}

	@Override
	public GameScore getBestPerformanceForGame(long gameId) {
		return gameScoreDao.getBestPerformanceForGame(gameId);
	}

	@Override
	public long getNumberOfGamesPlayedByUser(long userId) {
		return gameScoreDao.getNumberOfGamesPlayedByUser(userId);
	}

	@Override
	public long getNumberOfGamesPlayedByUser(long userId, long gameId) {
		return gameScoreDao.getNumberOfGamesPlayedByUser(userId, gameId);
	}

	@Override
	public double getAverageGameScoreForUser(long userId, long gameId) {
		return gameScoreDao.getAverageGameScoreForUser(userId, gameId);
	}

	@Override
	public GameScore fromJsonToEntity(String json) {
		return new JSONDeserializer<GameScore>().use(null, GameScore.class).deserialize(json);
	}

	@Override
	public String toJson(Collection<GameScore> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	@Override
	public List<GameScore> getAllScores() {
		return gameScoreDao.getAllScores();
	}

	@Override
	public List<GameScore> getAllScoresForUser(long userId) {
		return gameScoreDao.getAllScoresForUser(userId);
	}

	@Override
	public List<GameScore> getAllScoresForUserGame(long userId, long gameId) {
		return gameScoreDao.getAllScoresForUserGame(userId, gameId);
	}

	@Override
	public List<GameScore> getTopScores() {
		return gameScoreDao.getTopScores();
	}

}
