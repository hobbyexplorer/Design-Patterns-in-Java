package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.RotateActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;
import model.Sprite;
import model.action.Action;
import model.action.Rotate;

public class RotateActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	private Action actionToBeAdded;

	private ActionFormsMasterController actionFormsMasterController;

	public RotateActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new RotateActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Rotate Action Fillout form controller called.");

		// ActionController.getInstance().getActionObject(ActionController.getInstance().getSelectedAction());
		// GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
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
			addActions();
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			addActions();
			actionFormsMasterController.submitForm();
		}
	}

	public void addActions(){
		Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
		// Parse the slider text and get the angle value. By default it will
		// be 90;
		int angle = Integer.parseInt(((RotateActionFillOutForm) this.fillOutForm).getAngleText());
		actionToBeAdded = new Rotate(spriteToAdd, angle);
		actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}
}
