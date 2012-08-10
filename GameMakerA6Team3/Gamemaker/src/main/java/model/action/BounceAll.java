package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class BounceAll implements Action
{
	private int actionType = 2;
	private Sprite sprite;
	private String actionName;

	public BounceAll(Sprite sprite)
	{
		this.actionName = Constants.ACTION_BOUNCE_ALL;
		this.sprite = sprite;
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject actionObjectCollisionTarget, GameObject actionObjectCollisionSubject)
	{
		//System.out.println("In Bounce all");
		int subjectX = actionObjectCollisionSubject.getX();
		int subjectY = actionObjectCollisionSubject.getY();
		int subjectWidth = actionObjectCollisionSubject.getObjectWidth();
		int subjectHeight = actionObjectCollisionSubject.getObjectHeight();

		int targetX = actionObjectCollisionTarget.getX();
		int targetY = actionObjectCollisionTarget.getY();
		int targetWidth = actionObjectCollisionTarget.getObjectWidth();
		int targetHeight = actionObjectCollisionTarget.getObjectHeight();

		int subjectHeightInTarget = 0;
		int subjectWidthInTarget = 0;

		if (subjectY < targetY)
		{
			subjectHeightInTarget = ((subjectY + subjectHeight) < (targetY + targetHeight)) ? ((subjectY + subjectHeight) - targetY) : ((targetY + targetHeight) - targetY);
		}
		else
		{
			subjectHeightInTarget = ((subjectY + subjectHeight) < (targetY + targetHeight)) ? ((subjectY + subjectHeight) - subjectY) : ((targetY + targetHeight) - subjectY);
		}

		if (subjectX < targetX)
		{
			subjectWidthInTarget = ((subjectX + subjectWidth) < (targetX + targetWidth)) ? ((subjectX + subjectWidth) - targetX) : ((targetX + targetWidth) - targetX);
		}
		else
		{
			subjectWidthInTarget = ((subjectX + subjectWidth) < (targetX + targetWidth)) ? ((subjectX + subjectWidth) - subjectX) : ((targetX + targetWidth) - subjectX);
		}

		if (subjectWidthInTarget < subjectHeightInTarget)
		{
			reflectX(actionObjectCollisionSubject);
		}
		else if (subjectWidthInTarget > subjectHeightInTarget)
		{
			reflectY(actionObjectCollisionSubject);
		}
		else
		{
			reflectX(actionObjectCollisionSubject);
			reflectY(actionObjectCollisionSubject);
		}

	}

	private void reflectX(GameObject actionObjectCollisionSubject)
	{
		GameObject newObject;
		for(int index=0; index<this.sprite.getGameObjects().size(); index++)
		{
			newObject = this.sprite.getGameObjects().get(index);
			newObject.reflectFromVerticalSurface();
			newObject.moveObject();
		}

	}

	private void reflectY(GameObject actionObjectCollisionSubject)
	{
		GameObject newObject;
		for(int index=0; index<this.sprite.getGameObjects().size(); index++)
		{
			newObject = this.sprite.getGameObjects().get(index);
			newObject.reflectFromHorizontalSurface();
			newObject.moveObject();
		}
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
				
		return actionElement;
	}

}
