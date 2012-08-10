package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gamemaker.Constants;
import controller.GameBoardController;
import controller.SaveGame;
import model.GameObject;

public class SetGameTime implements Action
{
	private int actionType = 0;
	private String message;
	//Time in seconds
	private int gameTime;
	private String actionName;


	public SetGameTime(int gameTime, String message)
	{
		this.actionName = Constants.ACTION_SET_GAME_TIME;
		this.gameTime = gameTime;
		this.message = message;
	}
	@Override
	public void execute()
	{
		if(GameBoardController.getInstance().getSecondsElapsed() >= this.gameTime)
		{
			GameBoardController.getInstance().setStopGameFlag(true);
			GameBoardController.getInstance().setGameMessage(this.message);
		}
	}

	@Override
	public void execute(GameObject gameObject)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSpriteId()
	{
		return -1;
	}

	@Override
	public int getActionType()
	{
		return this.actionType;
	}

	@Override
	public String getActionName()
	{
		return this.actionName;
	}
	
	@Override
	public Element getActionInfoXML(SaveGame saveGame) {
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");
		
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "gametime", Integer.toString(gameTime));
		saveGame.createNode(actionElement, "message", message);
				
		return actionElement;
	}


}
