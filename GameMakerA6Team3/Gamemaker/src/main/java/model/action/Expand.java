package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Expand implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private int expandBy;
	private String actionName;


	public Expand(Sprite sprite, int expandBy)
	{
		this.actionName = Constants.ACTION_EXPAND;
		this.sprite = sprite;
		this.expandBy = expandBy;
	
	}
	
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(GameObject gameObject)
	{
		gameObject.expand(this.expandBy);
	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{
		// TODO Auto-generated method stub
		
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
		saveGame.createNode(actionElement, "expandBy", Integer.toString(expandBy));
				
		return actionElement;
	}


}
