package org.knownspace.gamemaker.server.entity;

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

@Entity
@Table(name = "SCORE")
public class GameScore {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "SCORE_ID", insertable = false, updatable = false)
	private long scoreId;
	
	@Column(name = "USER_ID", insertable = false, updatable = false)
	private long userId;
	
	@Column(name = "GAME_ID", insertable = false, updatable = false)
	private long gameId;
	
	@Column(name = "SCORE")
	private long score;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMISSION_TIMESTAMP")
	private Date timeStamp;
	
	@Column(name = "IS_WIN")
	private Boolean isWin;
	
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

	public long getScoreId() {
		return scoreId;
	}

	public void setScoreId(long scoreId) {
		this.scoreId = scoreId;
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

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Boolean getIsWin() {
		return isWin;
	}

	public void setIsWin(Boolean isWin) {
		this.isWin = isWin;
	}
	
	public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
}
