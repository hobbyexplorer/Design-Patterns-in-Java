package org.knownspace.gamemaker.server.services;

import org.knownspace.gamemaker.server.entity.GameKey;

public interface GameKeyService {
	Boolean save(GameKey gameKey);
	Boolean delete(String gameKey);
	long isGameKeyPresent(String gameKey);
}
