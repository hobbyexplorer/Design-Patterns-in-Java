package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.dao.GameDao;
import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private GameDao gameDao;
	
	@Override
	public Object getPublishedGameVersionData(long gameId) {
		return gameDao.getPublishedGameVersionData(gameId);
	}

	@Override
	public Boolean isGamePublished(long gameId) {
		return gameDao.isGamePublished(gameId);
	}

	@Override
	public Game getGame(long userId, String gameName) {
		return gameDao.getGame(userId, gameName);
	}

	@Override
	public List<Game> getAllPublishedGames() {
		return gameDao.getAllPublishedGames();
	}
	
	
	@Override
	public Game getGame(long gameId) {
		return gameDao.getGame(gameId);
	}

	@Override
	public long getGameId(long userId, String gameName) {
		return gameDao.getGameId(userId, gameName);
	}

	@Override
	public Boolean save(Game game) {
		return gameDao.save(game);
	}

	@Override
	public Boolean delete(long gameId) {
		return gameDao.delete(gameId);
	}

	@Override
	public Game fromJsonToEntity(String json) {
		return new JSONDeserializer<Game>().use(null, Game.class).deserialize(json);
	}

	@Override
	public String toJson(Collection<Game> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	@Override
	public List<Game> getAllGames(long userId) {
		// TODO Auto-generated method stub
		return gameDao.getAllGames(userId);
	}
	
	@Override
	public List<Game> getAllPublishedGames(int pageNumber, int pageSize) {
		return gameDao.getAllPublishedGames(pageNumber, pageSize);
	}
	
	@Override
	public List<Game> getAllGames(long userId, int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return gameDao.getAllGames(userId, pageNumber, pageSize);
	}

	@Override
	public List<Game> getTopGames()
	{
		return gameDao.getTopGames();
	}

}
