package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;

public interface GameService {
	Object getPublishedGameVersionData(long gameId);
	Boolean isGamePublished(long gameId);
	Game getGame(long userId, String gameName);
	Game getGame(long gameId);
	long getGameId(long userId, String gameName);
	Boolean save(Game game);
	Boolean delete(long gameId);
	List<Game> getAllPublishedGames();
	List<Game> getAllGames(long userId);
	List<Game> getAllPublishedGames(int pagenumber, int pageSize);
	List<Game> getAllGames(long userId, int pageNumber, int pageSize);
	
	Game fromJsonToEntity(String json);
    String toJson(Collection<Game> collection);
	List<Game> getTopGames();

}
