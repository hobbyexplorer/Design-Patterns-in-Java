package model;

import gamemaker.GameMaker;

import java.util.ArrayList;
import java.util.Iterator;

import model.event.Event;

public class GameBoardModel
{

	private ArrayList<Sprite> spriteList;
	private ArrayList<Event> eventList;
	private int spriteCounter = 0;
	private int eventCounter = 0;

	/**
	 * Returns the corresponding sprite for the sprite name argument. Returns
	 * null if the sprite name does not match
	 * 
	 * @param spriteName
	 * @return Sprite
	 */
	public Sprite getSprite(String spriteName)
	{
		for (Sprite oneSprite : spriteList)
		{
			if (oneSprite.getName().equalsIgnoreCase(spriteName))
			{
				return oneSprite;
			}
		}
		return null;
	}

	public GameBoardModel()
	{
		setSpriteList(new ArrayList<Sprite>());
		setEventList(new ArrayList<Event>());
	}

	public void setSpriteList(ArrayList<Sprite> spriteList)
	{
		this.spriteList = spriteList;
	}

	public ArrayList<Sprite> getSpriteList()
	{
		return spriteList;
	}

	public void setEventList(ArrayList<Event> eventList)
	{
		this.eventList = eventList;
	}

	public ArrayList<Event> getEventList()
	{
		return eventList;
	}

	public void addSprite(Sprite sprite)
	{
		sprite.setSpriteId(spriteCounter);
		getSpriteList().add(sprite);
		++spriteCounter;
	}

	public void removeSprite(int spriteId)
	{
		Sprite removeSprite = null;
		Iterator<Sprite> itr = getSpriteList().iterator();
		while (itr.hasNext())
		{
			removeSprite = itr.next();
			if (removeSprite.getSpriteId() == spriteId)
				break;
		}
		getSpriteList().remove(removeSprite);

	}

	public void addEvent(Event event)
	{
		GameMaker.logger.logInfo(this.getClass().getName() + " - Before adding event to the event list, Event Count number: " + eventCounter);
		event.setEventId(eventCounter);
		getEventList().add(event);
		++eventCounter;
		GameMaker.logger.logInfo(this.getClass().getName() + " - After adding event to the event list. Event Count number: " + eventCounter);
	}

	public void removeEvent(int eventId)
	{
		Event removeEvent = null;
		Iterator<Event> itr = getEventList().iterator();
		while (itr.hasNext())
		{
			removeEvent = itr.next();
			if (removeEvent.getEventId() == eventId)
				break;
		}
		getEventList().remove(removeEvent);

	}

	public int getSpriteCounter()
	{
		return spriteCounter;
	}

	public void setSpriteCounter(int spriteCounter)
	{
		this.spriteCounter = spriteCounter;
	}

	public int getEventCounter()
	{
		return eventCounter;
	}

	public void setEventCounter(int eventCounter)
	{
		this.eventCounter = eventCounter;
	}

}
