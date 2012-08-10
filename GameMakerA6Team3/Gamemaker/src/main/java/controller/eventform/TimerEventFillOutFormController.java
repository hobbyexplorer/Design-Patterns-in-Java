package controller.eventform;

import gamemaker.Constants;
import gamemaker.GameMaker;
import gamemaker.eventform.EventFillOutForm;
import gamemaker.eventform.KeyPressEventFillOutForm;
import gamemaker.eventform.TimerEventFillOutForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import controller.GameCreatePanelController;
import controller.actionform.ActionFormsMasterController;
import controller.actionform.KeyEventFormInputListener;
import controller.eventaction.EventActionController;
import controller.rule.RuleController;
import model.Sprite;
import model.action.Action;
import model.event.Event;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;

public class TimerEventFillOutFormController implements ActionListener, EventFillOutFormController
{
	private EventFillOutForm fillOutForm;

	Event eventToBeAdded;

	ArrayList<String> listOfActionNames;

	ActionFormsMasterController actionFormsMasterController;

	int inputKeyCode;

	public TimerEventFillOutFormController()
	{
		actionFormsMasterController = new ActionFormsMasterController(this);
		listOfActionNames = RuleController.getInstance().getSelectedActionNames();
		parseSelectedActions();
		fillOutForm = new TimerEventFillOutForm();
		fillOutForm.AddActionListenerToButtons(this);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Timer event fillout form up and waiting for user input.");
	}

	private void parseSelectedActions()
	{
		for (String oneActionName : listOfActionNames)
		{
			actionFormsMasterController.addActionController(oneActionName);
		}
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
			eventToBeAdded = new TimerEvent(spriteToBeAdded);
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