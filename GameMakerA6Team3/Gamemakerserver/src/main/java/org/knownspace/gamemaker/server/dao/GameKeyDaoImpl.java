package org.knownspace.gamemaker.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.knownspace.gamemaker.server.entity.GameKey;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GameKeyDaoImpl implements GameKeyDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	
	
	@Override
	@Transactional
	public Boolean save(GameKey gameKey) {
		if (gameKey == null)
		{
			return false;
		}
		try
		{
			if (gameKey.getGameKeyId() == 0)
			{
				

				entityManager.persist(gameKey);
				
			}
			else
			{
				entityManager.merge(gameKey);
	            //return true;
			}
		}
		catch (OptimisticLockException oe)
		{
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Boolean delete(String gameKey) {
		Query q = entityManager.createQuery("delete from GameKey k where k.gameKey = :gameKey");
		q.setParameter("gameKey", gameKey);
		int result = q.executeUpdate();
		if (result == 1)
		{
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long isGameKeyPresent(String gameKey) {
		Query q = entityManager.createQuery("select k from GameKey k where k.gameKey = :gameKey");
		q.setParameter("gameKey", gameKey);
		List<GameKey> result = q.getResultList();
		if (result.size() == 1)
		{
			return result.get(0).getGameId();
		}
		
		return 0;
	}

}
