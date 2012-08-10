package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveActionFillOutForm;
import gamemaker.actionform.MoveAllActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;
import model.Sprite;
import model.action.Action;
import model.action.Destroy;
import model.action.Move;
import model.action.MoveAll;

public class MoveAllActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	private Sprite spriteToAdd;

	private int spriteSpeed;

	private int angle;

	public MoveAllActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new MoveAllActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Move All Action Fillout form controller called.");

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

	public void addActions()
	{
		spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
		// Parse the slider text and get the angle value. By default it will
		// be 90;
		angle = Integer.parseInt(((MoveAllActionFillOutForm) this.fillOutForm).getAngleText());
		spriteSpeed = spriteToAdd.getSpeed();
		actionToBeAdded = new MoveAll(spriteToAdd, angle, spriteSpeed);
		actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);

	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}
}
