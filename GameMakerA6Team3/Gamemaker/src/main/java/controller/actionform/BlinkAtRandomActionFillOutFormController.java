package controller.actionform;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.BlinkAtRandomActionFillOutForm;
import model.Sprite;
import model.action.Action;
import model.action.BlinkRandom;

public class BlinkAtRandomActionFillOutFormController implements ActionFillOutFormController, ActionListener
{

	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	public BlinkAtRandomActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new BlinkAtRandomActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Blink at Random Action Fillout form controller called.");

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
			long blinkDelay = Integer.parseInt(((BlinkAtRandomActionFillOutForm) this.fillOutForm).getBlinkDelayText());
			long blinkFor = Integer.parseInt(((BlinkAtRandomActionFillOutForm) this.fillOutForm).getBlinkForText());
			actionToBeAdded = new BlinkRandom(spriteToAdd, blinkDelay, blinkFor);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			long blinkDelay = Integer.parseInt(((BlinkAtRandomActionFillOutForm) this.fillOutForm).getBlinkDelayText());
			long blinkFor = Integer.parseInt(((BlinkAtRandomActionFillOutForm) this.fillOutForm).getBlinkForText());
			actionToBeAdded = new BlinkRandom(spriteToAdd, blinkDelay, blinkFor);
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
