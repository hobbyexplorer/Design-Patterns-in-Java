package controller.eventform;

import gamemaker.GameMaker;
import gamemaker.eventform.EventFillOutForm;
import gamemaker.eventform.GenerateRandomEventFillOutForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameCreatePanelController;
import controller.eventaction.EventActionController;
import controller.rule.RuleController;

import model.event.Event;
import model.event.GenerateRandom;

public class GenerateRandomEventFillOutFormController implements ActionListener, EventFillOutFormController
{
	private EventFillOutForm fillOutForm;

	Event eventToBeAdded;

	public GenerateRandomEventFillOutFormController()
	{
		fillOutForm = new GenerateRandomEventFillOutForm();
		eventToBeAdded = new GenerateRandom(null, null, 0, 0, 0, 0);
		GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
		
		GameMaker.logger.logInfo(this.getClass().getName() + " - Genrate random event added to the gameboard model.");
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

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

}
