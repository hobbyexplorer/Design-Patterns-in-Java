package org.knownspace.gamemaker.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoImpl implements GameDao
{

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Object getPublishedGameVersionData(long gameId)
	{
		Query q = entityManager.createQuery("select g from Game g where g.gameId = :id");
		q.setParameter("id", gameId);

		List<Game> games = q.getResultList();

		if (games.size() > 0)
		{
			int publishedVersion = games.get(0).getPublishedVersion();
			return games.get(0).getVersions().get(publishedVersion).getGameData();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isGamePublished(long gameId)
	{
		Query q = entityManager.createQuery("select g from Game g where g.gameId = :id");
		q.setParameter("id", gameId);

		List<Game> games = q.getResultList();
		if (games.size() > 0)
		{
			return games.get(0).getIsPublished();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Game getGame(long userId, String gameName)
	{
		Query q = entityManager.createQuery("select g from Game g where g.userId = :id and g.name = :name");
		q.setParameter("id", userId);
		q.setParameter("name", gameName);

		List<Game> games = q.getResultList();
		if (games.size() > 0)
		{
			return games.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Game getGame(long gameId)
	{
		Query q = entityManager.createQuery("select g from Game g where g.gameId = :id");
		q.setParameter("id", gameId);

		List<Game> games = q.getResultList();

		if (games.size() > 0)
		{
			return games.get(0);
		}

		return null;
	}

	@Override
	public long getGameId(long userId, String gameName)
	{
		Game game = getGame(userId, gameName);

		if (game != null)
		{
			return game.getGameId();
		}

		return 0;
	}

	@Override
	public Boolean save(Game game)
	{
		if (game == null)
		{
			return false;
		}
		try
		{
			if (game.getGameId() == 0)
			{
				entityManager.persist(game);
			}
			else
			{
				entityManager.merge(game);
			}
		}
		catch (OptimisticLockException oe)
		{
			return false;
		}
		return true;
	}

	@Override
	public Boolean delete(long gameId)
	{
		Query q = entityManager.createQuery("delete g from Game g where g.gameId = :id");
		q.setParameter("id", gameId);
		int result = q.executeUpdate();
		if (result == 1)
		{
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllPublishedGames()
	{
		Query q = entityManager.createQuery("select g from Game g where g.isPublished = 1");

		List<Game> allGames = q.getResultList();

		if (allGames.size() > 0)
		{
			return allGames;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllPublishedGames(int pageNumber, int pageSize)
	{
		Query q = entityManager.createQuery("select g from Game g where g.isPublished = 1");

		List<Game> allGames = q.getResultList();

		if (allGames.size() > 0)
		{
			List<Game> targetSetOfGames = getRecordsinRange(allGames, ((pageNumber - 1) * pageSize), ((pageNumber - 1) * pageSize) + (pageSize - 1));
			if (targetSetOfGames.size() > 0)
			{
				return targetSetOfGames;
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames(long userId)
	{
		Query q = entityManager.createQuery("select g from Game g where g.userId = :id");
		q.setParameter("id", userId);
		List<Game> allGames = q.getResultList();

		if (allGames.size() > 0)
		{
			return allGames;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames(long userId, int pageNumber, int pageSize)
	{
		Query q = entityManager.createQuery("select g from Game g where g.userId = :id");
		q.setParameter("id", userId);
		List<Game> allGames = q.getResultList();

		if (allGames.size() > 0)
		{
			List<Game> targetSetOfGames = getRecordsinRange(allGames, ((pageNumber - 1) * pageSize), ((pageNumber - 1) * pageSize) + (pageSize - 1));
			if (targetSetOfGames.size() > 0)
			{
				return targetSetOfGames;
			}
		}

		return null;
	}

	public List<Game> getRecordsinRange(List<Game> fullList, int start, int end)
	{
		List<Game> returnData = new ArrayList<Game>();

		for (int counter = 0; counter < fullList.size(); ++counter)
		{
			if (counter >= start && counter <= end)
			{
				returnData.add(fullList.get(counter));
			}
		}

		return returnData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getTopGames()
	{
		Query q = entityManager.createQuery("select g from Game g where g.isPublished = 1 order by averageRating desc");

		List<Game> topGames = q.getResultList();

		if (topGames.size() > 0)
		{
			return topGames;
		}

		return null;
	}

}
