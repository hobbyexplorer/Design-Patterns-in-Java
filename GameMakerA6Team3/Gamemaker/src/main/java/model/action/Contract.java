package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Contract implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private int contractBy;
	private String actionName;


	public Contract(Sprite sprite, int contractBy)
	{
		this.actionName = Constants.ACTION_CONTRACT;
		this.sprite = sprite;
		this.contractBy = contractBy;
	
	}
	
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(GameObject gameObject)
	{
		gameObject.contract(this.contractBy);
		if(gameObject.getObjectWidth() <= 10 || gameObject.getObjectHeight() <= 10)
			sprite.removeGameObject(gameObject.getObjectId());
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
		saveGame.createNode(actionElement, "contractBy", Integer.toString(contractBy));
				
		return actionElement;
	}


}
