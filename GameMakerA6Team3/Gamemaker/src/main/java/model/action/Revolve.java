package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Revolve implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;

	public Revolve(Sprite sprite)
	{
		this.actionName = Constants.ACTION_REVOLVE;
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
		if ((gameObject.getX() + gameObject.getObjectWidth()) > Constants.GAMEBOARD_WIDTH)
			gameObject.setX(0);
		else if (gameObject.getX() < 0)
			gameObject.setX(Constants.GAMEBOARD_WIDTH - gameObject.getObjectWidth());

		if ((gameObject.getY() + gameObject.getObjectHeight()) > Constants.GAMEBOARD_HEIGHT)
			gameObject.setY(0);
		else if (gameObject.getY() < 0)
			gameObject.setY(Constants.GAMEBOARD_HEIGHT - gameObject.getObjectHeight());

		/*
		 * if((gameObject.getX() + gameObject.getObjectWidth()) > 620 ){
		 * gameObject.setX(0); gameObject.setY(700 - gameObject.getY());
		 * System.out.println("here 1"); }
		 * 
		 * else if(gameObject.getX() < 0 ){ gameObject.setX(620 -
		 * gameObject.getObjectWidth()); gameObject.setY(700 -
		 * gameObject.getY()); System.out.println("here 2"); }
		 * 
		 * 
		 * if((gameObject.getY() + gameObject.getObjectHeight()) > 700){
		 * gameObject.setY(0); gameObject.setX(620 - gameObject.getX());
		 * System.out.println("here 3"); }
		 * 
		 * 
		 * else if(gameObject.getY() < 0){ gameObject.setY(700 -
		 * gameObject.getObjectHeight()); gameObject.setX(620 -
		 * gameObject.getX()); System.out.println("here 4"); }
		 */}

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
				
		return actionElement;
	}

}
