package model.event;

import gamemaker.Constants;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;
import model.Sprite;
import model.action.Action;

public class GenerateAtPosition implements Event
{
	private int eventId;
	private Sprite sprite;
	private ArrayList<Action> actionList = new ArrayList<Action>();
	private int x;
	private int y;
	private int delay;
	private int startTime;
	private int maxTime;
	private int count;
	private String eventString;

	/**
	 * @return the eventString
	 */
	public String getEventString()
	{
		return eventString;
	}

	private static final String EVENT_NAME = Constants.EVENT_GENERATE_AT_POSITION;

	public GenerateAtPosition(Sprite sprite, int x, int y, int count, int startTime, int delay, int maxTime)
	{
		this.eventId = -1;
		this.sprite = sprite;
		this.setActionList(new ArrayList<Action>());
		this.x = x;
		this.y = y;
		this.count = count;
		this.setStartTime(startTime);
		this.setDelay(delay);
		this.setMaxTime(maxTime);
	}

	@Override
	public void setEventId(int eventID)
	{
		this.eventId = eventID;

	}

	@Override
	public int getEventId()
	{
		return this.eventId;
	}

	public void setStartTime(int startTime)
	{
		this.startTime = startTime;
	}

	public int getStartTime()
	{
		return startTime;
	}

	public void setDelay(int delay)
	{
		this.delay = delay;
	}

	public int getDelay()
	{
		return delay;
	}

	public void setMaxTime(int maxTime)
	{
		this.maxTime = maxTime;
	}

	public int getMaxTime()
	{
		return maxTime;
	}

	@Override
	public void performActions()
	{
		for (int index = 0; index < this.count; index++)
		{
			GameObject newObject;
			newObject = sprite.addGameObject();
			newObject.setX(this.x);
			newObject.setY(this.y);
		}

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
	public String getEventName()
	{
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

		saveGame.createNode(eventElement, "xCoordinate", Integer.toString(x));

		saveGame.createNode(eventElement, "yCoordinate", Integer.toString(y));

		saveGame.createNode(eventElement, "delay", Integer.toString(delay));

		saveGame.createNode(eventElement, "startTime", Integer.toString(startTime));

		saveGame.createNode(eventElement, "maxTime", Integer.toString(maxTime));

		saveGame.createNode(eventElement, "count", Integer.toString(count));

		saveGame.createNode(eventElement, "eventActionString", eventString);

		/*
		 * Element allActionElement = document.createElement("allAction");
		 * for(Action action: actionList){ Element actionElement =
		 * action.getActionInfoXML(saveGame);
		 * allActionElement.appendChild(actionElement); }
		 * eventElement.appendChild(allActionElement);
		 */
		return eventElement;
	}

	@Override
	public void setEventString(String eventString)
	{
		this.eventString = eventString;
	}

}
