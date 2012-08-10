package org.knownspace.gamemaker.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import flexjson.JSONSerializer;

@Entity
@Table(name = "GAME_DATA")
public class GameVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "GAME_DATA_ID")
	private long gameVersionId;
	
	@Column(name = "GAME_ID" , insertable = false, updatable = false)
	private long gameId;
	
	@Column(name = "VERSION")
	private int version;
	
	@Lob
	@Column(name = "GAME_DATA", columnDefinition ="CLOB")
	private String gameData;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_TIMESTAMP")
	private Date timeStamp;
	
	@ManyToOne
	@JoinColumn(name="GAME_ID")
	private Game game;

	public GameVersion()
	{
		
	}
	
	public long getGameVersionId() {
		return gameVersionId;
	}

	public void setGameVersionId(long gameVersionId) {
		this.gameVersionId = gameVersionId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Object getGameData() {
		return gameData;
	}

	public void setGameData(String gameData) {
		this.gameData = gameData;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
}
