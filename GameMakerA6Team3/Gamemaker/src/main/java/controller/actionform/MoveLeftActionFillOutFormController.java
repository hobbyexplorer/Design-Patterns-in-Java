package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveLeftActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.Destroy;
import model.action.MoveLeft;

public class MoveLeftActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;
	
	ActionFormsMasterController actionFormsMasterController;
	
	// The boolean to check whether to rotate the sprite
	private boolean rotateImage = false;
	
	// The String to store the rotate image value
	private String rotateImageString;
	
	// The sprite to object to add to event and action list
	private Sprite spriteToAdd;
	
	public MoveLeftActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new MoveLeftActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Move Left Action Fillout form controller called.");

		actionToBeAdded = new Destroy(null);
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
			addAction();
			actionFormsMasterController.showNextWindow();
		}
		else if (Constants.SUBMIT_BUTTON.equalsIgnoreCase(e.getActionCommand()))
		{
			fillOutForm.disposeFrame();
			addAction();
			actionFormsMasterController.submitForm();
		}
		
	}
	
	public void addAction(){
		spriteToAdd = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
		rotateImageString = ((MoveLeftActionFillOutForm) this.fillOutForm).getRotateSprite().getSelectedItem().toString();
		if(rotateImageString.equalsIgnoreCase("yes"))
			rotateImage = true;
		
		actionToBeAdded = new MoveLeft(spriteToAdd, rotateImage);
		actionFormsMasterController.addActionToCurrentEvent(actionToBeAdded);
		
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
