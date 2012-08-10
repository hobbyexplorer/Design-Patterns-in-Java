package org.knownspace.gamemaker.server.services;

import org.knownspace.gamemaker.server.dao.GameKeyDao;
import org.knownspace.gamemaker.server.entity.GameKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameKeyServiceImpl implements GameKeyService {

	@Autowired
	private GameKeyDao gameKeyDao;
	
	@Override
	public Boolean save(GameKey gameKey) {
		return gameKeyDao.save(gameKey);
	}

	@Override
	public Boolean delete(String gameKey) {
		return gameKeyDao.delete(gameKey);
	}

	@Override
	public long isGameKeyPresent(String gameKey) {
		// TODO Auto-generated method stub
		return gameKeyDao.isGameKeyPresent(gameKey);
	}

}
