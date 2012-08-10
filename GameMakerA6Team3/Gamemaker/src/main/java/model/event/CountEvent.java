package model.event;

import gamemaker.Constants;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;
import model.Sprite;
import model.action.Action;

public class CountEvent implements Event
{

	private int eventId;
	private Sprite sprite;
	private ArrayList<Action> actionList = new ArrayList<Action>();
	private static final String EVENT_NAME = Constants.EVENT_COUNT;

	private String eventString;

	/**
	 * @return the eventString
	 */
	public String getEventString()
	{
		return eventString;
	}

	public CountEvent(Sprite sprite)
	{
		this.eventId = -1;
		this.sprite = sprite;
		this.actionList = new ArrayList<Action>();
	}

	@Override
	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}

	@Override
	public int getEventId()
	{
		return eventId;
	}

	public void setSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public void setActionList(ArrayList<Action> actionList)
	{
		this.actionList = actionList;
	}

	public ArrayList<Action> getActionList()
	{
		return actionList;
	}

	@Override
	public void addAction(Action action)
	{
		actionList.add(action);
	}

	@Override
	public void removeAction(int index)
	{
		actionList.remove(index);
	}

	@Override
	public void performActions()
	{

		int noOfActions = actionList.size();
		for (int objectCounter = 0; objectCounter < getSprite().getGameObjects().size(); ++objectCounter)
		{
			GameObject gameObject = getSprite().getGameObjects().get(objectCounter);
			for (int actionCounter = 0; actionCounter < noOfActions; ++actionCounter)
			{
				Action action = actionList.get(actionCounter);
				if (action.getSpriteId() == sprite.getSpriteId() && action.getActionType() == 1)
					action.execute(gameObject);
				else action.execute();
			}
		}

		/*
		 * int noOfActions = actionList.size(); for (int actionCounter = 0;
		 * actionCounter < noOfActions; ++actionCounter) { Action action =
		 * actionList.get(actionCounter); if(action.getSpriteId() ==
		 * sprite.getSpriteId() && action.getActionType() == 1) { for (int
		 * objectCounter = 0; objectCounter <
		 * getSprite().getGameObjects().size(); ++objectCounter ) { GameObject
		 * gameObject = getSprite().getGameObjects().get(objectCounter);
		 * action.execute(gameObject); }
		 * 
		 * } else action.execute(); }
		 */}

	@Override
	public String getEventName()
	{
		// TODO Auto-generated method stub
		return EVENT_NAME;
	}

	@Override
	public Element getEventInfoXML(SaveGame saveGame)
	{
		Document document = saveGame.getDocument();
		Element eventElement = document.createElement("event");

		if (sprite != null)
		{
			saveGame.createNode(eventElement, "eventSprite", Integer.toString(sprite.getSpriteId()));
		}
		else
		{
			saveGame.createNode(eventElement, "eventSprite", "null");
		}

		saveGame.createNode(eventElement, "eventId", Integer.toString(eventId));

		saveGame.createNode(eventElement, "eventString", EVENT_NAME);

		saveGame.createNode(eventElement, "eventActionString", eventString);

		Element allActionElement = document.createElement("allAction");

		for (Action action : actionList)
		{
			Element actionElement = action.getActionInfoXML(saveGame);
			allActionElement.appendChild(actionElement);
		}
		eventElement.appendChild(allActionElement);

		return eventElement;
	}

	@Override
	public void setEventString(String eventString)
	{
		this.eventString = eventString;
	}

}
