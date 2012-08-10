package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.GameOverActionOnCollisionFillOutForm;
import gamemaker.actionform.GameOverActionOnCountFillOutForm;
import gamemaker.actionform.SetGameTimeActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.GameOver;

public class GameOverActionOnCountFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	public GameOverActionOnCountFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new GameOverActionOnCountFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Game Over on count Action Fill out form controller called.");
	}

	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(visibility);
	}

	public void makeThisLastForm()
	{
		fillOutForm.makeThisLastForm();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Constants.CANCEL_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
		}
		else if (Constants.NEXT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			String message = ((GameOverActionOnCountFillOutForm)fillOutForm).getGameMessage();
			actionToBeAdded = new GameOver(spriteToAdd, message, true);
			int count = Integer.parseInt(((GameOverActionOnCountFillOutForm)fillOutForm).getCount());
			String condition = ((GameOverActionOnCountFillOutForm)fillOutForm).getSign();
			boolean greaterThan = false;
			boolean lessThan = false;
			boolean equalTo = false ;
			
			if(condition.equals(new String("<"))){
				greaterThan = false; lessThan = true; equalTo = false;
			}else if(condition.equals(new String(">="))){
				greaterThan = true; lessThan = false; equalTo = true;
			}else if(condition.equals(new String("<="))){
				greaterThan = false; lessThan = true; equalTo = true;
			}else if(condition.equals(new String("="))){
				greaterThan = false; lessThan = false; equalTo = true;
			}else if(condition.equals(new String(">"))){
				greaterThan = true; lessThan = false; equalTo = false;
			}
			((GameOver)actionToBeAdded).setCondition(count, greaterThan, lessThan, equalTo);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			String message = ((GameOverActionOnCountFillOutForm)fillOutForm).getGameMessage();
			actionToBeAdded = new GameOver(spriteToAdd, message, true);
			int count = Integer.parseInt(((GameOverActionOnCountFillOutForm)fillOutForm).getCount());
			String condition = ((GameOverActionOnCountFillOutForm)fillOutForm).getSign();
			boolean greaterThan = false;
			boolean lessThan = false;
			boolean equalTo = false ;
			
			if(condition.equals(new String("<"))){
				greaterThan = false; lessThan = true; equalTo = false;
			}else if(condition.equals(new String(">="))){
				greaterThan = true; lessThan = false; equalTo = true;
			}else if(condition.equals(new String("<="))){
				greaterThan = false; lessThan = true; equalTo = true;
			}else if(condition.equals(new String("="))){
				greaterThan = false; lessThan = false; equalTo = true;
			}else if(condition.equals(new String(">"))){
				greaterThan = true; lessThan = false; equalTo = false;
			}
			((GameOver)actionToBeAdded).setCondition(count, greaterThan, lessThan, equalTo);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
