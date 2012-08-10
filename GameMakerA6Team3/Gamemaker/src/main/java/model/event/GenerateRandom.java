package model.event;

import gamemaker.Constants;

import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;
import model.Sprite;
import model.action.Action;

public class GenerateRandom implements Event
{
	private int eventId;
	private Sprite sprite;
	private ArrayList<Action> actionList = new ArrayList<Action>();
	private int delay;
	private int startTime;
	private int maxTime;
	private int count;
	private int[] sides;
	private Random generator;
	private String eventString;

	/**
	 * @return the eventString
	 */
	public String getEventString()
	{
		return eventString;
	}

	private static final String EVENT_NAME = Constants.EVENT_GENERATE_RANDOM;

	public GenerateRandom(Sprite sprite, int[] sides, int count, int startTime, int delay, int maxTime)
	{
		this.eventId = -1;
		this.sprite = sprite;
		this.setActionList(new ArrayList<Action>());
		this.sides = sides;
		this.count = count;
		this.setStartTime(startTime);
		this.setDelay(delay);
		this.setMaxTime(maxTime);
		generator = new Random();
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
			int side = randomGenerator(this.sides.length);
			if (side == 0)
			{
				newObject.setX(0);
				newObject.setY(randomGenerator(Constants.GAMEBOARD_HEIGHT));
			}
			else if (side == 1)
			{
				newObject.setX(randomGenerator(Constants.GAMEBOARD_WIDTH));
				newObject.setY(0);
			}
			else if (side == 2)
			{
				newObject.setX(Constants.GAMEBOARD_WIDTH - newObject.getObjectWidth());
				newObject.setY(randomGenerator(Constants.GAMEBOARD_HEIGHT));
			}
			else if (side == 3)
			{
				newObject.setX(randomGenerator(Constants.GAMEBOARD_WIDTH));
				newObject.setY(Constants.GAMEBOARD_HEIGHT - newObject.getObjectHeight());
			}
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

	public int randomGenerator(int range)
	{
		return generator.nextInt(range);
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

		saveGame.createNode(eventElement, "delay", Integer.toString(delay));

		saveGame.createNode(eventElement, "startTime", Integer.toString(startTime));

		saveGame.createNode(eventElement, "maxTime", Integer.toString(maxTime));

		saveGame.createNode(eventElement, "count", Integer.toString(count));

		saveGame.createNode(eventElement, "eventString", EVENT_NAME);

		saveGame.createNode(eventElement, "eventActionString", eventString);

		Element allSidesElement = document.createElement("allSides");

		for (int side : sides)
		{
			// Element sideElement = document.createElement("side");
			saveGame.createNode(allSidesElement, "side", Integer.toString(side));
			// allSidesElement.appendChild(sideElement);
		}
		eventElement.appendChild(allSidesElement);

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
