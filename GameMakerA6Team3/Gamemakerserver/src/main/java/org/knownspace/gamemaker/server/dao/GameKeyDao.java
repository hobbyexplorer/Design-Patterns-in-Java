package org.knownspace.gamemaker.server.dao;

import org.knownspace.gamemaker.server.entity.GameKey;

public interface GameKeyDao {
	Boolean save(GameKey gameKey);
	Boolean delete(String gameKey);
	long isGameKeyPresent(String gameKey);
}
