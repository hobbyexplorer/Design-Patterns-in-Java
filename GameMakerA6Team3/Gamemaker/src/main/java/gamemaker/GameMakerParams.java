package gamemaker;

public class GameMakerParams {
	private static GameMakerParams gameParams = null;
	private int UserID;
	private int gameID;
	private int version;
	private boolean gameMakerMode;
	
	private GameMakerParams(){
		UserID = -1;
		gameID = -1;
		version = -1;
		gameMakerMode = true;
	}
	
	public static GameMakerParams getInstance(){
		if(gameParams == null){
			gameParams = new GameMakerParams();
		}
		return gameParams;
	}
	
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public boolean isGameMakerMode() {
		return gameMakerMode;
	}

	public void setGameMakerMode(boolean gameMode) {
		this.gameMakerMode = gameMode;
	}
	
}
