package org.knownspace.gamemaker.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.knownspace.gamemaker.server.entity.GameRating;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GameRatingDaoImpl implements GameRatingDao
{

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public double getUserRating(long userId, long gameId)
	{
		System.out.println("GameID: " + gameId);
		System.out.println("UserID: " + userId);
		Query q = entityManager.createQuery("select r from GameRating r where r.gameId = :gameId and r.userId = :userId");
		q.setParameter("gameId", gameId);
		q.setParameter("userId", userId);
		List<GameRating> existingRatings = q.getResultList();

		if (existingRatings.size() > 0)
		{
			if (existingRatings.size() == 1)
			{
				return existingRatings.get(0).getGameRating();
			}
			else
			{
				//Check If the rating is greater than five at the receiving end. If so, then the user has not rated the game yet.
				return 6.0;
			}
		}	
		return 0.0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Boolean saveGameRating(GameRating rating)
	{

		if (rating == null)
		{
			return false;
		}
		try
		{

			long gameId = rating.getGameId();
			long userId = rating.getUserId();
			System.out.println("GameID: " + gameId);
			System.out.println("UserID: " + userId);
			Query q = entityManager.createQuery("select r from GameRating r where r.gameId = :gameId and r.userId = :userId");
			q.setParameter("gameId", gameId);
			q.setParameter("userId", userId);
			List<GameRating> existingRatings = q.getResultList();

			if (existingRatings.size() > 0)
			{
				if (existingRatings.size() == 1)
				{
					entityManager.merge(rating);
				}
				else
				{
					return false;
				}
			}
			else
			{
				entityManager.persist(rating);
			}

			q = entityManager.createQuery("select r from GameRating r where r.gameId = :id");
			q.setParameter("id", gameId);
			List<GameRating> ratings = q.getResultList();
			if (ratings.size() > 0)
			{
				long sum = 0;
				double averageRating = 0;

				for (int counter = 0; counter < ratings.size(); ++counter)
				{
					sum += ratings.get(counter).getGameRating();
				}

				averageRating = sum / ((double) ratings.size());

				q = entityManager.createQuery("Update Game g SET g.averageRating = :avgRating WHERE g.gameId = :id");
				q.setParameter("avgRating", averageRating);
				q.setParameter("id", gameId);
				int result = q.executeUpdate();
				if (result > 0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}

		}
		catch (OptimisticLockException oe)
		{
			return false;
		}
	}
}
