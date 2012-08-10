package org.knownspace.gamemaker.server.dao;

import java.util.List;

import org.knownspace.gamemaker.server.entity.GameUser;

public interface GameUserDao {
	GameUser getUserById(long userId);
	GameUser getUserByUserName(String userName);
	Boolean save(GameUser user);
	Boolean delete(long userId);
	Boolean verifyUserPassword(String userName, String password);
	List<GameUser> getAllUsers();
     boolean checkUserExists(String uname, String password);
     boolean checkUserExistsAlready(String userName);
}
