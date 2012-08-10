package model.action;

import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class BlinkRandom implements Action
{
	private int actionType = 0;
	private Sprite sprite;
	private String actionName;
	//delay in milliseconds
	private long blinkDelay;
	private GameObject selectedObject;
	private long blinkFor;
	private long blinkStart;
	private Random generator;
	private long lastBlinkTime = 0;

	public BlinkRandom(Sprite sprite, long blinkDelay, long blinkFor)
	{
		this.actionName = Constants.ACTION_BLINK_RANDOM;
		this.sprite = sprite;
		this.blinkDelay = blinkDelay;
		this.blinkFor = blinkFor;
		this.blinkStart = 0;
		generator = new Random();
	}

	@Override
	public void execute()
	{
		long currTime = System.currentTimeMillis();
		long elapsedTime = currTime - this.lastBlinkTime;
		if (elapsedTime >= this.blinkFor)
		{
			if(this.selectedObject != null)
				this.selectedObject.setVisible(true);

			if(randomGenerator(4) == 2)
			{
				int selectedObjectIndex = randomGenerator(this.sprite.getGameObjects().size());
				this.selectedObject = this.sprite.getGameObjects().get(selectedObjectIndex);
				this.selectedObject.toggleVisibility();
				this.selectedObject.setLastVisibilityToggle(currTime);
			}
			this.lastBlinkTime = currTime;
		}

		else if(this.selectedObject != null)
		{
			long elapsedToggleTime = currTime - this.selectedObject.getLastVisibilityToggle();
			if (elapsedToggleTime >= this.blinkDelay){
				this.selectedObject.toggleVisibility();
				this.selectedObject.setLastVisibilityToggle(currTime);

			}
		}

	}

	@Override
	public void execute(GameObject gameObject)
	{
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

	public int randomGenerator(int range)
	{	
		return generator.nextInt(range);
	}
	
	@Override
	public Element getActionInfoXML(SaveGame saveGame) {
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");
		
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "blinkDelay", Long.toString(blinkDelay));
		saveGame.createNode(actionElement, "blinkFor", Long.toString(blinkFor));
		
		return actionElement;
	}

}
