package org.knownspace.gamemaker.server.services;

import java.util.Collection;
import java.util.List;

import org.knownspace.gamemaker.server.dao.GameUserDao;
import org.knownspace.gamemaker.server.entity.GameUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class GameUserServiceImpl implements GameUserService {

	@Autowired
	private GameUserDao gameUserDao;

	@Transactional(readOnly=true)
	@Override
	public GameUser getUserById(long userId) {
		// TODO Auto-generated method stub
		return gameUserDao.getUserById(userId);
	}

	@Transactional(readOnly=true)
	@Override
	public GameUser getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return gameUserDao.getUserByUserName(userName);
	}

	@Transactional
	@Override
	public Boolean save(GameUser user) {
		// TODO Auto-generated method stub
		return gameUserDao.save(user);
	}

	@Transactional
	@Override
	public Boolean delete(long userId) {
		// TODO Auto-generated method stub
		return gameUserDao.delete(userId);
	}

	@Transactional(readOnly=true)
	@Override
	public Boolean verifyUserPassword(String userName, String password) {
		// TODO Auto-generated method stub
		return gameUserDao.verifyUserPassword(userName, password);
	}

	@Transactional(readOnly=true)
	@Override
	public List<GameUser> getAllUsers() {
		// TODO Auto-generated method stub
		return gameUserDao.getAllUsers();
	}

	@Override
	public GameUser fromJsonToEntity(String json) {
		// TODO Auto-generated method stub
		return new JSONDeserializer<GameUser>().use(null, GameUser.class).deserialize(json);
	}

	@Override
	public String toJson(Collection<GameUser> collection) {
		// TODO Auto-generated method stub
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}
	
	@Transactional
	public boolean checkUserExists(String uname, String password) {

		return gameUserDao.checkUserExists(uname, password);
	}
	
	@Override
	public boolean checkUserExistsAlready(String userName) {

		return gameUserDao.checkUserExistsAlready(userName);
	}
	

}
