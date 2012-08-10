package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.MoveDownActionFillOutForm;
import gamemaker.actionform.SplitActionFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.Destroy;
import model.action.MoveDown;
import model.action.Split;

public class SplitActionFillOutFormController implements ActionFillOutFormController, ActionListener
{
	private ActionFillOutForm fillOutForm;

	Action actionToBeAdded;
	
	ActionFormsMasterController actionFormsMasterController;
	
	// The sprite to object to add to event and action list
	private Sprite spriteToGenerate;
	
	private String spriteToGenerateName;
	
	// The sprite to be passed to Split action
	private Sprite spriteToSplit;
	
	// The name of sprite to be passed to Split action
	private String spriteNameToSplit;
	
	// The array list containing the list of sprite
	private ArrayList<Sprite> spriteList;
	
	
	public SplitActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new SplitActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Split Action Fillout form controller called.");

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
		spriteNameToSplit = ((SplitActionFillOutForm) this.fillOutForm).getSelectedSpritesCombo().getSelectedItem().toString();
		spriteToGenerateName = ((SplitActionFillOutForm) this.fillOutForm).getSpriteListCombo().getSelectedItem().toString();
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			if(spriteNameToSplit.equalsIgnoreCase(oneSprite.getName()))
				spriteToSplit = oneSprite;
			else if(spriteToGenerateName.equalsIgnoreCase(oneSprite.getName()))
				spriteToGenerate = oneSprite;
		}
		
		actionToBeAdded = new Split(spriteToSplit, spriteToGenerate);
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
