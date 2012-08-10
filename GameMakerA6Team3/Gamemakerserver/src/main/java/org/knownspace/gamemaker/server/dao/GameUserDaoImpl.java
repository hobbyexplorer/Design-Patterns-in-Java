package org.knownspace.gamemaker.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.knownspace.gamemaker.server.entity.GameUser;
import org.springframework.stereotype.Repository;

@Repository
public class GameUserDaoImpl implements GameUserDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public GameUser getUserById(long userId) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select u from GameUser u where u.userId = :id");
		q.setParameter("id", userId);
		List<GameUser> users = q.getResultList();
 		return users != null && users.size() == 1 ? users.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	public GameUser getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select u from GameUser u where u.userName = :name");
		q.setParameter("name", userName);
		List<GameUser> users = q.getResultList();
 		return users != null && users.size() == 1 ? users.get(0) : null;
	}

	@Override
	public Boolean save(GameUser user) {
		// TODO Auto-generated method stub
		if (user == null) {
            return false;
        }
        try {
        	
            if (user.getUserId() == 0) {
                entityManager.persist(user);
            } else {
                entityManager.merge(user);
            }
        } catch (OptimisticLockException oe) {
            return false;
        }
        return true;
	}

	@Override
	public Boolean delete(long userId) {
		// TODO Auto-generated method stub
		
		if(userId == 0)
		{
			return false;
		}
		
		Query q = entityManager.createQuery("delete u from GameUser u where u.id = id");
		q.setParameter("id", userId);
		int result = q.executeUpdate();
		if (result == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean verifyUserPassword(String userName, String password) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select u from GameUser u where u.userName = :userName");
		q.setParameter("userName", userName);
		@SuppressWarnings("unchecked")
		List<GameUser> users = q.getResultList();
		
		if(users.size() == 0)
		{
			return false;
		}
		else if(users.get(0).getPassword().equalsIgnoreCase(password))
		{
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GameUser> getAllUsers() {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select u from GameUser u");
		List<GameUser> users = q.getResultList();
		
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUserExists(String userName, String password) {
		
		boolean exists = false;
		
		Query q = entityManager.createQuery("select userName, password from GameUser u where u.userName = :userName and u.password= :password");
		q.setParameter("userName", userName);
		q.setParameter("password", password);
		List<GameUser> userList = q.getResultList();
		if(userList.size() > 0) {
			exists = true;
		}
	
		return exists;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUserExistsAlready(String userName) {
		
		boolean exists = false;
		
		Query q = entityManager.createQuery("select userName from UserDetails u where u.userName = :userName");
		q.setParameter("userName", userName);
		List<GameUser> userList = q.getResultList();
		if(userList.size() > 0){
			
			exists = true;
		}
		
		return exists;
	}

}
