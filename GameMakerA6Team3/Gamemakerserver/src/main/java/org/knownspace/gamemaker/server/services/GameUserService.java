package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.entity.GameUser;

public interface GameUserService {
	
	boolean checkUserExists(String uname, String password);
	boolean checkUserExistsAlready(String username);
	GameUser getUserById(long userId);
	GameUser getUserByUserName(String userName);
	Boolean save(GameUser user);
	Boolean delete(long userId);
	Boolean verifyUserPassword(String userName, String password);
	List<GameUser> getAllUsers();
	
	GameUser fromJsonToEntity(String json);
    String toJson(Collection<GameUser> collection);

}
