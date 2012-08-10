package model.event;

import gamemaker.Constants;

import java.util.ArrayList;
import java.awt.event.KeyEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import model.GameObject;
import model.Sprite;
import model.action.Action;
import model.action.Score;
import model.action.ScoreForCollision;

public class KeyPressedEvent implements Event
{
	private int eventId;
	private Sprite sprite;
	private ArrayList<Action> actionList = new ArrayList<Action>();
	private int keyCode;
	private String eventString;

	/**
	 * @return the eventString
	 */
	public String getEventString()
	{
		return eventString;
	}

	private static final String EVENT_NAME = Constants.EVENT_KEY_PRESSED;

	/**
	 * Constructor of KeyPressedEvent Assigns -1 to eventID Initializes
	 * actionlist array list.
	 * 
	 * @param Sprite
	 *            sprite
	 * @param KeyCode
	 *            keyCode
	 */
	public KeyPressedEvent(Sprite sprite, int keyCode)
	{
		this.eventId = -1;
		this.sprite = sprite;
		this.actionList = new ArrayList<Action>();
		this.keyCode = keyCode;
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

	public Sprite getSprite()
	{
		return sprite;
	}

	public void setSprite(Sprite sprite)
	{
		this.sprite = sprite;
	}

	public ArrayList<Action> getActionList()
	{
		return actionList;
	}

	public void setActionList(ArrayList<Action> actionList)
	{
		this.actionList = actionList;
	}

	public int getKeyCode()
	{
		return keyCode;
	}

	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}

	public boolean isEventTriggered(KeyEvent e)
	{
		if (keyCode == e.getKeyCode())
		{
			return true;
		}
		return false;
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
				if (action instanceof ScoreForCollision || action instanceof Score)
				{
					action.execute();
					continue;
				}
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
		 * } else action.execute();
		 * 
		 * }
		 */
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

		saveGame.createNode(eventElement, "eventKeyCode", Integer.toString(keyCode));

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
