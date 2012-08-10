package model.event;

import org.w3c.dom.Element;

import controller.SaveGame;
import model.action.Action;

public interface Event
{
	public void setEventId(int eventID);

	public int getEventId();

	public void performActions();

	public void addAction(Action action);

	public void removeAction(int index);

	public String getEventName();

	public Element getEventInfoXML(SaveGame saveGame);

	public void setEventString(String eventString);

	public String getEventString();
}
