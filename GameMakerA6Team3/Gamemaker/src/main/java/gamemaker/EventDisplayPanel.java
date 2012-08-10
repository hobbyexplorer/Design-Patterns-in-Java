package gamemaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Class Name: EventDisplayPanel.java Class Responsibilities: Responsible for
 * the display of the event Class collaborators: GameMaker.java
 */
public class EventDisplayPanel
{

	// The event display panel.
	private JPanel eventDisplayPanel;

	// The event list.
	private JList eventList;

	// The model.
	private DefaultListModel model;

	/** The event display scroll. */
	private JScrollPane eventDisplayScroll;

	// Constructor EventDisplayPanel: Instantiates a new event display panel.

	public JList getEventList()
	{
		return eventList;
	}
	public EventDisplayPanel()
	{
		this.eventDisplayPanel = new JPanel();
		this.model = new DefaultListModel();

		this.eventList = new JList(this.model);
		
		this.eventDisplayScroll = new JScrollPane(this.eventList);
		
		this.eventDisplayScroll.setPreferredSize(new Dimension(Constants.EVENTDISPLAY_SCROLL_WIDTH, Constants.EVENTDISPLAY_SCROLL_HEIGHT));

		getEventDisplayPanel().setBorder(BorderFactory.createTitledBorder(Constants.EVENTDISPLAY_PANEL_TITLE));
		getEventDisplayPanel().setLayout(new FlowLayout());
		getEventDisplayPanel().setVisible(true);

		this.eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.eventList.setLayoutOrientation(JList.VERTICAL);
		this.eventList.setVisibleRowCount(1);
		this.eventDisplayPanel.setBackground(Color.white);
		this.eventDisplayPanel.add(eventDisplayScroll);
	}

	/*
	 * Method getSelectedEvent : Gets the selected event.
	 */
	public int getSelectedEvent()
	{

		return this.eventList.getSelectedIndex();
	}

	/* Method getEventDisplayPanel: Gets the event display panel. */
	public JPanel getEventDisplayPanel()
	{
		return this.eventDisplayPanel;
	}

	/* Method setEventDisplayPanel: Sets the event display panel. */
	public void setEventDisplayPanel(JPanel eventDisplayPanel)
	{
		this.eventDisplayPanel = eventDisplayPanel;
	}

	/* Method addToEventDisplay: Adds the to event display. */
	public void addToEventDisplay(String spriteEventActionName)
	{
		this.model.addElement(spriteEventActionName);
	}

	/* Method removeFromEventDisplay: Removes the from event display. */
	public void removeFromEventDisplay(String spriteEventActionName)
	{
		this.model.removeElement(spriteEventActionName);
	}

	/*
	 * Method getSelectedSpriteEventAction: Gets the selected sprite event
	 * action.
	 */
	public String getSelectedSpriteEventAction()
	{
		return eventList.getSelectedValue().toString();
	}

	/*
	 * Method emptyEventDisplay: Empty event display.
	 */
	public void emptyEventDisplay()
	{

		this.model.removeAllElements();
	}

	/*
	 * Method isEventListEmpty : Checks if is event list empty.
	 */
	public boolean isEventListEmpty()
	{

		if (this.model.isEmpty())
			return true;

		return false;

	}

}
