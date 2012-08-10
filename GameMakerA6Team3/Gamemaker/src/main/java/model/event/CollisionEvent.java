package model.event;

import gamemaker.Constants;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;
import model.Sprite;
import model.action.Action;
import model.action.Score;
import model.action.ScoreForCollision;

public class CollisionEvent implements Event
{
	private int eventId;
	private Sprite spriteOne;
	private Sprite spriteTwo;
	private ArrayList<Action> actionList = new ArrayList<Action>();
	private boolean breakFlag = false;
	private String eventString;

	/**
	 * @return the eventString
	 */
	public String getEventString()
	{
		return eventString;
	}

	private static final String EVENT_NAME = Constants.EVENT_COLLISION;

	public CollisionEvent(Sprite spriteOne, Sprite spriteTwo)
	{
		this.eventId = -1;
		this.spriteOne = spriteOne;
		this.spriteTwo = spriteTwo;
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

	public void setSpriteOne(Sprite spriteOne)
	{
		this.spriteOne = spriteOne;
	}

	public Sprite getSpriteOne()
	{
		return spriteOne;
	}

	public void setSpriteTwo(Sprite spriteTwo)
	{
		this.spriteTwo = spriteTwo;
	}

	public Sprite getSpriteTwo()
	{
		return spriteTwo;
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

	public void checkCollision()
	{
		breakFlag = false;
		for (int objectCounterOne = 0; objectCounterOne < getSpriteOne().getGameObjects().size(); ++objectCounterOne)
		{
			for (int objectCounterTwo = 0; objectCounterTwo < getSpriteTwo().getGameObjects().size(); ++objectCounterTwo)
			{
				GameObject objectOne = getSpriteOne().getGameObjects().get(objectCounterOne);
				GameObject objectTwo = getSpriteTwo().getGameObjects().get(objectCounterTwo);
				Rectangle objectOneRectangle = new Rectangle();
				Rectangle objectTwoRectangle = new Rectangle();

				objectOneRectangle.setBounds(objectOne.getX(), objectOne.getY(), objectOne.getObjectWidth(), objectOne.getObjectHeight());
				objectTwoRectangle.setBounds(objectTwo.getX(), objectTwo.getY(), objectTwo.getObjectWidth(), objectTwo.getObjectHeight());

				if (objectOneRectangle.intersects(objectTwoRectangle) && objectOne.isVisible() && objectTwo.isVisible())
				{   System.out.println("event happened");
					performActions(objectOne, objectTwo);
					breakFlag = true;
					break;

				}
			}
			if (breakFlag)
				break;
		}
	}

	public void performActions(GameObject objectOne, GameObject objectTwo)
	{
		int noOfActions = actionList.size();
		GameObject selectedObject = null;
		for (int actionCounter = 0; actionCounter < noOfActions; ++actionCounter)
		{
			Action action = actionList.get(actionCounter);
			if (action instanceof ScoreForCollision || action instanceof Score)
			{
				action.execute();
				continue;
			}
			if (action.getSpriteId() == getSpriteOne().getSpriteId())
			{
				Iterator<GameObject> itr = getSpriteOne().getGameObjects().iterator();
				while (itr.hasNext())
				{
					selectedObject = itr.next();
					if (selectedObject.getObjectId() == objectOne.getObjectId())
						break;
				}
				if (action.getActionType() == 2)
				{
					action.execute(objectTwo, selectedObject);
				}
				else if (action.getActionType() == 1)
				{
					action.execute(selectedObject);
				}
				else
				{
					action.execute();
				}
			}
			else if (action.getSpriteId() == getSpriteTwo().getSpriteId())
			{
				Iterator<GameObject> itr = getSpriteTwo().getGameObjects().iterator();
				while (itr.hasNext())
				{
					selectedObject = itr.next();
					if (selectedObject.getObjectId() == objectTwo.getObjectId())
						break;
				}
				if (action.getActionType() == 2)
				{
					action.execute(objectOne, selectedObject);
				}
				else if (action.getActionType() == 1)
				{
					action.execute(selectedObject);
				}
				else
				{
					action.execute();
				}
			}
			/*
			 * else { action.execute(); }
			 */
		}
	}

	@Override
	public void performActions()
	{

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

		saveGame.createNode(eventElement, "eventId", Integer.toString(eventId));

		saveGame.createNode(eventElement, "spriteOne", Integer.toString(spriteOne.getSpriteId()));

		saveGame.createNode(eventElement, "spriteTwo", Integer.toString(spriteTwo.getSpriteId()));

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
