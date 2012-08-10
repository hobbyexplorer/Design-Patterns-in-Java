package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveActionFillOutForm;
import gamemaker.actionform.MoveAlongActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Sprite;
import model.action.Action;
import model.action.MoveAlong;
import model.action.MoveAll;
import controller.GameCreatePanelController;
import controller.rule.RuleController;

public class MoveAlongActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	private Sprite selectedSprite;

	public MoveAlongActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new MoveAlongActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Move Action Fillout form controller called.");
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
		selectedSprite = ((MoveAlongActionFillOutForm) this.fillOutForm).getSelectedSprite();	
		actionToBeAdded = new MoveAlong(selectedSprite);
		actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);		
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
