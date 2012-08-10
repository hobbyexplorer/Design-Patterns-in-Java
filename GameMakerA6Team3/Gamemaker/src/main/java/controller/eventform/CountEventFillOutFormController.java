package controller.eventform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.eventform.CountEventFillOutForm;
import gamemaker.eventform.EventFillOutForm;
import gamemaker.eventform.TimerEventFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Sprite;
import model.action.Action;
import model.event.CountEvent;
import model.event.Event;
import controller.GameCreatePanelController;
import controller.actionform.ActionFormsMasterController;
import controller.eventaction.EventActionController;
import controller.rule.RuleController;

public class CountEventFillOutFormController implements ActionListener, EventFillOutFormController
{
	private EventFillOutForm fillOutForm;

	Event eventToBeAdded;

	ArrayList<String> listOfActionNames;

	ActionFormsMasterController actionFormsMasterController;

	int inputKeyCode;

	public CountEventFillOutFormController()
	{
		actionFormsMasterController = new ActionFormsMasterController(this);
		listOfActionNames = RuleController.getInstance().getSelectedActionNames();
		parseSelectedActions();
		fillOutForm = new CountEventFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - count event fillout form up and waiting for user input.");
	
	}

	private void parseSelectedActions()
	{
		for (String oneActionName : listOfActionNames)
		{
			actionFormsMasterController.addActionController(oneActionName);
		}
	}
	
	@Override
	public void addCombinationToEventActionList()
	{
		EventActionController.getInstance().getEventDisplayPanel().addToEventDisplay(RuleController.getInstance().getCurrentCombination());
		eventToBeAdded.setEventString(RuleController.getInstance().getCurrentCombination());
	}

	@Override
	public void addEventToEventList(Event eventToBeAdded)
	{
		GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Event added to the event list." + eventToBeAdded);
		
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
			Sprite spriteToBeAdded = GameCreatePanelController.getInstance().getGameBoardModel().getSprite(RuleController.getInstance().getSelectedSpriteName());
			eventToBeAdded = new CountEvent(spriteToBeAdded);
			actionFormsMasterController.addEventToBeAdded(eventToBeAdded);
			actionFormsMasterController.showNextWindow();
			// add this event
		}
	}
		
	
	
	public void addAction(Action action)
	{
		eventToBeAdded.addAction(action);
	}

	public EventFillOutForm getFillOutForm()
	{
		return fillOutForm;
	}

}
