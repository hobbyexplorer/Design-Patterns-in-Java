package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.GameBoardController;
import controller.SaveGame;
import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.DestroyActionFillOutForm;
import model.GameObject;
import model.Sprite;

public class Destroy implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;
	private ActionFillOutForm fillOutForm;

	public Destroy(Sprite sprite)
	{
		this.actionName = Constants.ACTION_DESTROY;
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
	public Element getActionInfoXML(SaveGame saveGame)
	{
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");

		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "actionString", actionName);

		return actionElement;
	}

}
