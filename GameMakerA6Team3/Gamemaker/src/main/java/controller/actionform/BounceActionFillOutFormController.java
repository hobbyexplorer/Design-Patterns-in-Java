package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.BounceActionFillOutForm;
import gamemaker.actionform.TwoSpriteMoveAllActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Sprite;
import model.action.Action;
import model.action.Bounce;

public class BounceActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	public BounceActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new BounceActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Bounce Action Fill out form controller called.");
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
			Sprite selectedSprite = ((BounceActionFillOutForm) this.fillOutForm).getSelectedSprite();
			actionToBeAdded = new Bounce(selectedSprite);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite selectedSprite = ((BounceActionFillOutForm) this.fillOutForm).getSelectedSprite();
			actionToBeAdded = new Bounce(selectedSprite);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
