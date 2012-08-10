package controller.eventaction;

import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gamemaker.Constants;
import gamemaker.EventPanel;
import gamemaker.GameMaker;

public class EventController implements ListSelectionListener
{
	private static EventController instance;

	private EventPanel eventPanel;

	public static EventController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new EventController();
		return instance;
	}

	private EventController()
	{
		eventPanel = new EventPanel();
		eventPanel.getEventList().addListSelectionListener(this);
	}

	public EventPanel getEventPanel()
	{
		return this.eventPanel;
	}

	public String getSelectedEvent()
	{
		return eventPanel.getSelectedEvent();
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		GameMaker.logger.logInfo(this.getClass().getName() + " -  value changed in event list panel");

		if (e.getSource().equals(eventPanel.getEventList()) && (!eventPanel.getEventList().isSelectionEmpty()))
		{
			GameMaker.logger.logInfo(this.getClass().getName() + " - condition checked: event changed in list panel");

			String eventName = eventPanel.getEventList().getSelectedValue().toString();
			if (eventName.equals(Constants.EVENT_KEY_PRESSED))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - key press event got in ");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.KEY_EVENT_ACTION_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
				return;
			}
			if (eventName.equals(Constants.EVENT_COLLISION))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - collision event got in ");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.COLLISION_EVENT_ACTION_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
				return;
			}
			if (eventName.equals(Constants.EVENT_GENERATE_RANDOM))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - Generate random event got in");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.EVENT_GENERATE_RANDOM_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
			}
			if (eventName.equals(Constants.EVENT_GENERATE_AT_POSITION))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - Generate at position event got in");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.EVENT_GENERATE_AT_POSITION_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
			}
			if (eventName.equals(Constants.EVENT_COLLISION_WITH_EXCEPTION))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - Collision with exception event got in");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.EVENT_COLLISION_WITH_EXCEPTION_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
			}
			if (eventName.equals(Constants.EVENT_COUNT))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - Count event got in");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.EVENT_COUNT_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
			}
			if (eventName.equals(Constants.EVENT_TIMER))
			{
				GameMaker.logger.logInfo(this.getClass().getName() + " - Timer tick event got in");
				((DefaultListModel) ActionController.getInstance().getActionList().getModel()).removeAllElements();
				ActionController.getInstance().populateJList(Constants.EVENT_TIMER_NAMES);
				ActionController.getInstance().getActionList().setEnabled(true);
			}

		}
		/*
		 * if (e.getSource().equals(eventPanel.getEventList()) &&
		 * !e.getValueIsAdjusting()) { //
		 * LOG.info("getValueIsAdjusting == false come from spritList"); int
		 * selectedSpriteNumber =
		 * eventPanel.getEventList().getSelectedIndices().length; switch
		 * (selectedSpriteNumber) { case 0:
		 * GameMaker.logger.logInfo(this.getClass().getName() + " case 0");
		 * break;
		 * 
		 * case 1: GameMaker.logger.logInfo(this.getClass().getName() +
		 * " case 1"); break;
		 * 
		 * case 2: GameMaker.logger.logInfo(this.getClass().getName() +
		 * "case 2."); break;
		 * 
		 * default: GameMaker.logger.logInfo(this.getClass().getName() +
		 * " case default"); break; } }
		 */
	}

	public void removeAllEventsInList()
	{
		this.eventPanel.removeAllElementsInEventList();
	}

	public void populateJList(String[] keyEventActionNames)
	{
		this.eventPanel.populateJList(keyEventActionNames);
	}

	public JList getEventList()
	{
		return this.eventPanel.getEventList();
	}
}
