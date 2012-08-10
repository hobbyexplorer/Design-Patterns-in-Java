package model.action;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gamemaker.Constants;
import gamemaker.GameMakerParams;
import controller.GameBoardController;
import controller.SaveGame;
import databaseconnector.DatabaseConnector;
import model.GameObject;
import model.Sprite;

public class GameOver implements Action
{
	private int actionType = 0;
	private Sprite sprite;
	private String actionName;
	private String message;
	private Boolean conditionApplied;
	private Boolean greaterThan;
	private Boolean lessThan;
	private Boolean equalTo;
	private int conditionCount;


	public GameOver(Sprite sprite, String message, Boolean conditionApplied)
	{
		this.actionName = Constants.ACTION_GAME_OVER;
		this.sprite = sprite;
		this.message = message;
		this.conditionApplied = conditionApplied;
		greaterThan = false;
		lessThan = false;
		equalTo = false;
		conditionCount = 0;
	}

	public void setOperators(Boolean greaterThan, Boolean lessThan, Boolean equalTo)
	{
		this.greaterThan = greaterThan;
		this.lessThan = lessThan;
		this.equalTo = equalTo;
	}

	public void setCondition(int conditionCount, Boolean greaterThan, Boolean lessThan, Boolean equalTo)
	{
		setOperators(greaterThan, lessThan, equalTo);
		this.conditionCount = conditionCount;
	}

	public void removeCondition()
	{
		conditionApplied = false;
		setOperators(false, false, false);
		this.conditionCount = 0;
	}

	public boolean checkCondition()
	{
		if(!conditionApplied)
		{
			return true;
		}

		int objectsOfTargetSpriteCounter = this.sprite.getGameObjects().size();

		if(greaterThan && (objectsOfTargetSpriteCounter > conditionCount))
		{
			return true;
		}

		else if(lessThan && (objectsOfTargetSpriteCounter < conditionCount))
		{
			return true;
		}

		else if(equalTo && (objectsOfTargetSpriteCounter == conditionCount))
		{
			return true;
		}

		return false;
	}



	@Override
	public void execute()
	{
		if(checkCondition())
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
		return this.sprite.getSpriteId();
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
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "message", message);
		saveGame.createNode(actionElement, "conditionApplied", conditionApplied.toString());
		saveGame.createNode(actionElement, "conditionCount", Integer.toString(conditionCount));	
		saveGame.createNode(actionElement, "greaterThan", Boolean.toString(greaterThan));
		saveGame.createNode(actionElement, "lessThan", Boolean.toString(lessThan));
		saveGame.createNode(actionElement, "equalTo", Boolean.toString(equalTo));
		
		
		return actionElement;
	}


}
