package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveActionFillOutForm;
import gamemaker.actionform.MoveRandomActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.Move;
import model.action.MoveRandom;

public class MoveRandomActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	private Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	public MoveRandomActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new MoveRandomActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Move Random Action Fillout form controller called.");

		// ActionController.getInstance().getActionObject(ActionController.getInstance().getSelectedAction());
		// GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
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
			actionToBeAdded = new MoveRandom(spriteToAdd);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			actionToBeAdded = new MoveRandom(spriteToAdd);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	@Override
	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(visibility);
		
	}

	@Override
	public void makeThisLastForm()
	{
		fillOutForm.makeThisLastForm();
	}
	
	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
