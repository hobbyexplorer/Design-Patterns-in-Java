package org.knownspace.gamemaker.server.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import flexjson.JSONSerializer;

@Entity
@Table(name = "GAME_USER")
public class GameUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "AGE")
	private int age;
	
	@Column(name = "SEX")
	private char sex;
	
	@Column(name = "EMAIL")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
    private List<GameScore> scores;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
	 private List<Game> games;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="user")
	 private List<GameRating> ratings;
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<GameScore> getScores() {
		return scores;
	}

	public void setScores(List<GameScore> scores) {
		this.scores = scores;
	}

	public GameUser(long userId, String name, String userName, String password)
	{
		setUserId(userId);
		setName(name);
		setUserName(userName);
		setPassword(password);
	}
	
	public GameUser()
	{
		
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
}
