package model.action;

import java.util.Random;

import gamemaker.Constants;
import gamemaker.GameMaker;
import model.GameBoardModel;
import model.GameObject;
import model.Sprite;
import model.event.TimerEvent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;

public class Chase implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private Sprite chaseSprite;
	private TimerEvent timerEvent;
	private String actionName;
	private Random random;
	private int direction;

	public Chase(Sprite sprite, Sprite chaseSprite)
	{
		this.actionName = Constants.ACTION_CHASE;
		this.sprite = sprite;
		this.chaseSprite = chaseSprite;
		GameMaker.logger.logInfo("sprite selected is " + this.sprite.getName() );
		GameMaker.logger.logInfo("chasesprite selected is " + this.chaseSprite.getName() );
		random = new Random();
		int direction = random.nextInt(3);
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
		
		GameObject newObject;
		newObject = chaseSprite.getGameObjects().get(0);
		
		GameMaker.logger.logInfo("newobject selected is " + newObject );
		GameMaker.logger.logInfo("gameObject selected is " + gameObject );
		
		if(newObject.getX() == gameObject.getX() )
		{
			if(gameObject.getY() <= newObject.getY())
			{
				gameObject.moveDown();
				direction =0;
				GameMaker.logger.logInfo("chase move down" );
			}
			else
			{
				gameObject.moveUp();
				direction =1;
				GameMaker.logger.logInfo("chase move up" );
			}
		}
		else if(newObject.getY() == gameObject.getY() )
		{
			if(gameObject.getX() <= newObject.getX())
			{
				gameObject.moveRight();
				direction =2;
				GameMaker.logger.logInfo("chase move right" );
			}
			else
			{
				gameObject.moveLeft();
				direction =3;
				GameMaker.logger.logInfo("chase move left" );
			}
		}
		else
		{
			if(direction ==0)
			{
				gameObject.moveDown();
			}
			else if (direction ==1)
			{
				gameObject.moveUp();
			}
			else if(direction ==2)
			{
				gameObject.moveRight();
			}
			else if(direction ==3)
			{
				gameObject.moveLeft();
			}
		}
	}

	@Override
	public void execute(GameObject GameObject1, GameObject GameObject2)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getSpriteId()
	{
		// TODO Auto-generated method stub
		return this.sprite.getSpriteId();
	}

	@Override
	public int getActionType()
	{
		// TODO Auto-generated method stub
		return this.actionType;
	}

	@Override
	public String getActionName()
	{
		// TODO Auto-generated method stub
		return this.actionName;
	}

	@Override
	public Element getActionInfoXML(SaveGame saveGame)
	{
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");
		
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "chaseSprite", Integer.toString(chaseSprite.getSpriteId()));
				
		return actionElement;
	}

}
