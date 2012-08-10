package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.ContractActionFillOutForm;
import gamemaker.actionform.ExpandActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Sprite;
import model.action.Action;
import model.action.Contract;
import model.action.Expand;

public class ContractActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;

	ActionFormsMasterController actionFormsMasterController;

	private Sprite selectedSprite;

	public ContractActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new ContractActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Contract action fill out form controller called.");
		// ((TwoSpriteMoveAllActionFillOutForm)fillOutForm).
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

			int contractBy = Integer.parseInt(((ContractActionFillOutForm) this.fillOutForm).getExpandPercent());

			Sprite selectedSprite = ((ContractActionFillOutForm) this.fillOutForm).getSelectedSprite();

			actionToBeAdded = new Contract(selectedSprite, contractBy);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();

			// Parse the slider text and get the angle value. By default it will
			// be 90;
			int contractBy = Integer.parseInt(((ContractActionFillOutForm) this.fillOutForm).getExpandPercent());
			Sprite selectedSprite = ((ContractActionFillOutForm) this.fillOutForm).getSelectedSprite();

			actionToBeAdded = new Contract(selectedSprite, contractBy);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}

	public ActionFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}
}