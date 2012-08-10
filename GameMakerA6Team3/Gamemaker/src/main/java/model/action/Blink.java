package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Blink implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;
	//delay in milliseconds
	private long blinkDelay;

	public Blink(Sprite sprite, long blinkDelay)
	{
		this.actionName = Constants.ACTION_BLINK;
		this.sprite = sprite;
		this.blinkDelay = blinkDelay;
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		long currTime = System.currentTimeMillis();
		long elapsedTime = currTime - gameObject.getLastVisibilityToggle();
		if (elapsedTime >= this.blinkDelay){
			gameObject.toggleVisibility();
			gameObject.setLastVisibilityToggle(currTime);
		}
	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{
		
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
		
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "blinkDelay", Long.toString(blinkDelay));
				
		return actionElement;
	}

}
