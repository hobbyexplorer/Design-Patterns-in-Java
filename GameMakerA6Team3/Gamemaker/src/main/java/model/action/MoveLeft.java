package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class MoveLeft implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;
	private Boolean rotateImage;
	

	public MoveLeft(Sprite sprite, Boolean rotateImage)
	{
		this.actionName = Constants.ACTION_MOVE_LEFT;
		this.sprite = sprite;
		this.rotateImage = rotateImage;
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		
		if(this.rotateImage){
			if(gameObject.getAngle() == 180){
				gameObject.moveObject();
			}
			else{
				int rotate = 180 - gameObject.getAngle();
				gameObject.updateAngle(rotate);
				gameObject.updateImageRotationAngle(rotate);
				gameObject.moveObject();
			}
		}
		else gameObject.moveLeft();
		/*if(gameObject.getAngle()!=gameObject.getImageRotationAngle())
			gameObject.updateImageRotationAngle(gameObject.getAngle());*/
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
		saveGame.createNode(actionElement, "rotateImage", rotateImage.toString());
				
		return actionElement;
	}

}
