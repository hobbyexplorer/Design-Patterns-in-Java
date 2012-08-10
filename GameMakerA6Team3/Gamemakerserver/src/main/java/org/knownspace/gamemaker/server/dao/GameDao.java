package org.knownspace.gamemaker.server.dao;

import java.util.List;

import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;

public interface GameDao
{

	Object getPublishedGameVersionData(long gameId);

	Boolean isGamePublished(long gameId);

	Game getGame(long userId, String gameName);

	Game getGame(long gameId);

	long getGameId(long userId, String gameName);

	Boolean save(Game game);

	Boolean delete(long gameId);

	List<Game> getAllPublishedGames();
	List<Game> getAllPublishedGames(int pageNumber, int pageSize);
	List<Game> getAllGames(long userId);
	List<Game> getAllGames(long userId, int pageNumber, int pageSize);

	List<Game> getTopGames();
}
