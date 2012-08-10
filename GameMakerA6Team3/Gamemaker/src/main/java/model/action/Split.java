package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Split implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private Sprite splitSprite;
	private String actionName;

	public Split(Sprite sprite, Sprite splitSprite)
	{
		this.actionName = Constants.ACTION_SPLIT;
		this.sprite = sprite;
		this.splitSprite = splitSprite;
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		int width = gameObject.getObjectWidth();
		int height = gameObject.getObjectHeight();
		int x = gameObject.getX();
		int y = gameObject.getY();
		int tempX = x;
		int tempY = y;
		GameObject newObject;
		for (int objectCounter = 0; objectCounter < 4; objectCounter++)
		{
			if (objectCounter == 0 || objectCounter == 2)
				tempX = x;

			if (objectCounter == 0 || objectCounter == 1)
				tempY = y;

			if (objectCounter == 1 || objectCounter == 3)
				tempX = x + width / 2;

			if (objectCounter == 2 || objectCounter == 3)
				tempY = y + height / 2;

			newObject = splitSprite.addGameObject();
			newObject.setX(tempX);
			newObject.setY(tempY);

		}

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
		
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "splitSprite", Integer.toString(splitSprite.getSpriteId()));
				
		return actionElement;
	}

}
