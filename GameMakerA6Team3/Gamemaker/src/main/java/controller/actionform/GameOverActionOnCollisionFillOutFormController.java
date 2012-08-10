package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.GameOverActionOnCollisionFillOutForm;
import gamemaker.actionform.SetGameTimeActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.GameOver;

public class GameOverActionOnCollisionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	public GameOverActionOnCollisionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new GameOverActionOnCollisionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Game Over on collision Action Fill out form controller called.");
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
			String message = ((GameOverActionOnCollisionFillOutForm)fillOutForm).getGameMessage();
			actionToBeAdded = new GameOver(spriteToAdd, message, false);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			String message = ((GameOverActionOnCollisionFillOutForm)fillOutForm).getGameMessage();
			actionToBeAdded = new GameOver(spriteToAdd, message, false);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
