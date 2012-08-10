package model.action;

import gamemaker.Constants;
import gamemaker.GameMaker;
import model.GameObject;

import org.w3c.dom.Element;

import controller.GameBoardController;
import controller.SaveGame;

public class ScoreForCollision implements Action
{
	private int scoreToBeAdded;
	private String actionName;
	private int overallScore;
	private int secondCounter;

	public ScoreForCollision(int scoreToBeAdded)
	{
		this.actionName = Constants.ACTION_SCORE;
		this.scoreToBeAdded = scoreToBeAdded;
		this.overallScore = 0;
		this.secondCounter = 0;
	}

	@Override
	public void execute()
	{
		if (++secondCounter > 10)
		{
			secondCounter = 0;
			this.overallScore = GameBoardController.getInstance().incrementScoreCountBy(this.scoreToBeAdded);
			GameMaker.gameScore.setText("Score :" + Integer.toString(overallScore));
		}
	}

	@Override
	public void execute(GameObject gameObject)
	{

	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{
	}

	@Override
	public int getSpriteId()
	{
		return 0;
	}

	@Override
	public int getActionType()
	{
		return 0;
	}

	@Override
	public String getActionName()
	{
		return null;
	}

	@Override
	public Element getActionInfoXML(SaveGame saveGame)
	{
		return null;
	}

}
