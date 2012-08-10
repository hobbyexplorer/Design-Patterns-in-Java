package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import flexjson.JSONSerializer;


@SuppressWarnings("serial")
@Entity
@Table(name = "GAME_RATING")
public class GameRating implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "RATING_ID", insertable = false, updatable = false)
	private long ratingId;
	
	@Column(name = "USER_ID", insertable = false, updatable = false)
	private long userId;
	
	@Column(name = "GAME_ID",insertable = false, updatable = false)
	private long gameId;
	
	@Column(name = "GAME_RATING")
	private int gameRating;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_TIMESTAMP")
	private Date timeStamp;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private GameUser user;
	
	@ManyToOne
	@JoinColumn(name="GAME_ID")
	private Game game;
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public GameUser getUser() {
		return user;
	}

	public void setUser(GameUser user) {
		this.user = user;
	}
	
	public long getRatingId() {
		return ratingId;
	}

	public void setRatingId(long ratingId) {
		this.ratingId = ratingId;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public int getGameRating() {
		return gameRating;
	}

	public void setGameRating(int gameRating) {
		this.gameRating = gameRating;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

}
