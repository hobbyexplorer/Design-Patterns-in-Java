package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import flexjson.JSONSerializer;

@SuppressWarnings("serial")
@Entity
@Table(name = "GAME")
public class Game implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "GAME_ID", insertable = false, updatable = false)
	private long gameId;

	@Column(name = "USER_ID", insertable = false, updatable = false)
	private long userId;

	@Column(name = "GAME_NAME")
	private String name;

	@Column(name = "IS_PUBLISHED")
	private Boolean isPublished;

	@Column(name = "PUBLISHED_VERSION")
	private int publishedVersion;
	
	@Column(name = "AVERAGE_RATING")
	private double averageRating;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "game")
	private List<GameVersion> versions;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "game")
	private List<GameScore> scores;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "game")
	private List<GameRating> ratings;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private GameUser user;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="USER_ID") private GameUser user;
	 */
	public GameUser getUser() {
		return user;
	}

	public void setUser(GameUser user) {
		this.user = user;
	}

	public List<GameScore> getScores() {
		return scores;
	}

	public void setScores(List<GameScore> scores) {
		this.scores = scores;
	}

	public Game() {
		versions = new ArrayList<GameVersion>();
	}

	public List<GameVersion> getVersions() {
		return versions;
	}

	public void setVersions(List<GameVersion> versions) {
		this.versions = versions;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	public int getPublishedVersion() {
		return publishedVersion;
	}

	public void setPublishedVersion(int publishedVersion) {
		this.publishedVersion = publishedVersion;
	}

	public List<GameRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<GameRating> ratings) {
		this.ratings = ratings;
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
}
