package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Bounce implements Action
{
	private int actionType = 2;
	private Sprite sprite;
	private String actionName;

	public Bounce(Sprite sprite)
	{
		this.actionName = Constants.ACTION_BOUNCE;
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
		System.out.println("action exe");
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
			System.out.println("Height in top: "+subjectHeightInTarget);
			System.out.println(subjectWidthInTarget);
		}
		else
		{
			subjectHeightInTarget = ((subjectY + subjectHeight) < (targetY + targetHeight)) ? ((subjectY + subjectHeight) - subjectY) : ((targetY + targetHeight) - subjectY);
			System.out.println("Height in bottom: "+subjectHeightInTarget);
			System.out.println(subjectWidthInTarget);
		}

		if (subjectX < targetX)
		{
			subjectWidthInTarget = ((subjectX + subjectWidth) < (targetX + targetWidth)) ? ((subjectX + subjectWidth) - targetX) : ((targetX + targetWidth) - targetX);
			System.out.println("Width in left: "+subjectWidthInTarget);
		}
		else
		{
			subjectWidthInTarget = ((subjectX + subjectWidth) < (targetX + targetWidth)) ? ((subjectX + subjectWidth) - subjectX) : ((targetX + targetWidth) - subjectX);
			System.out.println("Width in Right: "+subjectWidthInTarget);
		}

		if (subjectWidthInTarget < subjectHeightInTarget)
		{
			System.out.println("reflectx");
			reflectX(actionObjectCollisionSubject);
		}
		else if (subjectWidthInTarget > subjectHeightInTarget)
		{
			System.out.println("reflecty");
			reflectY(actionObjectCollisionSubject);
		}
		else
		{
			System.out.println("reflectxy");
			reflectX(actionObjectCollisionSubject);
			reflectY(actionObjectCollisionSubject);
		}
		

	}

	private void reflectX(GameObject actionObjectCollisionSubject)
	{
		actionObjectCollisionSubject.reflectFromVerticalSurface();
		actionObjectCollisionSubject.moveObject();

	}

	private void reflectY(GameObject actionObjectCollisionSubject)
	{
		System.out.println(actionObjectCollisionSubject.getAngle());
		actionObjectCollisionSubject.reflectFromHorizontalSurface();
		System.out.println(actionObjectCollisionSubject.getAngle());
		actionObjectCollisionSubject.moveObject();
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
