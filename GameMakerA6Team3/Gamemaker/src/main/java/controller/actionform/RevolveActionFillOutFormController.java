package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.RevolveActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Sprite;
import model.action.Revolve;
import controller.GameCreatePanelController;
import controller.rule.RuleController;

public class RevolveActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	
	private ActionFillOutForm fillOutForm;

	private model.action.Action actionToBeAdded;
	
	ActionFormsMasterController actionFormsMasterController;
	
	public RevolveActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new RevolveActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName()+" - Revolve action fillout form controller called");
	}

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
			actionToBeAdded = new Revolve(spriteToAdd);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.showNextWindow();
			
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			Sprite spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			actionToBeAdded = new Revolve(spriteToAdd);
			actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
			actionFormsMasterController.submitForm();
		}
	}	
	

	@Override
	public void setVisible(boolean visibility)
	{
		fillOutForm.setVisible(true);
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
