package org.knownspace.gamemaker.server.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;
import org.springframework.stereotype.Repository;

@Repository
public class GameScoreDaoImpl implements GameScoreDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public long getGameScore(long scoreId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.scoreId = :id");
		q.setParameter("id", scoreId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			long score = gameScores.get(0).getScore();
			return score;
		}
		else
		{
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getLatestGameScore(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int maxIndex = 0;
			Date latestDate = gameScores.get(0).getTimeStamp();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(isGreaterThan(gameScores.get(counter).getTimeStamp(), latestDate))
				{
					maxIndex = counter;
				}
			}
			
			return gameScores.get(maxIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getBestGameScore(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int maxIndex = 0;
			long maximumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() > maximumScore)
				{
					maxIndex = counter;
				}
			}
			
			return gameScores.get(maxIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getWorstGameScore(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int minIndex = 0;
			long minimumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() < minimumScore)
				{
					minIndex = counter;
				}
			}
			
			return gameScores.get(minIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getWorstPerformanceForUser(long userId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId");
		q.setParameter("userId", userId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int minIndex = 0;
			long minimumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() < minimumScore)
				{
					minIndex = counter;
				}
			}
			
			return gameScores.get(minIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getBestPerformanceForUser(long userId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId");
		q.setParameter("userId", userId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int maxIndex = 0;
			long maximumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() > maximumScore)
				{
					maxIndex = counter;
				}
			}
			
			return gameScores.get(maxIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getWorstPerformanceForGame(long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.gameId = :gameId");
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int minIndex = 0;
			long minimumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() < minimumScore)
				{
					minIndex = counter;
				}
			}
			
			return gameScores.get(minIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public GameScore getBestPerformanceForGame(long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.gameId = :gameId");
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		if(gameScores.size() > 0)
		{
			int maxIndex = 0;
			long maximumScore = gameScores.get(0).getScore();
			
			for(int counter = 1; counter < gameScores.size(); ++counter)
			{
				if(gameScores.get(counter).getScore() > maximumScore)
				{
					maxIndex = counter;
				}
			}
			
			return gameScores.get(maxIndex);
		}
		else
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfGamesPlayedByUser(long userId) {
		Query q = entityManager.createQuery("select distinct(s.gameId) from GameScore s where s.userId = :userId");
		q.setParameter("userId", userId);
		
		List<GameScore> gameScores = q.getResultList();
		
		return gameScores.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getNumberOfGamesPlayedByUser(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
		
		List<GameScore> gameScores = q.getResultList();
		
		return gameScores.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public double getAverageGameScoreForUser(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
				
		double totalScore = 0;
		List<GameScore> gameScores = q.getResultList();
		
		for(int counter = 0; counter < gameScores.size(); ++counter)
		{
			totalScore += gameScores.get(counter).getScore();
		}
				
		return (totalScore/gameScores.size());
	}
	
	@SuppressWarnings("deprecation")
	public Boolean isGreaterThan(Date firstDate, Date secondDate)
	{
		if(firstDate.getYear() > secondDate.getYear())
		{
			return true;
		}
		else if(firstDate.getYear() == secondDate.getYear())
		{
			if(firstDate.getMonth() > secondDate.getMonth())
			{
				return true;
			}
			else if(firstDate.getMonth() == secondDate.getMonth())
			{
				if(firstDate.getDate() > secondDate.getDate())
				{
					return true;
				}
				else if(firstDate.getDate() == secondDate.getDate())
				{
					if(firstDate.getHours() > secondDate.getHours())
					{
						return true;
					}
					else if(firstDate.getHours() == secondDate.getHours())
					{
						if(firstDate.getMinutes() > secondDate.getMinutes())
						{
							return true;
						}
						else if(firstDate.getMinutes() == secondDate.getMinutes())
						{
							if(firstDate.getSeconds() > secondDate.getSeconds())
							{
								return true;
							}
							else if(firstDate.getSeconds() == secondDate.getSeconds())
							{
								return false;
							}
						}
					}
				}
			}
				
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameScore> getAllScores() {
		Query q = entityManager.createQuery("select s from GameScore s");
		List<GameScore> gameScores = q.getResultList();
		return gameScores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameScore> getAllScoresForUser(long userId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId");
		q.setParameter("userId", userId);
		List<GameScore> gameScores = q.getResultList();
		return gameScores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameScore> getAllScoresForUserGame(long userId, long gameId) {
		Query q = entityManager.createQuery("select s from GameScore s where s.userId = :userId and s.gameId = :gameId");
		q.setParameter("userId", userId);
		q.setParameter("gameId", gameId);
		List<GameScore> gameScores = q.getResultList();
		return gameScores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameScore> getTopScores() {
		Query q = entityManager.createQuery("select s from GameScore s order by s.score desc");
		q.setMaxResults(10);
		
		List<GameScore> gameScores = q.getResultList();
		return gameScores;
	}

}
