package org.knownspace.gamemaker.server.dao;

import java.util.List;

import org.knownspace.gamemaker.server.entity.GameScore;

public interface GameScoreDao {
	
	long getGameScore(long scoreId);
	GameScore getLatestGameScore(long userId, long gameId);
	GameScore getBestGameScore(long userId, long gameId);
	GameScore getWorstGameScore(long userId, long gameId);
	GameScore getWorstPerformanceForUser(long userId);
	GameScore getBestPerformanceForUser(long userId);
	GameScore getWorstPerformanceForGame(long gameId);
	GameScore getBestPerformanceForGame(long gameId);
	long getNumberOfGamesPlayedByUser(long userId);
	long getNumberOfGamesPlayedByUser(long userId, long gameId);
	double getAverageGameScoreForUser(long userId, long gameId);
	List<GameScore> getAllScores();
	List<GameScore> getAllScoresForUser(long userId);
	List<GameScore> getAllScoresForUserGame(long userId, long gameId);
	List<GameScore> getTopScores();
}
