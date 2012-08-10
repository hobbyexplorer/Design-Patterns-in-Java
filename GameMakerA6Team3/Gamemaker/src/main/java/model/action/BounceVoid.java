package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class BounceVoid implements Action
{
	private int actionType = 2;
	private Sprite sprite;
	private String actionName;

	public BounceVoid(Sprite sprite)
	{
		this.actionName = Constants.ACTION_BOUNCE_VOID;
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

		boolean movingRight = (actionObjectCollisionSubject.getAngle()>358 || actionObjectCollisionSubject.getAngle()<2)?true:false;
		boolean movingLeft = (actionObjectCollisionSubject.getAngle()>178 && actionObjectCollisionSubject.getAngle()<182)?true:false;
		boolean movingUp = (actionObjectCollisionSubject.getAngle()>88 && actionObjectCollisionSubject.getAngle()<92)?true:false;
		boolean movingDown = (actionObjectCollisionSubject.getAngle()>268 && actionObjectCollisionSubject.getAngle()<272)?true:false;
		
		if(movingRight){
			subjectWidthInTarget = targetX-(subjectX+subjectWidth)-1;
		}
		if(movingLeft){
			subjectWidthInTarget = (targetX+targetWidth)-subjectX+1;
		}
		if(movingDown){
			subjectHeightInTarget = targetY-(subjectY+subjectHeight)-1; 
		}
		if(movingUp){
			subjectHeightInTarget = (targetY+targetHeight)-subjectY+1;
		}
		
		if(movingLeft||movingRight){
			actionObjectCollisionSubject.moveHorizontal(subjectWidthInTarget);
		}
		if(movingUp||movingDown){
			actionObjectCollisionSubject.moveVertical(subjectHeightInTarget);
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
