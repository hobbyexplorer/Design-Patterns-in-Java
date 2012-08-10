package controller.eventaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Sprite;
import model.event.Event;
import controller.GameCreatePanelController;

import gamemaker.EventDisplayPanel;

public class EventActionController implements ActionListener
{

	private static EventActionController instance;

	private EventDisplayPanel eventDisplayPanel;

	public static EventActionController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new EventActionController();
		return instance;
	}

	private EventActionController()
	{
		eventDisplayPanel = new EventDisplayPanel();
	}

	public void updateEventActionJList()
	{
		ArrayList<Event> eventList;
		eventList = GameCreatePanelController.getInstance().getGameBoardModel().getEventList();
		for (Event oneEvent : eventList)
		{
			eventDisplayPanel.addToEventDisplay(oneEvent.getEventString());
		}
	}

	public EventDisplayPanel getEventDisplayPanel()
	{
		return eventDisplayPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub

	}
}
