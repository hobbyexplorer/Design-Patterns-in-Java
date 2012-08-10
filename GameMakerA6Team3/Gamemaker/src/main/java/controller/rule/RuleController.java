package controller.rule;

import java.util.ArrayList;
import model.event.Event;
import model.event.GenerateAtPosition;
import controller.GameCreatePanelController;
import controller.SpriteDisplayPanelController;
import controller.eventaction.ActionController;
import controller.eventaction.EventController;
import controller.eventform.CollisionEventFillOutFormController;
import controller.eventform.CollisionExEventFillOutFormController;
import controller.eventform.CountEventFillOutFormController;
import controller.eventform.GenerateRandomEventFillOutFormController;
import controller.eventform.KeyPressEventFillOutFormController;
import controller.eventform.TimerEventFillOutFormController;
import gamemaker.Constants;
import gamemaker.GameMaker;

/**
 * This class is responsible for adding the rules to the event action list. This
 * class adds the combination of Sprite + Event name + Action name to the JList
 * in the game construction area, as well as it adds a new Event entry in the
 * event list.
 */
public class RuleController
{
	private static RuleController instance;

	private ArrayList<String> selectedActionName;
	private String selectedEventName;
	private String selectedSpriteName;
	private String eventCombination;

	public static RuleController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new RuleController();
		return instance;
	}

	private RuleController()
	{
		selectedActionName = new ArrayList<String>();
	}

	/**
	 * 
	 * @return String[] selected action names in a string array
	 */
	public ArrayList<String> getSelectedActionNames()
	{
		return selectedActionName;
	}

	/**
	 * Returns the selected sprite name form game board
	 * 
	 * @return String
	 */
	public String getSelectedSpriteName()
	{
		return selectedSpriteName;
	}

	/**
	 * Returns the selected event name from the game board.
	 * 
	 * @return String
	 */
	public String getSelectedEventName()
	{
		return selectedEventName;
	}

	public String getCurrentCombination()
	{
		return eventCombination;
	}

	/**
	 * Will return an array list of Strings. Should only be called by actions that
	 * are compatible with two Sprites.
	 * 
	 * @return String[]
	 */
	public ArrayList<String> getSelectedSprites()
	{
		return SpriteDisplayPanelController.getInstance().getSelectedValues();
	}

	public void addRule()
	{
		// TODO: Add the following commented validation.
		/*
		 * if (ifNotSelected(spriteList) || ifNotSelected(eventList) ||
		 * ifNotSelected(actionList)) { JOptionPane.showMessageDialog(this,
		 * "Please choose Sprite, Event and Action", "Invalid Selection",
		 * JOptionPane.INFORMATION_MESSAGE); }
		 */
		selectedActionName.clear();
		selectedSpriteName = SpriteDisplayPanelController.getInstance().getSelectedSpriteName();
		selectedEventName = EventController.getInstance().getSelectedEvent();
		for (Object oneActionName : ActionController.getInstance().getSelectedActions())
		{
			selectedActionName.add(oneActionName.toString());
		}

		eventCombination = selectedSpriteName + " + " + selectedEventName + " + " + selectedActionName;
		Event eventToBeAdded;

		GameMaker.logger.logInfo(this.getClass().getName() + " - Adding sprite event action.");
		GameMaker.logger.logInfo(this.getClass().getName() + "  - Selected sprite name is: " + selectedSpriteName);
		GameMaker.logger.logInfo(this.getClass().getName() + "  - Selected event name is: " + selectedEventName);
		// GameMaker.logger.logInfo(this.getClass().getName() +
		// "  - Selected action name is: " + selectedActionName);

		for (String oneSelectedAction : selectedActionName)
		{

			GameMaker.logger.logInfo(this.getClass().getName() + "  - Selected action name is: " + oneSelectedAction);
		}

		// Create an event object and add it to the event list in game board
		// model.
		if (Constants.EVENT_KEY_PRESSED.equalsIgnoreCase(selectedEventName))
		{
			new KeyPressEventFillOutFormController();
		}
		else if (Constants.EVENT_GENERATE_RANDOM.equalsIgnoreCase(selectedEventName))
		{
			new GenerateRandomEventFillOutFormController();
		}
		else if (Constants.EVENT_TIMER.equalsIgnoreCase(selectedEventName))
		{
			GameMaker.logger.logInfo(this.getClass().getName() + " - Invoking Timer event fill out form...");
			new TimerEventFillOutFormController();
		}
		else if (Constants.EVENT_COLLISION.equalsIgnoreCase(selectedEventName))
		{
			GameMaker.logger.logInfo(this.getClass().getName() + " - Invoking collision event fill out form...");
			new CollisionEventFillOutFormController();
		}
		else if (Constants.EVENT_COLLISION_WITH_EXCEPTION.equalsIgnoreCase(selectedEventName))
		{
			GameMaker.logger.logInfo(this.getClass().getName() + " - Invoking collision with exception event fill out form...");
			new CollisionExEventFillOutFormController();
		}
		else if (Constants.EVENT_GENERATE_AT_POSITION.equalsIgnoreCase(selectedEventName))
		{
			eventToBeAdded = new GenerateAtPosition(null, 0, 0, 0, 0, 0, 0);
			GameCreatePanelController.getInstance().getGameBoardModel().addEvent(eventToBeAdded);
			GameMaker.logger.logInfo(this.getClass().getName() + " - Generate at position event added to the gameboard model.");
		}
		else if (Constants.EVENT_COUNT.equalsIgnoreCase(selectedEventName))
		{
			GameMaker.logger.logInfo(this.getClass().getName() + " - Invoking Count event fill out form...");
			new CountEventFillOutFormController();
		}
	
	}
}
