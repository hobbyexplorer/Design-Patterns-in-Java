package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveActionFillOutForm;
import gamemaker.actionform.TwoSpriteMoveAllActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Sprite;
import model.action.Action;
import model.action.MoveAll;
import controller.GameCreatePanelController;
import controller.rule.RuleController;

public class TwoSpriteMoveAllActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	private Sprite selectedSprite;

	public TwoSpriteMoveAllActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new TwoSpriteMoveAllActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Move Action Fillout form controller called.");
	//	((TwoSpriteMoveAllActionFillOutForm)fillOutForm).
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
			
			// Parse the slider text and get the angle value. By default it will
			// be 90;
			int angle = Integer.parseInt(((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getAngleText());
			int speed = Integer.parseInt(((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getSpeedText());
			Sprite selectedSprite = ((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getSelectedSprite();
			
			actionToBeAdded = new MoveAll(selectedSprite, angle, speed);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();

			// Parse the slider text and get the angle value. By default it will
			// be 90;
			int angle = Integer.parseInt(((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getAngleText());
			int speed = Integer.parseInt(((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getSpeedText());
			Sprite selectedSprite = ((TwoSpriteMoveAllActionFillOutForm) this.fillOutForm).getSelectedSprite();

			actionToBeAdded = new MoveAll(selectedSprite, angle, speed);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
