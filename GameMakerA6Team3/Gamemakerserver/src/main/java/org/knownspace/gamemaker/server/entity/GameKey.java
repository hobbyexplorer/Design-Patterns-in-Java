package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "GAME_KEY")
public class GameKey implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "GAME_KEY_ID", insertable = false, updatable = false)
	private long gameKeyId;
	
	@Column(name = "GAME_ID")
	private long gameId;
	
	@Column(name = "GAME_KEY")
	private String gameKey;

	public long getGameKeyId() {
		return gameKeyId;
	}

	public void setGameKeyId(long gameKeyId) {
		this.gameKeyId = gameKeyId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public String getGameKey() {
		return gameKey;
	}

	public void setGameKey(String gameKey) {
		this.gameKey = gameKey;
	}
}
