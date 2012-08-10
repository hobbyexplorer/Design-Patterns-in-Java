package controller.actionform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.actionform.ActionFillOutForm;
import gamemaker.actionform.ChaseActionFillOutForm;
import gamemaker.actionform.ShootActionFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.GameCreatePanelController;
import controller.rule.RuleController;

import model.Sprite;
import model.action.Action;
import model.action.Chase;
import model.action.Destroy;

public class ChaseActionFillOutFormController implements ActionFillOutFormController, ActionListener
{

	private ActionFillOutForm fillOutForm;
	Action actionToBeAdded;
	ActionFormsMasterController actionFormsMasterController;
	// The sprite to object to add to event and action list
	private Sprite spriteToAdd;
	
	// The sprite to be passed to chase action
	private Sprite spriteToChase;
	
	// The name of sprite to be passed to chase action
	private String spriteNameToChase;
	
	// The array list containing the list of sprite
	private ArrayList<Sprite> spriteList;
	
	
	public ChaseActionFillOutFormController(ActionFormsMasterController actionFormsMasterController)
	{
		this.actionFormsMasterController = actionFormsMasterController;
		fillOutForm = new ChaseActionFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Chase Action Fill out form controller called.");
		actionToBeAdded = new Destroy(null);
		
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
		spriteNameToChase = ((ChaseActionFillOutForm) this.fillOutForm).getSpriteListCombo().getSelectedItem().toString();
		spriteList = GameCreatePanelController.getInstance().getGameBoardModel().getSpriteList();
		for (Sprite oneSprite : spriteList)
		{
			if(spriteNameToChase.equalsIgnoreCase(oneSprite.getName()))
				spriteToChase = oneSprite;
		}
		
		actionToBeAdded = new Chase(spriteToAdd, spriteToChase);
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
