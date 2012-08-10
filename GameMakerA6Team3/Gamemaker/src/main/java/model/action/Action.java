package model.action;

import org.w3c.dom.Element;

import controller.SaveGame;

import model.GameObject;

public interface Action
{	
	public void execute();
	public void execute(GameObject gameObject);
	public void execute(GameObject gameObject1, GameObject gameObject2);
	public int getSpriteId();
	public int getActionType();
	public String getActionName();
	public Element getActionInfoXML(SaveGame saveGame);
	
}
